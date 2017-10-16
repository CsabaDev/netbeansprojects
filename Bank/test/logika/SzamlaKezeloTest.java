package logika;

import models.BankSzamla;
import models.Hozzafero;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SzamlaKezeloTest {
    
    public SzamlaKezeloTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testBetesz() {
        System.out.println("betesz");
        
        BankSzamla szamla = new BankSzamla(30, 1);
        szamla.hozzaferok.add(new Hozzafero("Csaba"));
        szamla.setEgyenleg(100);
        
        SzamlaKezelo kezelo = new SzamlaKezelo(szamla);
        kezelo.betesz(100);
        
        assertEquals("Nem egyenlő", 170, szamla.getEgyenleg());
    }

    @Test
    public void testKivesz() {
        System.out.println("kivesz");
        
    }

    @Test
    public void testDijSzamol() {
        System.out.println("dijSzamol");

    }

    @Test
    public void testToStringBe() {
        System.out.println("toStringBe");
    }

    /**
     * Test of toStringKi method, of class SzamlaKezelo.
     */
    @Test
    public void testToStringKi() {
        System.out.println("toStringKi");
    }

    /**
     * Test of hozzaferotFelvesz method, of class SzamlaKezelo.
     */
    @Test
    public void testHozzaferotFelvesz() {
        System.out.println("hozzaferotFelvesz");
    }

    /**
     * Test of hozzaferotEltavolit method, of class SzamlaKezelo.
     */
    @Test
    public void testHozzaferotEltavolit() {
        System.out.println("hozzaferotEltavolit");
    }

    /**
     * Test of vanEIlyenHozzafero method, of class SzamlaKezelo.
     */
    @Test
    public void testVanEIlyenHozzafero() {
        System.out.println("vanEIlyenHozzafero");
    }

    /**
     * Test of hozzaferotKeres method, of class SzamlaKezelo.
     */
    @Test
    public void testHozzaferotKeres() {
        System.out.println("hozzaferotKeres");
    }
    
}
