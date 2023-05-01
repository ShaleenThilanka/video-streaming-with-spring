package com.shaliya.springwebfluxvideostreaming.util;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Generator {



    private final Random RANDOM = new Random();
    private final String NUMERIC = "0123456789";
    public final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";



    public Long generateDigits(int length) {
        return generateRandomDigits(length);
    }

    private Long generateRandomDigits(int length) {
        StringBuilder returnValue = new StringBuilder(length);


        for (int i = 0; i < length; i++) {
            returnValue.append(NUMERIC.charAt(RANDOM.nextInt(NUMERIC.length())));
        }
        return Long.parseLong(returnValue.toString());
    }


}
