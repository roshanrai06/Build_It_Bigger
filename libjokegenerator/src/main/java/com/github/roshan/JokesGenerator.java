package com.github.roshan;

import java.util.Random;

public class JokesGenerator {

    public static String getJokes() {
        return "new jokes " + new Random().nextInt(100);
    }
}
