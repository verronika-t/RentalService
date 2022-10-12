package bg.sofia.uni.fmi.mjt.rentalservice.service;

import bg.sofia.uni.fmi.mjt.rentalservice.location.Location;
import bg.sofia.uni.fmi.mjt.rentalservice.vehicle.Vehicle;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class RentalService implements RentalServiceAPI {

    private Vehicle[] vehicles;


    public RentalService(Vehicle[] vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public double rentUntil(Vehicle vehicle, LocalDateTime until) {
        if (!exist(vehicle)) {
            return -1;
        }

        if (vehicle == null || until.isBefore(LocalDateTime.now()) || vehicle.getEndOfReservationPeriod().isAfter(LocalDateTime.now())) {
            return -1;
        }

        vehicle.setEndOfReservationPeriod(until);
        return  getTripMinutes(until);
    }

    private long getTripMinutes(LocalDateTime until) {
        Duration duration = Duration.between(LocalDateTime.now(), until);
        long minutes = duration.toMinutes();

        return duration.toSecondsPart() > 0 ? minutes + 1 : minutes;
    }

    private boolean exist(Vehicle vehicle) {

        for (Vehicle vehicle1 : vehicles) {
            if (vehicle1.equals(vehicle)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Vehicle findNearestAvailableVehicleInRadius(String type, Location location, double maxDistance) {
        if (maxDistance < 0 || !isValidType(type)) {
            return null;
        }

        double minDistance = Double.MAX_VALUE;
        int index = -1;

        for (int i = 0; i < vehicles.length; i++) {
            if (!vehicles[i].getType().equals(type) || vehicles[i].getEndOfReservationPeriod().isAfter(LocalDateTime.now())) {
                continue;
            }

            double distance = getDistance(location, vehicles[i].getLocation());
            if (distance < minDistance) {
                minDistance = distance;
                index = i;
            }
        }

        if (minDistance <= maxDistance && index != -1) {
            return vehicles[index];
        }

        return null;

    }

    private double getDistance(Location location1, Location location2) {
        return Math.sqrt(
                Math.pow(location1.getX() - location2.getX(), 2) + Math.pow(location1.getY() - location2.getY(), 2));
    }

    private boolean isValidType(String type) {
     if (type.equals("BICYCLE") || type.equals("CAR") || type.equals("SCOOTER")) {
         return true;
     }
     return false;
    }
}




