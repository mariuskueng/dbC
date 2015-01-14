package project_3;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="Movies")
public class Movie { 
    @Id
    @Column(name = "MOVIE_ID")
    private String movieId;
    
    @Column(name = "TITLE") 
    private String title;
    
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="DIRECTOR_ID")
    private Director director;
    
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="Movies_Actors", 
        joinColumns={@JoinColumn(name="MOVIE_ID")}, 
        inverseJoinColumns={@JoinColumn(name="ACTOR_ID")})
    private Set<Actor> actors = new HashSet<>();
    
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="SCREENPLAY_ID")
    private Screenplay screenplay;
    
    public Movie(String movieId, String title, Director director, Screenplay screenplay) {
        this.movieId = movieId;
        this.title = title;
        this.director = director;
        this.screenplay = screenplay;
    }
    
    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Screenplay getScreenplay() {
        return screenplay;
    }

    public void setScreenplay(Screenplay screenplay) {
        this.screenplay = screenplay;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public void addActor(Actor actor) {
        actors.add(actor);
    }
    
    public void removeActor(Actor actor) {
        actors.remove(actor);
    }
    
    @Override
    public boolean equals(Object obj) {
           
        if (!(obj instanceof Movie)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Movie movieClone = (Movie) obj;
        boolean isEqual = this.movieId.compareTo(movieClone.movieId) == 0;
        
        return isEqual;
    }

}
