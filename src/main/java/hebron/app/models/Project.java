package hebron.app.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "\"project\"")
public class Project {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private Date startDate;

    private Date endDate;

    private Integer participantsCount;

    private Double currentSum;

    private Double goal;

    @OneToMany
    @JoinColumn(name = "history_id")
    private List<History> histories;

    @ManyToMany
    @JoinTable(name = "project_user",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "teamMember_id")
    )
    private List<TeamMember> teamMembers;
}
