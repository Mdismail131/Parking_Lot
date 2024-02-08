package repositories;

import models.Gate;
import models.Operator;
import models.ParkingLot;

import java.util.HashMap;
import java.util.Optional;

public class ParkingLotRepository {

    private HashMap<Long, ParkingLot> db;
    public Optional<ParkingLot> getParkingLotByID(Long id) {
        if (db.containsKey(id)){
            return Optional.of(db.get(id));
        }
        return Optional.empty();
    }

}
