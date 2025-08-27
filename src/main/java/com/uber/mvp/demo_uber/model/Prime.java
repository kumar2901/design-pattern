package com.uber.mvp.demo_uber.model;

import java.util.HashMap;
import java.util.Map;

public class Prime implements ServiceType {

    static Map<CurrencyCode, Double> priceMap=new HashMap<>();
    static {
        priceMap.put(CurrencyCode.USD,3.0);
        priceMap.put(CurrencyCode.INR,8.0);
        priceMap.put(CurrencyCode.EUR,2.5);
    }
    @Override
    public Double rate(CurrencyCode currencyCode) {
        return priceMap.getOrDefault(currencyCode,4.0);
    }
}
