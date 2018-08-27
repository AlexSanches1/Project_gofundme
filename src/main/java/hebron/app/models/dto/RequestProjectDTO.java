package hebron.app.models.dto;

import hebron.app.models.History;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RequestProjectDTO {

    private String title;

    private Date startDate;

    private Date endDate;

    private Double goal;

    private List<History> histories;
}
