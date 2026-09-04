package com.baeldung.lju;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JUnitBaseTest {

    @Test
    void genericTest(){
        int a = 2;
        int b = 3;

        int sum = a + b;
        // Failing Test
        //Assertions.assertEquals(6, sum);

        Assertions.assertEquals(6,sum, "testing the alternative path");
        Assertions.assertEquals(1,b-a);
        Assertions.assertEquals(5,a+b, "testing the happy path");
    }
}
