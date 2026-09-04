package com.baeldung.lju;

import org.junit.jupiter.api.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

class LifecycleMethodsAndResourcesHandlingUnitTest {

    final static Logger logger = LoggerFactory.getLogger(LifecycleMethodsAndResourcesHandlingUnitTest.class);
    //BufferedReader fileReader;

    // used for @BeforeAll. fileReader class variable is now shared across all the tests
    static BufferedReader fileReader;

    // open the file `only once` and explore the effects of such an operation
    @BeforeAll
    static void setUpResource() throws Exception {
       InputStream fileStream = LifecycleMethodsAndResourcesHandlingUnitTest.class.getClassLoader().getResourceAsStream("file.txt");
       fileReader = new BufferedReader(new InputStreamReader(fileStream));
       logger.info("static fileReader is ready: {}", fileReader.ready());
    }

    @BeforeEach
    void setupUsingResource() throws Exception {
       // access the contents of the file.txt file before each test:
        InputStream fileStream =  LifecycleMethodsAndResourcesHandlingUnitTest.class.getClassLoader().getResourceAsStream("file.txt");
        fileReader = new BufferedReader(new InputStreamReader(fileStream));
        logger.info("fileReader is ready: {}", fileReader.ready());
    }

    //method that prints to the console two lines from the file.txt file:
    @Test
    void givenOpenResource_whenReadLines1_thenLineIsLogged() throws Exception {
        for (int i = 0; i < 2; i++){
            logger.info(fileReader.readLine());
        }
        fileReader.close();
    }

    //method that adds another test in the same class, which also prints two lines from the file:
    @Test
    void givenOpenResource_whenReadLines2_thenLineIsLogged() throws Exception {
        for (int i = 0; i < 2; i++){
            logger.info(fileReader.readLine());
        }
        fileReader.close();
    }

//    @AfterEach
//    void cleanupResource() throws Exception {
//        fileReader.close();
//        logger.info("fileReader is closed");
//    }

    // used with @BeforeAll
    // keep the stream open while running all the tests:
    // execute this method before running each test
    @AfterEach
    void cleanupResource() throws Exception {

    }

    @AfterAll
    static void cleanupStaticResource() throws Exception {
        fileReader.close();
        logger.info("static fileReader is closed");
    }

}
