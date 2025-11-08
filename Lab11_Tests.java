import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;
import java.io.*;
import java.net.*;
import java.time.*;

public class Lab11_Tests {
    /*
        Complete the test case below that checks to see that threads A and B have both contributed 100 entries respectively
        to the shared ArrayList when they have both finished running.
    */
    @Test
    public void test1() {
        Lab11_Thread threadA = new Lab11_Thread("A1", 100);
        Lab11_Thread threadB = new Lab11_Thread("B1", 100);

        threadA.start();
        threadB.start();   

        try{
        threadA.join();
        threadB.join();
        } catch (Exception e){
            System.out.println("Error occured");
        }
        ArrayList<String> sum = threadA.getData();

        assertEquals(200, sum.size());
        System.out.println("Target Value is: 200");
        System.out.println("Array list size is: " + sum.size());
        System.out.println("Lab11: Test 1 works.");

    }
    /*
        Complete the test case below that checks to see if the shared ArrayList has at least 10 entries after 500ms of system time
    */
    @Test
    public void test2() {

        Lab11_Thread threadA = new Lab11_Thread("A2", 500);
        Lab11_Thread threadB = new Lab11_Thread("B2", 500);

        threadA.start();
        threadB.start();

        try {
            Thread.sleep(500); 
        } catch (Exception e){
            e.printStackTrace();
        }
        ArrayList<String> sum = threadA.getData();
        System.out.println("Shared Array List Size: " + sum.size());

        boolean checker = false;
        if (sum.size() > 10) {
            checker = true;
        }
        assertEquals(true, checker);
        System.out.println("Test 2 is sucessful");
    }
    
    /*
        Complete the test case below that checks to see if thread A finishes adding its 10 entries before thread B was allowed to 
        add anything to the shared ArrayList
    */
    @Test
    public void test3() {
        Lab11_Thread threadA = new Lab11_Thread("A3", 10);
        Lab11_Thread threadB = new Lab11_Thread("B3", 10);

        threadA.start();
        
        try {
            threadA.join();
        } catch (Exception e){
            e.printStackTrace();
        }
        
        threadB.start();
    }
}
