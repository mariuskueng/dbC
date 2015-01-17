

import javax.persistence.*;

@Entity
@Table(name="Screenwriters")
@AttributeOverrides({
    @AttributeOverride(name="firstname", column=@Column(name="FIRSTNAME")),
    @AttributeOverride(name="lastname", column=@Column(name="LASTNAME")),
    @AttributeOverride(name="age", column=@Column(name="AGE"))
})
public class Screenwriter extends MoviePerson {

    public Screenwriter(String Id, String firstname, String lastname, int age) {
        super(Id, firstname, lastname, age);
    }

}
