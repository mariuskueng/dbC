package project_3;

import java.util.ArrayList;

public class Plot {

    public static void main(String[] args) {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        actors.add(new Actor("Marius", "Küng", 24));
        ArrayList<Movie> movies = new ArrayList<Movie>();
        Movie m1 = new Movie("Star Wars", new Director("Hans", "Wurst", 50), actors);
        movies.add(m1);
        movies.add(m1);
        movies.add(m1);
        
        for (Movie movie : movies) {
            System.out.println(movie.getTitle());
            for (Actor actor : movie.getActors()) {
               System.out.println(actor.getFirstname());
            }
        }
    }

}
