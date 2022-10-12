package bg.sofia.uni.fmi.mjt.rentalservice.vehicle;

import bg.sofia.uni.fmi.mjt.rentalservice.location.Location;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class AbstractVehicle implements Vehicle {

    private String id;
    private Location location;
    private final String type;
    private final double pricePerMin;
    private LocalDateTime endOfReservationPeriod;

    public AbstractVehicle(String id, Location location, String type, double pricePerMin) {
        this.setId(id);
        this.location = location;
        this.type = type;
        this.pricePerMin = pricePerMin;
    }

    @Override
    public double getPricePerMinute() {
        return pricePerMin;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getId() {
        return this.id;
    }

    private void setId(String id) {
        if(id.equals(" ") || id.equals("") || id == null) {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }

    @Override
    public Location getLocation() {
       return this.location;
    }

    private void setLocation(Location location) {
        if (location == null) {
            throw new IllegalArgumentException();
        }
        this.location = location;
    }

    @Override
    public LocalDateTime getEndOfReservationPeriod() {

        if (this.endOfReservationPeriod == null || this.endOfReservationPeriod.isBefore(LocalDateTime.now())) {
            return LocalDateTime.now();
        }
        return this.endOfReservationPeriod;
    }

    @Override
    public boolean isVehicleBooked() {
        return !this.getEndOfReservationPeriod().isBefore(LocalDateTime.now()) && this.endOfReservationPeriod != null;
    }

    @Override
    public void setEndOfReservationPeriod(LocalDateTime until) {
        this.endOfReservationPeriod = until;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractVehicle that = (AbstractVehicle) o;
        return Double.compare(that.pricePerMin, pricePerMin) == 0 && Objects.equals(id, that.id) && Objects.equals(location, that.location) && Objects.equals(type, that.type) && Objects.equals(endOfReservationPeriod, that.endOfReservationPeriod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location, type, pricePerMin, endOfReservationPeriod);
    }
}
