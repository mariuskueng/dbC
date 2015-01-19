
public abstract class MoviePerson {

    protected String Id;
    private String firstname;
    private String lastname;
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

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof MoviePerson)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        MoviePerson moviePersonClone = (MoviePerson) obj;
        boolean isEqual = this.Id.compareTo(moviePersonClone.Id) == 0;

        return isEqual;
    }
}
