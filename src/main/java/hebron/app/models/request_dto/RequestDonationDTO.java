package hebron.app.models.request_dto;

import lombok.Data;

@Data
public class RequestDonationDTO {

    private RequestContributorDTO requestContributorDTO;

    private Double sum;

    private Long projectId;
}
