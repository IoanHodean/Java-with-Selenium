package util;

import java.util.Random;

public class TestUtil {
    public static String generateRandomEmail(){
        Random random = new Random();
        String email = "johndoe" + random.nextLong() + "@email.com";
        System.out.println("Used email: " + email);
        return email;
    }
}
