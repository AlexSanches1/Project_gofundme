package hebron.app.models.request_dto;

import lombok.Data;

import java.util.Date;

@Data
public class RequestProjectDTO {

    private String title;

    private String shortDescription;

    private Date startDate;

    private Date endDate;

    private Double goal;
}
