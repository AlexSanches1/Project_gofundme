package hebron.app.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Contributor {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    private String email;

    private String image;

}
