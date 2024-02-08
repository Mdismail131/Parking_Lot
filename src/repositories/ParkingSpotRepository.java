package repositories;

import models.ParkingLot;
import models.ParkingSpot;
import models.ParkingSpotStatus;
import models.Vehicle;

import java.util.HashMap;
import java.util.Optional;

public class ParkingSpotRepository {
    private HashMap<Long, ParkingSpot> db;
    public Optional<ParkingSpot> getParkingLotByID(Long id) {
        if (db.containsKey(id)){
            return Optional.of(db.get(id));
        }
        return Optional.empty();
    }
    public void save(ParkingSpot parkingSpot) {
        if (!parkingSpot.getParkingSpotStatus().equals(ParkingSpotStatus.OCCUPIED)) {
            parkingSpot.setParkingSpotStatus(ParkingSpotStatus.OCCUPIED);
        }
    }
}
