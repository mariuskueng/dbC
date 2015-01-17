

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class MovieTests {
    /** This is the one session we work on */
    Session session;
    
    Movie classUnderTest;
    
    @BeforeClass
    static public void beforeClass () { 
        
    }
    
    @AfterClass
    static public void afterClass () {

    }
    
    @Before
    public void setUp () {
        // reset session - drops tables
        session = createSession();
        
        // setup test data:
        // so every test case has the 
        // same environment work with.
        createMovieTestEnvironment();
        
        // convenience - begins a
        // new transaction and commits
        // it in the tearDown method.
        session.beginTransaction();
    }
    
    @After
    public void tearDown () {
        // corresponding part to beginTransaction 
        // see setup - change simultaneously!
        session.getTransaction().commit();
    }
    
    public Session createSession () {
        
        Configuration config = new Configuration();
        
        // add all class annotations
        config.addAnnotatedClass(MoviePerson.class);
        config.addAnnotatedClass(Actor.class);
        config.addAnnotatedClass(Director.class);
        config.addAnnotatedClass(Movie.class);
        config.addAnnotatedClass(Screenwriter.class);
        config.addAnnotatedClass(Screenplay.class);
        config.configure("hibernate.cfg.xml");
        
        // some exception handling here would be awesome
        new SchemaExport(config).create(true,true);
        
        ServiceRegistry serviceRegistry =
            new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
        SessionFactory factory = config.buildSessionFactory(serviceRegistry);

        // first one should be openSession and not getSession
        // see: https://stackoverflow.com/questions/2378572/hibernate-session-is-closed
        return factory.openSession();   
    }
    
    public void createMovieTestEnvironment () {
        session.beginTransaction();
        
        // first we instantiate a actors
        Actor actorMatthew = new Actor("A01", "Matthew", "McConaughey", 45);
        Actor actorAnne = new Actor("A02", "Anne", "Hathaway", 32);
        Actor actorJessica = new Actor("A03", "Jessica", "Chastain", 37);
        
        session.save(actorMatthew);
        session.save(actorAnne);
        session.save(actorJessica);
        
        // and the director
        Director directorChris = new Director("D01", "Christopher", "Nolan", 44);
        
        // add the director to the database
        session.save(directorChris);
        
        // and a screenwriter
        
        Screenwriter screenwriter = new Screenwriter("SW01", "Jonathan", "Nolan", 37);
        
        session.save(screenwriter);
        
        // and a screenplay 
        
        Screenplay screenplay = new Screenplay("SP01", "Interstallar-2014", Calendar.getInstance(), screenwriter);
        
        session.save(screenplay);
        
        // and of course a movie
        classUnderTest = new Movie("MINTER", "Interstellar", directorChris, screenplay);
        classUnderTest.addActor(actorMatthew);
        classUnderTest.addActor(actorAnne);
        classUnderTest.addActor(actorJessica);
        
        session.save(classUnderTest);
        
        session.getTransaction().commit();
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testGetDirectors () {
        List<Director> allDirectors = session.createQuery("from Director").list();
 
        // check if the director is in the database 
        assertTrue(allDirectors.contains(((Director) new Director("D01", "Christopher", "Nolan", 44))));
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testGetActors () {
        List<Actor> allActors = session.createQuery("from Actor").list();
        
        // check if the actors are in the database
        assertTrue(allActors.contains(((Actor) new Actor("A01", "Matthew", "McConaughey", 45))));
        assertTrue(allActors.contains(((Actor) new Actor("A02", "Anne", "Hathaway", 32))));
        assertTrue(allActors.contains(((Actor) new Actor("A03", "Jessica", "Chastain", 37))));
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testGetScreenplays () {
        List<Screenplay> allScreenplays = session.createQuery("from Screenplay").list();
        
        // check if the screenplay is in the database
        assertTrue(allScreenplays.contains(((Screenplay) new Screenplay("SP01", "Interstallar-2014", Calendar.getInstance(), new Screenwriter("SW01", "Jonathan", "Nolan", 37)))));
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testGetScreenwriters () {
        List<Screenwriter> allScreenwriters = session.createQuery("from Screenwriter").list();
        
        // check if the screenwriter is in the database
        assertTrue(allScreenwriters.contains(((Screenwriter) new Screenwriter("SW01", "Jonathan", "Nolan", 37))));
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testGetMovies () {
        List<Movie> allMovies = session.createQuery("from Movie").list();
        
        // check if the movies are in the database
        assertTrue(allMovies.contains(((Movie) 
                new Movie("MINTER", "Interstellar", new Director("D01", "Christopher", "Nolan", 44), 
                new Screenplay("SP01", "Interstellar-2014", Calendar.getInstance(), 
                new Screenwriter("SW01", "Jonathan", "Nolan", 37
        ))))));
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testUpdateMovie () {
    	// get the movie
        List<Movie> moviesQuery = session.createQuery("from Movie M where M.id = 'MINTER'").list();
        Movie m = moviesQuery.get(0);
        // change the movie title
        m.setTitle("The Room");
        session.saveOrUpdate(m);
        // check the new title
        assertEquals("The Room", m.getTitle());
        
        // get the movie again
        List<Movie> moviesQuery2 = session.createQuery("from Movie M where M.id = 'MINTER'").list();
        Movie m2 = moviesQuery2.get(0);
        
        // check the title from a new query
        assertEquals("The Room", m2.getTitle());
        
        // and an actor
        List<Actor> allActors = session.createQuery("from Actor where firstname = 'Jessica'").list();
        Actor actorJessica = allActors.get(0);

        // check the firstname
        assertEquals("Jessica", actorJessica.getFirstname());

        // remove an actor from this movie
        m2.removeActor(actorJessica);
        session.saveOrUpdate(m2);
        
        // check the new amount of actors participating
        assertEquals(2, m2.getActors().size());
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testDeleteMovies () {
    	// get the movie
    	List<Movie> moviesQuery = session.createQuery("from Movie M where M.id = 'MINTER'").list();
    	Movie m = moviesQuery.get(0);
    	
    	// remove all actors from it
    	for (Actor a : m.getActors()) {
			System.out.println(a);
			session.delete(a);
		}
    	
    	// remove the movie
    	session.delete(m);
    	
    	// new movie query
        moviesQuery = session.createQuery("from Movie M where M.id = 'MINTER'").list();
        
        // check that the movie isn't available anymore in the database
        assertEquals(0, moviesQuery.size());
        
        // and also the actors
        List<Actor> allActors = session.createQuery("from Actor").list();
        assertEquals(0, allActors.size());
    }
}
