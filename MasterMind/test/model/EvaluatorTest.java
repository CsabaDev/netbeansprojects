/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author czine
 */
public class EvaluatorTest {
    
    public EvaluatorTest() {
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

    /**
     * Test of evaluate method, of class Evaluator.
     */
    @Test
    public void testEvaluate() {
        System.out.println("evaluate");
        CodePeg[] code = {CodePeg.BLUE, CodePeg.BLUE, CodePeg.RED, CodePeg.YELLOW};
        GameModel game = new GameModel(code, 8, 20, true);
        CodePeg[] guess = {CodePeg.BLUE, CodePeg.RED, CodePeg.BLUE, CodePeg.YELLOW};
        ResultPeg[] expResult = {ResultPeg.WHITE, ResultPeg.WHITE, ResultPeg.BLACK, ResultPeg.BLACK};
        ResultPeg[] result = Evaluator.evaluate(game, guess);
        assertArrayEquals(expResult, result);
        
    }
    
}
