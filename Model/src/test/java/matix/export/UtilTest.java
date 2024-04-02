package matix.export;

import static org.junit.jupiter.api.Assertions.*;
import matix.export.Util;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigInteger;
import java.util.Arrays;

import  matix.export.RsaEncoder;

class UtilTest {

    @Test
    void gdcTest(){
        System.out.println(Util.modularInverse(BigInteger.valueOf(8),BigInteger.valueOf(35)));
        System.out.println(Util.toPositiveMod(Util.modularInverse(BigInteger.valueOf(8),BigInteger.valueOf(35)), BigInteger.valueOf(35)));

    }
    @Test
    void keyGenerationTest(){
        long p =53;
        long q = 67;
        System.out.println(Arrays.toString(RsaEncoder.generateKeys()));


    }




}