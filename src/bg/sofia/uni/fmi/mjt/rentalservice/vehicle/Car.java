package bg.sofia.uni.fmi.mjt.rentalservice.vehicle;

import bg.sofia.uni.fmi.mjt.rentalservice.location.Location;

import java.time.LocalDateTime;

public class Car extends AbstractVehicle {

    private static final String type = "CAR";
    private static final double pricePerMin = 0.50;


    public Car(String id, Location location, String type, double pricePerMin) {
        super(id, location, type, pricePerMin);
    }
}
