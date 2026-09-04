package com.baeldung.lju;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
custom display name generator that converts camel case and underscores in method names into a more readable format,
from--> "givenExistingCampaign_whenFindByNonExistingId_thenNoCampaignRetrieved"
to --> “Given existing campaign, when find by non existing id, then no campaign retrieved"
 */
// adding the new display name generator to our test class:
@DisplayNameGeneration(CustomDisplayNameGenerator.class)
public class CustomDisplayNameGenerator extends DisplayNameGenerator.Standard {

    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod){
        String methodName = testMethod.getName();
        return convertToReadableFormat(methodName);
    }
    /*
    We split the method name into parts using underscores as the delimiter. After that, we iterate over each part and append a comma after every part. We use a regular expression to insert spaces before uppercase letters in camelcase words and convert the entire part to lowercase. Capitalize
    the first letter of the first word of the display name and append every part to the result.
    After that, we return the new method name.
    */
    private String convertToReadableFormat(String methodName) {
        StringBuilder displayName = new StringBuilder();

        String[] parts = methodName.split("_");

        for (String part : parts) {
            if (!displayName.isEmpty()){
                displayName.append(", ");
            }
            Matcher matcher = Pattern.compile("([a-z])([A-Z])").matcher(part);
            String readablePart = matcher.replaceAll("$1_$2").toLowerCase();

            if (displayName.isEmpty()) {
                readablePart = readablePart.substring(0, 1).toUpperCase() + readablePart.substring(1);
            }
            displayName.append(readablePart);
        }
        return displayName.toString();
    }
}
