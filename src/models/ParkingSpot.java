package models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ParkingSpot {
    private int slotNumber;
    private ParkingSpotStatus parkingSpotStatus;
    private VehicleType vehicleType;
    private ParkingFloor parkingFloor;
    private Vehicle vehicle;
}
