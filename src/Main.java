import controllers.TicketController;
import dtos.IssueTicketRequest;
import dtos.IssueTicketResponse;
import models.VehicleType;
import repositories.*;
import services.TicketService;
import strategies.RandomSpotAssignmentStrategy;
import strategies.SpotAssignmentStrategy;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        GateRepository gateRepository = new GateRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();
        TicketRepository ticketRepository = new TicketRepository();
        ParkingSpotRepository parkingSpotRepository = new ParkingSpotRepository();

        SpotAssignmentStrategy spotAssignmentStrategy = new RandomSpotAssignmentStrategy(parkingLotRepository);

        TicketService ticketService = new TicketService(gateRepository, vehicleRepository, spotAssignmentStrategy, ticketRepository, parkingSpotRepository);

        TicketController ticketController = new TicketController(ticketService);

        IssueTicketResponse issueTicketResponse = ticketController.issueTicket(IssueTicketRequest.builder()
                .gateId(1L).vehicleNumber("123").vehicleType(VehicleType.CAR).build());

    }
}