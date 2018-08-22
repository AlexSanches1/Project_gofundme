package hebron.app.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RequestProjectDTO {

    private String title;

    private Date startDate;

    private Date endDate;

    private Double goal;

    private String header;

    private String footer;
}
