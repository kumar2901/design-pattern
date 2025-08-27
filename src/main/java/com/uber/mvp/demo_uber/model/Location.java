package com.uber.mvp.demo_uber.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Location {

    private String longitude;
    private String altitude;
}
