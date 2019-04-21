package test;

import static org.junit.Assert.*;
 
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
 
import org.junit.Before;
import org.junit.Test;

import printprime.printPrimes;

public class primeTest {
    printPrimes p;
    ByteArrayOutputStream str;
     
    @Before
    public void setup() throws Exception{
        p = new prime();
        str = new ByteArrayOutputStream();
        System.setOut(new PrintStream(str));
    }
     
    @Test
    public void test() {
        String output = new String("Prime: 2\r\nPrime: 3\r\nPrime: 5\r\nPrime: 7\r\nPrime: 11\r\n");
        p.printPrimes(5);
        assertEquals(output, str.toString());
    }

}
