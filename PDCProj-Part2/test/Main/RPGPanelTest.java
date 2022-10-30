/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Main;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author botor
 */
public class RPGPanelTest {

    public RPGPanelTest() {
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
     * Test of setupMap method, of class RPGPanel.
     */
    @Test
    public void testSetupMap() {
        System.out.println("");
        System.out.println("setupMap");
        RPGPanel instance = new RPGPanel();
        instance.setupMap();
        System.out.println("Checking If gameState is correct");
        System.out.println("---------------------------------");
        if (instance.gameState == States.INSTRUCTIONSTATE) {
            System.out.println("Test Passed");
        } else {
            fail("gameState is not Instruction State");
        }
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of startGameThread method, of class RPGPanel.
     */
    @Test
    public void testStartGameThread() {
        System.out.println("");
        System.out.println("startGameThread");
        RPGPanel instance = new RPGPanel();
        instance.startGameThread();
        System.out.println("Checking If gameThread is not null");
        System.out.println("---------------------------------");
        if (instance.gameThread != null) {
            System.out.println("Test Passed");
        } else {
            fail("gameThread is null");
        }
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of restart method, of class RPGPanel.
     */
    @Test
    public void testRestart() {
        System.out.println("");
        System.out.println("restart");
        RPGPanel instance = new RPGPanel();
        instance.restart();
        System.out.println("Checking if player has default x and y upon restart");
        System.out.println("---------------------------------");
        if (instance.playerObj.worldX == instance.TILESIZE * 11 && instance.playerObj.worldY == instance.TILESIZE * 7) {
            System.out.println("Test Passed");
        } else {
            fail("Player does not have default coordinates upon restart");
        }
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of run method, of class RPGPanel.
     */
    @Test
    public void testRun() {
        System.out.println("");
        System.out.println("run");
        RPGPanel instance = new RPGPanel();
        instance.run();
        System.out.println("Checking if drawInterval is initialized");
        System.out.println("---------------------------------");
        if (instance.drawInterval != 0) {
            System.out.println("Test Passed");
        } else {
            fail("drawInterval is not initialized");
        }
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of update method, of class RPGPanel.
     */
    @Test
    public void testUpdate() {
        System.out.println("");
        System.out.println("update");
        RPGPanel instance = new RPGPanel();
        instance.update();
        System.out.println("Checking if update() is being called");
        System.out.println("---------------------------------");
        if(instance.playerObj.updateCheck == true) {
            System.out.println("Test passed");
        } else {
            fail("update() is not being called");
        }
        // TODO review the generated test code and remove the default call to fail.
    }

}
