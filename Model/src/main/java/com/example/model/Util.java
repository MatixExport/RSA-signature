package com.example.model;

import java.math.BigInteger;
import java.util.Random;
import java.security.SecureRandom;

public class Util {

    static public BigInteger toPositiveMod(BigInteger integer,BigInteger mod){
        if(integer.compareTo(BigInteger.ZERO) >= 0){
            return integer;
        }
        return integer.mod(mod).add(mod).mod(mod);
    }

    static public BigInteger getRandomBigInteger(BigInteger limit){
        Random generator = new SecureRandom();
        BigInteger randomNumber;
        do {
            randomNumber = new BigInteger(limit.bitLength(), generator);
        } while (randomNumber.compareTo(limit) >= 0);
        return randomNumber;
    }


    static public BigInteger getRandomBigIntegerPrimeNumber(int len){
        Random random = new SecureRandom();
        return BigInteger.probablePrime(len,random);
    }
    public static byte[] joinTables(byte[] in1, byte[] in2) {
        byte[] output = new byte[in1.length + in2.length];
        System.arraycopy(in1, 0, output, 0, in1.length);
        System.arraycopy(in2, 0, output, in1.length, in2.length);
        return output;
    }
    public static byte[] bigIntegerArrayToByteArray(BigInteger[] bigIntegers){
        byte[] bytes = {};
        for (BigInteger i:bigIntegers) {
            bytes = joinTables(bytes,i.toByteArray());
        }
        return bytes;
    }







}
