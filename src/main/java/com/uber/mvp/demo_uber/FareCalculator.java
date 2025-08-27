package com.uber.mvp.demo_uber;

import com.uber.mvp.demo_uber.model.Location;
import com.uber.mvp.demo_uber.model.RideDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;


@Component
public class FareCalculator {

    /**
     * 4
     * 16->37->9+49=58->25+64=89->81+64=145->1+16+25=42->16+4=20>4
     * 5->25->29->81+4=85->25+64=89
     * @param distance
     * @return
     */

    public List<RideDetails> fetchFareForAvailableServices(double distance) {
        return Collections.emptyList();
    }
}
