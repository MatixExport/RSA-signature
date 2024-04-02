package matix.export;
import java.math.BigInteger;
import java.net.URI;
import java.sql.Array;

import matix.export.Util;

public class RsaEncoder {

    public static BigInteger[] generateKeys(){
        long p = Util.getRandomPrimeNumber(0,9999999);
        long q = Util.getRandomPrimeNumber(0,9999999);

        BigInteger n = BigInteger.valueOf(p);
        n = n.multiply(BigInteger.valueOf(q));

        //BigInteger phi = Util.totient(n);
        BigInteger phi = BigInteger.valueOf(p-1);
        phi = phi.multiply(BigInteger.valueOf(q-1));
        BigInteger e = BigInteger.ZERO;
        do{
            e = Util.getRandomBigInteger(phi);
        }while(!Util.gcd(e,phi).equals(BigInteger.ONE));
//        e = BigInteger.valueOf(1021);
        BigInteger d = Util.toPositiveMod(Util.modularInverse(e,phi), phi);
        return new BigInteger[]{e,n,d};

    }


}
