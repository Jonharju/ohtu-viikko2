/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jonas
 */
public class StatisticsTest {
    Statistics stats;
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };
    public StatisticsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.stats = new Statistics(readerStub);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void findsPlayer(){
        assertNotNull(stats.search("Kurri"));
    }
    
    @Test
    public void doesNotFindPlayer(){
        assertNull(stats.search("nope"));
    }
    
    @Test
    public void findsTeam(){
        List<Player> team = stats.team("EDM");
        assertNotNull(team);
        assertFalse(team.isEmpty());
        assertEquals(3, team.size());
        assertEquals("EDM", team.get(0).getTeam());
    }
    
    @Test
    public void doesNotFindTeam(){
        List<Player> team = stats.team("nope");
        assertNotNull(team);
        assertTrue(team.isEmpty());
    }
    
    @Test
    public void getsRightScorers() {
        List<Player> scorers = stats.topScorers(2);
        assertFalse(scorers.isEmpty());
        assertEquals("Gretzky", scorers.get(0).getName());
        assertEquals("Lemieux", scorers.get(1).getName());
        assertEquals("Yzerman", scorers.get(2).getName());
    }
}
