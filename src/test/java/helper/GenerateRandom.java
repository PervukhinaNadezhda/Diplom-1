package helper;

import com.github.javafaker.Faker;

import java.util.Random;

public class GenerateRandom {

    public static float generatingRandomFloatBouned(float leftLimit, float rightLimit) {
        return leftLimit + new Random().nextFloat() * (rightLimit - leftLimit);
    }

    public static String generatingRandomFood() {
        Faker faker = new Faker();
        return faker.food().ingredient();
    }
}
