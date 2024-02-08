package services;

import exceptions.GateNotFoundException;
import models.*;
import repositories.*;
import strategies.RandomSpotAssignmentStrategy;
import strategies.SpotAssignmentStrategy;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

public class TicketService {

    private final GateRepository gateRepository;
    private final VehicleRepository vehicleRepository;
    private final SpotAssignmentStrategy spotAssignmentStrategy;
    private final TicketRepository ticketRepository;

    private final ParkingSpotRepository parkingSpotRepository;


    public TicketService(GateRepository gateRepository, VehicleRepository vehicleRepository, SpotAssignmentStrategy spotAssignmentStrategy, TicketRepository ticketRepository, ParkingSpotRepository parkingSpotRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.ticketRepository = ticketRepository;
        this.spotAssignmentStrategy = spotAssignmentStrategy;
        this.parkingSpotRepository = parkingSpotRepository;
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

        ParkingSpot parkingSpot = spotAssignmentStrategy.getSpot(1L, gate, vehicleType);

        parkingSpot.setVehicle(savedVehicle);
        parkingSpot.setParkingSpotStatus(ParkingSpotStatus.OCCUPIED);


        ticket.setParkingSpot(parkingSpot);
        ticketRepository.save(ticket);

        return ticket;
    }
}
