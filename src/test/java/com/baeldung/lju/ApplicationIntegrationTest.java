package com.baeldung.lju;

import org.junit.jupiter.api.Test;

class ApplicationIntegrationTest {


    @Test
    void mainAppMethodIntegrationTest() {
        //invoke the main() method with an empty array of arguments
        LjuApp.main(new String[]{});
    }
}
