package dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueTicketRequest {
    private String vehicleNumber;
    private Long gateId;
    private String vehicleType;
}
