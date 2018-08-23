package hebron.app.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "TeamMember")
@NoArgsConstructor
@Table(name = "\"teamMember\"")
public class TeamMember {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

//    private Role role;

    private String fullName;

    @OneToOne
    @JoinColumn(name = "photo_Id")
    private UserPhoto userPhoto;
}
