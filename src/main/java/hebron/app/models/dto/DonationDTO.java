package hebron.app.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DonationDTO {

    private Double sum;

    private Date date;

    private Long projectId;

    private Long contributorId;

}
