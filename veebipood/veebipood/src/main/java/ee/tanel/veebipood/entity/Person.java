package ee.tanel.veebipood.entity;


//import javax.persistence.* -> jakarta
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Person { // "User" on hõivatud PostgreSQL tasandil

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true) // andmebaasis peab olema unikaalne
    private String email;
    private String password;
    @Column(unique = true)
    private String personalCode;

    // cascadetype -> cascadetype.remove  kui kustutatakse Person, siis ka Address
    // casecadetype.persist  kui lisatakse person ja temaga antakse kaasa address mida pole DB-s, siis ta lisatakse andmebaasi kui eraldi kirje address tabelisse
    // cascadetype.merge  kui muudetakse personit ja personi küljes olevat addressi siis muutub nii personi sisu kui ka addressi sisu

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;


}
