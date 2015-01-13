package project_3;

import java.util.ArrayList;

public class Movie {
    private String title;
    private Director director;
    private ArrayList<Actor> actors;
    
    public Movie(String title, Director director, ArrayList<Actor> actors) {
        this.title = title;
        this.director = director;
        this.actors = actors;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public void setActors(ArrayList<Actor> actors) {
        this.actors = actors;
    }

}
