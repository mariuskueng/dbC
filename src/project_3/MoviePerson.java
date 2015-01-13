package project_3;

import javax.persistence.*;

@Entity
@Table(name = "MoviePerson")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class MoviePerson {
    
    @Id
    @Column(name = "MOVIEPERSON_ID")
    protected String Id;
    
    @Column(name = "FIRSTNAME")
    private String firstname;
    
    @Column(name = "LASTNAME")
    private String lastname;
    
    @Column(name = "AGE")
    private int age;
    
    public MoviePerson(String Id, String firstname, String lastname, int age) {
        this.Id = Id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
