package project_3;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="Movies")
public class Movie { 
    @Id
    @Column(name = "MOVIE_ID")
    private long movieId;
    
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
    
    public Movie(String title, Director director) {
        this.movieId = (long) (Math.random()*1000);
        this.title = title;
        this.director = director;
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

}
