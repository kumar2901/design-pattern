package com.kumar.design.pattern.idempotency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class IdempotencyController {

    private final IdempotencyService idempotencyService;

    @Autowired
    public IdempotencyController(IdempotencyService idempotencyService) {
        this.idempotencyService = idempotencyService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestHeader(value = "Idempotency-Key", required = true) String idempotencyKey,
                                                     @RequestBody OrderRequest request) {
        OrderResponse cached = idempotencyService.get(idempotencyKey);
        if (cached != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header("X-Idempotent-Cache", "HIT")
                    .body(cached);
        }

        // simulate order processing
        OrderResponse resp = new OrderResponse(UUID.randomUUID().toString(), "CREATED");

        // store response for idempotency
        idempotencyService.put(idempotencyKey, resp);

        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .header("X-Idempotent-Cache", "MISS")
                .body(resp);
    }
}

