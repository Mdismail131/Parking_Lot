package services;

import models.Gate;
import models.Ticket;
import models.Vehicle;
import models.VehicleType;
import repositories.GateRepository;

import java.util.Optional;

public class TicketService {

    private final GateRepository gateRepository;

    public TicketService(GateRepository gateRepository) {
        this.gateRepository = gateRepository;
    }

    public Ticket issueTicket(String vehicleNumber, VehicleType vehicleType, Long gateId) {
        Optional<Gate> getOptional = gateRepository.findGateById(gateId);
        return null;
    }
}
