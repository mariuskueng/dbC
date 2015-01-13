package project_3;

import static org.junit.Assert.*;

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
        
        config.addAnnotatedClass(MoviePerson.class);
        config.addAnnotatedClass(Actor.class);
        config.addAnnotatedClass(Director.class);
        config.addAnnotatedClass(Movie.class);
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
        
        session.save(directorChris);
        
        // and of course a movie
        classUnderTest = new Movie("MINTER", "Interstellar", directorChris);
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
        assertTrue(allDirectors.contains(((Director) new Director("D01", "Christopher", "Nolan", 44))));
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testGetActors () {
        List<Actor> allActors = session.createQuery("from Actor").list();
        assertTrue(allActors.contains(((Actor) new Actor("A01", "Matthew", "McConaughey", 45))));
        assertTrue(allActors.contains(((Actor) new Actor("A02", "Anne", "Hathaway", 32))));
        assertTrue(allActors.contains(((Actor) new Actor("A03", "Jessica", "Chastain", 37))));
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testGetMovies () {
        List<Movie> allMovies = session.createQuery("from Movie").list();
        assertTrue(allMovies.contains(((Movie) new Movie("MINTER", "Interstellar", new Director("D01", "Christopher", "Nolan", 44)))));
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testUpdateMovie () {
        List<Movie> moviesQuery = session.createQuery("from Movie M where M.id = 'MINTER'").list();
        Movie actorMatthew = moviesQuery.get(0);
        actorMatthew.setTitle("The Room");
        session.saveOrUpdate(actorMatthew);
        assertEquals("The Room", actorMatthew.getTitle());
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testDeleteMovies () {
        Query query = session.createQuery("delete from Movie M where M.id = 'MINTER'");
        query.executeUpdate();
        List<Movie> moviesQuery = session.createQuery("from Movie M where M.id = 'MINTER'").list();
        assertEquals(0, moviesQuery.size());
    }
}
