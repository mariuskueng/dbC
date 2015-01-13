package project_3;

import static org.junit.Assert.*;

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
        Movie movie = new Movie("Interstellar", directorChris);
        movie.addActor(actorMatthew);
        movie.addActor(actorAnne);
        movie.addActor(actorJessica);
        
        session.save(movie);
        
        session.getTransaction().commit();
    }
    
    @Test
    public void testRead () {
        assertEquals(true, true);
    }
}
