package hebron.app.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectDTO {

    private Long id;

    private String pathToVideo;

    private String pathToMainImage;

    private String title;

    private Date startDate;

    private Date endDate;

    private Integer participantsCount;

    private Double currentSum;

    private Double goal;

    private String shortDescription;

    private HistoryDTO history;
}

