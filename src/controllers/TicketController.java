package controllers;

import dtos.IssueTicketRequest;
import dtos.IssueTicketResponse;
import models.Ticket;
import models.VehicleType;
import services.TicketService;


public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public IssueTicketResponse issueTicket(IssueTicketRequest request ) {
        Ticket ticket = ticketService.issueTicket(request.getVehicleNumber(), getVehicleType(request.getVehicleType()), request.getGateId());

        return IssueTicketResponse.builder()
                .ticketId(ticket.getNumber())
                .floorNumber(ticket.getParkingSpot().getParkingFloor().getFloorNumber())
                .entryTime(ticket.getEntryTime())
                .vehicleNumber(ticket.getVehicle().getVehicleNumber())
                .gateNumber(ticket.getGeneratedAt().getGateNumber())
                .build();
    }
    private static VehicleType getVehicleType(String type) {
        switch (type) {
            case "CAR" :
                return VehicleType.CAR;
            case "BIKE" :
                return VehicleType.BIKE;
            default:
                return VehicleType.BUS;
        }
    }
}
