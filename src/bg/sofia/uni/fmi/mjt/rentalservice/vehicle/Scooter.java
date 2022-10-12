package bg.sofia.uni.fmi.mjt.rentalservice.vehicle;

import bg.sofia.uni.fmi.mjt.rentalservice.location.Location;

import java.time.LocalDateTime;

public class Scooter extends AbstractVehicle{

    private static final String type = "SCOOTER";
    private static final double pricePerMin = 0.30;

    public Scooter(String id, Location location, String type, double pricePerMin) {
        super(id, location, type, pricePerMin);
    }
}
