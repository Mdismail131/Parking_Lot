package strategies;

import models.*;
import repositories.ParkingLotRepository;

import java.util.Optional;

public class RandomSpotAssignmentStrategy
        implements SpotAssignmentStrategy{
    private ParkingLotRepository parkingLotRepository;

    public RandomSpotAssignmentStrategy(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    @Override
    public ParkingSpot getSpot(Long parkingLotId, Gate gate, VehicleType vehicleType) {
        Optional<ParkingLot> parkingLot = parkingLotRepository.getParkingLotByID(1L);

        for (ParkingFloor parkingFloor: parkingLot.orElseThrow().getParkingFloorList()) {
            for (ParkingSpot parkingSpot: parkingFloor.getParkingSpotsList()) {
                if (parkingSpot.getVehicleType().equals(vehicleType) &&
                        parkingSpot.getParkingSpotStatus().equals(ParkingSpotStatus.AVAILABLE)) {
                    return parkingSpot;
                }
            }
        }
        return null;
    }
}
