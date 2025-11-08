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
        assertEquals(200, threadA.getData().size());
        System.out.println("Target Value is: 200");
        System.out.println("Array list size is: " + threadA.getData().size());
        System.out.println("Lab11: Test 1 works.");
        threadA.getData().clear();;
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
        System.out.println("Total Data Size: " + threadA.getData().size());
        boolean checker = false;
        if (threadA.getData().size() > 10) {
            checker = true;
        }
        assertEquals(true, checker);
        System.out.println("Test 2 is sucessful");
        //Prevents the threads from overlapping into test 3
        try{
            threadA.join();
            threadB.join();
        } catch (Exception e) {
            System.out.println("error occured");
        }
        System.out.println("Test 2 threads are over");
        threadA.getData().clear();
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
        System.out.println("Thread size is: " + threadA.getData().size());
        assertEquals(10, threadA.getData().size());
        System.out.println("Test3 is correct");
        threadB.start();
        try {
            threadB.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //trying to compact if statements so I dont have to create new variables to track it.
        assertEquals(false, (threadB.getData().size() == 10));
    }
}
