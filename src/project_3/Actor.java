package project_3;

import javax.persistence.*;

@Entity
@Table(name="Actors")
@AttributeOverrides({
    @AttributeOverride(name="firstname", column=@Column(name="FIRSTNAME")),
    @AttributeOverride(name="lastname", column=@Column(name="LASTNAME")),
    @AttributeOverride(name="age", column=@Column(name="AGE"))
})
public class Actor extends MoviePerson {
    
    public Actor(String id, String firstname, String lastname, int age) {
        super(id, firstname, lastname, age);

    }

}
