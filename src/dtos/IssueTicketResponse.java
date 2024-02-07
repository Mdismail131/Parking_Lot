package dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Builder
public class IssueTicketResponse {
    private int spotNumber;
    private int floorNumber;
    private String ticketId;
    private Date entryTime;
    private int gateNumber;
    private String vehicleNumber;
}
