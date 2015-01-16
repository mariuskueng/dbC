

import javax.persistence.*;

@Entity
@Table(name="Directors")
@AttributeOverrides({
    @AttributeOverride(name="firstname", column=@Column(name="FIRSTNAME")),
    @AttributeOverride(name="lastname", column=@Column(name="LASTNAME")),
    @AttributeOverride(name="age", column=@Column(name="AGE"))
})
public class Director extends MoviePerson {
    
    public Director(String Id, String firstname, String lastname, int age) {
        super(Id, firstname, lastname, age);
    }

}
