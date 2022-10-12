package bg.sofia.uni.fmi.mjt.rentalservice.vehicle;

import bg.sofia.uni.fmi.mjt.rentalservice.location.Location;

import java.time.LocalDateTime;

public class Bicycle extends AbstractVehicle{

    private static final String type = "BICYCLE";
    private static final double pricePerMin = 0.20;

    public Bicycle(String id, Location location) {
        super(id, location, type, pricePerMin);
    }


}
