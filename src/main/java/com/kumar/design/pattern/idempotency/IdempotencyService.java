package com.kumar.design.pattern.idempotency;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class IdempotencyService {

    private record Entry(OrderResponse response, Instant expiresAt) {
    }

    private final Map<String, Entry> store = new ConcurrentHashMap<>();
    private final ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor();

    // default TTL 5 minutes
    private final long ttlMillis = TimeUnit.MINUTES.toMillis(5);

    public IdempotencyService() {
        cleaner.scheduleAtFixedRate(this::cleanup, ttlMillis, ttlMillis, TimeUnit.MILLISECONDS);
    }

    public OrderResponse get(String key) {
        Entry e = store.get(key);
        if (e == null) return null;
        if (Instant.now().isAfter(e.expiresAt)) {
            store.remove(key);
            return null;
        }
        return e.response;
    }

    public void put(String key, OrderResponse response) {
        store.put(key, new Entry(response, Instant.now().plusMillis(ttlMillis)));
    }

    private void cleanup() {
        Instant now = Instant.now();
        for (Map.Entry<String, Entry> e : store.entrySet()) {
            if (now.isAfter(e.getValue().expiresAt)) {
                store.remove(e.getKey());
            }
        }
    }
}

