package hebron.app.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "\"admin\"")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Byte id;

    private String login;

    private String password;
}
