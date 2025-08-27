package com.uber.mvp.demo_uber.service;

import com.uber.mvp.demo_uber.DistanceCalculator;
import com.uber.mvp.demo_uber.FareCalculator;
import com.uber.mvp.demo_uber.model.CabBookingResponse;
import com.uber.mvp.demo_uber.model.Location;
import com.uber.mvp.demo_uber.model.RideDetails;
import com.uber.mvp.demo_uber.model.ServiceType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CabServiceImpl implements  CabService{

    private DistanceCalculator distanceCalculator;
    private FareCalculator fareCalculator;

    @Override
    public List<RideDetails> requestCab(Location pickUp, Location drop) {
        double distance=distanceCalculator.calculateDistance(pickUp,drop);
        return fareCalculator.fetchFareForAvailableServices(distance);
    }

    @Override
    public CabBookingResponse bookCab(Location pickUpLocation, Location dropLocation, ServiceType vehicle) {


        return null;
    }
}
