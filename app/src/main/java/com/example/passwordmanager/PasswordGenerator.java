package com.example.passwordmanager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

public class PasswordGenerator {
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String PUNCTUATION = "!@#$%&?";

    private int length;
    private int upperNum;
    private int digitsNum;
    private int punctuationNum;
    private int lowerNum;

    public PasswordGenerator(int length, int upperNum, int digitsNum, int punctuationNum) {
        this.length = length;
        this.upperNum = upperNum;
        this.digitsNum = digitsNum;
        this.punctuationNum = punctuationNum;
        this.lowerNum = length - upperNum - digitsNum - punctuationNum;
    }

    public String getPassword() {
        String password = "";
        for(int i = 0; i < upperNum; i++) {
            password += UPPER.charAt((int) (Math.random() * UPPER.length()));
        }
        for(int i = 0; i < lowerNum; i++) {
            password += LOWER.charAt((int) (Math.random() * LOWER.length()));
        }
        for(int i = 0; i < digitsNum; i++) {
            password += DIGITS.charAt((int) (Math.random() * DIGITS.length()));
        }
        for(int i = 0; i < punctuationNum; i++) {
            password += PUNCTUATION.charAt((int) (Math.random() * PUNCTUATION.length()));
        }
        return password;
    }
}
