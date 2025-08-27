package com.uber.mvp.demo_uber.service;

import com.uber.mvp.demo_uber.model.CabBookingResponse;
import com.uber.mvp.demo_uber.model.Location;
import com.uber.mvp.demo_uber.model.RideDetails;
import com.uber.mvp.demo_uber.model.ServiceType;

import java.util.List;

public interface CabService {


    //request ride

    List<RideDetails> requestCab(Location pickUp, Location drop);
     CabBookingResponse bookCab(Location pickUpLocation, Location dropLocation, ServiceType vehicle);
}
