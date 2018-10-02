package hebron.app.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String pathToVideo;

    private String pathToMainImage;

    private String title;

    private Date startDate;

    private Date endDate;

    private Integer participantsCount;

    private Double currentSum;

    @NotNull
    private Double goal;

    private String shortDescription;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "history_id")
    private History history;

}

