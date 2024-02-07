package services;

import exceptions.GateNotFoundException;
import models.Gate;
import models.Ticket;
import models.Vehicle;
import models.VehicleType;
import repositories.GateRepository;
import repositories.VehicleRepository;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

public class TicketService {

    private final GateRepository gateRepository;
    private final VehicleRepository vehicleRepository;

    public TicketService(GateRepository gateRepository, VehicleRepository vehicleRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public Ticket issueTicket(String vehicleNumber, VehicleType vehicleType, Long gateId) throws GateNotFoundException {
        Ticket ticket = new Ticket();
        Optional<Gate> gateOptional = gateRepository.findGateById(gateId);
//        if (gateOptional.isEmpty()) {
//            throw new GateNotFoundException();
//        }
        Gate gate = gateOptional.orElseThrow(GateNotFoundException::new);

        Vehicle savedVehicle;

        Optional<Vehicle> vehicleOptional = vehicleRepository.findVehicleById(vehicleNumber);
        if (vehicleOptional.isEmpty()) {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleNumber(vehicleNumber);
            vehicle.setVehicleType(vehicleType);
            savedVehicle = vehicle;
        } else {
            savedVehicle = vehicleOptional.get();
        }
        ticket.setGeneratedAt(gate);
        ticket.setVehicle(savedVehicle);
        ticket.setEntryTime(new Date());
        ticket.setGeneratedBy(gate.getOperator());
        ticket.setNumber(String.valueOf(Instant.now().getEpochSecond()));
//        ticket.setParkingSpot();
        return null;
    }
}
