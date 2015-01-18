

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class MovieTests {
    /** This is the one session we work on */
	ObjectContainer db;

    Movie classUnderTest;

    @BeforeClass
    static public void beforeClass () {

    }

    @AfterClass
    static public void afterClass () {

    }

    @Before
    public void setUp () {
    	File file = new File("Movie.yap");
		if(file.exists()){
			file.delete();
		}

    	db = Db4oEmbedded.openFile("Movie.yap");

        // setup test data:
        // so every test case has the
        // same environment work with.
        createMovieTestEnvironment();
    }

    @After
    public void tearDown () {
    	// close db connection
    	db.close();
    }

    public void createMovieTestEnvironment () {

        // first we instantiate a actors
        Actor actorMatthew = new Actor("A01", "Matthew", "McConaughey", 45);
        Actor actorAnne = new Actor("A02", "Anne", "Hathaway", 32);
        Actor actorJessica = new Actor("A03", "Jessica", "Chastain", 37);

        db.store(actorMatthew);
        db.store(actorAnne);
        db.store(actorJessica);

        // and the director
        Director directorChris = new Director("D01", "Christopher", "Nolan", 44);

        // add the director to the database
        db.store(directorChris);

        // and a screenwriter

        Screenwriter screenwriter = new Screenwriter("SW01", "Jonathan", "Nolan", 37);

        db.store(screenwriter);

        // and a screenplay

        Screenplay screenplay = new Screenplay("SP01", "Interstellar-2014", Calendar.getInstance(), screenwriter);

        db.store((Screenplay) screenplay);

        // and of course a movie
        classUnderTest = new Movie("MINTER", "Interstellar", directorChris, screenplay);
        classUnderTest.addActor(actorMatthew);
        classUnderTest.addActor(actorAnne);
        classUnderTest.addActor(actorJessica);

        db.store(classUnderTest);
    }

    public <T> List<T> createTypedList (ObjectSet<T> set) {
    	List<T> list = new ArrayList<T>();
    	for (T obj : set) {
        	list.add((T) obj);
		}
    	return list;
    }
    

    @Test
    public void testCreateDirectors () {
    	List<Director> allDirectors = createTypedList(db.query(Director.class));

        // check if the director is in the database
        assertTrue(allDirectors.contains(((Director) new Director("D01", "Christopher", "Nolan", 44))));
    }

    @Test
    public void testGetDirector () {
    	Director director = classUnderTest.getDirector();
    	assertEquals("D01", director.Id);
    	assertEquals("Christopher", director.getFirstname());
    	assertEquals("Nolan", director.getLastname());
    	assertEquals(44, director.getAge());
    }

    @Test
    public void testCreateActors () {
    	List<Actor> allActors = createTypedList(db.query(Actor.class));

        // check if the actors are in the database
        assertTrue(allActors.contains(((Actor) new Actor("A01", "Matthew", "McConaughey", 45))));
        assertTrue(allActors.contains(((Actor) new Actor("A02", "Anne", "Hathaway", 32))));
        assertTrue(allActors.contains(((Actor) new Actor("A03", "Jessica", "Chastain", 37))));
    }
    
    @Test
    public void testGetActors () {
    	Set<Actor> allActors = (Set<Actor>) classUnderTest.getActors();
    	
    	assertEquals(3, allActors.size());
    }

    @Test
    public void testCreateScreenplays () {
        List<Screenplay> allScreenplaysList = createTypedList(db.query(Screenplay.class));

        // check if the screenplay is in the database
        assertTrue(allScreenplaysList.contains(((Screenplay) new Screenplay("SP01", "Interstallar-2014", Calendar.getInstance(), new Screenwriter("SW01", "Jonathan", "Nolan", 37)))));
    }
    
    @Test
    public void testGetScreenplay () {
    	Screenplay screenplay = classUnderTest.getScreenplay();
    	assertEquals("Interstellar-2014", screenplay.getTitle());
    }

    @Test
    public void testCreateScreenwriters () {
    	List<Screenwriter> allScreenwriters = createTypedList(db.query(Screenwriter.class));

        // check if the screenwriter is in the database
        assertTrue(allScreenwriters.contains(((Screenwriter) new Screenwriter("SW01", "Jonathan", "Nolan", 37))));
    }
    
    @Test
    public void testGetScreenwriter () {
    	Screenwriter screenwriter = classUnderTest.getScreenplay().getScreenWriter();
    	assertEquals("SW01", screenwriter.Id);
    	assertEquals("Jonathan", screenwriter.getFirstname());
    	assertEquals("Nolan", screenwriter.getLastname());
    	assertEquals(37, screenwriter.getAge());
    }

    @Test
    public void testCreateMovies () {
    	List<Movie> allMovies = createTypedList(db.query(Movie.class));

        // check if the movies are in the database
        assertTrue(allMovies.contains(((Movie)
                new Movie("MINTER", "Interstellar", new Director("D01", "Christopher", "Nolan", 44),
                new Screenplay("SP01", "Interstellar-2014", Calendar.getInstance(),
                new Screenwriter("SW01", "Jonathan", "Nolan", 37
        ))))));
    }
    
    @Test
    public void testGetMovie () {
    	Movie movie = classUnderTest;
    	assertEquals("MINTER", movie.getMovieId());
    	assertEquals("Interstellar", movie.getTitle());
    }

    @Test
    public void testUpdateMovie () {
    	// get the movie
    	List<Movie> allMovies = createTypedList(db.query(Movie.class));
        Movie m = allMovies.get(0);
        // change the movie title
        m.setTitle("The Room");
        db.store(m);
        // check the new title
        assertEquals("The Room", m.getTitle());

        // get the movie again
        List<Movie> allMovies2 = createTypedList(db.query(Movie.class));
        Movie m2 = allMovies2.get(0);

        // check the title from a new query
        assertEquals("The Room", m2.getTitle());

        // and an actor
        List<Actor> allActors = createTypedList(db.query(Actor.class));

        Actor actorJessica = allActors.get(2);

        // check the firstname
        assertEquals("Jessica", actorJessica.getFirstname());

        // remove an actor from this movie
        m2.removeActor(actorJessica);
        db.store(m2);

        // check the new amount of actors participating
        assertEquals(2, m2.getActors().size());
    }

    @Test
    public void testDeleteMovies () {
    	// get the movie
    	List<Movie> allMovies = createTypedList(db.query(Movie.class));
    	Movie m = allMovies.get(0);

    	// remove all actors from it
    	for (Actor a : m.getActors()) {
			db.delete(a);
		}

    	// remove the movie
    	db.delete(m);

    	// new movie query
    	allMovies = createTypedList(db.query(Movie.class));

        // check that the movie isn't available anymore in the database
        assertEquals(0, allMovies.size());

        // and also the actors
        List<Actor> allActors = createTypedList(db.query(Actor.class));
        assertEquals(0, allActors.size());
    }
}
