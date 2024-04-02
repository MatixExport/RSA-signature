package matix.export;
import java.math.BigInteger;
import java.net.URI;
import java.sql.Array;
import java.util.Arrays;

import matix.export.Util;

public class RsaEncoder {

    public static BigInteger[] generateKeys(int keyLen){
        BigInteger p,q;
        p = Util.getRandomBigIntegerPrimeNumber(keyLen);
        q = Util.getRandomBigIntegerPrimeNumber(keyLen);
        BigInteger n = p.multiply(q);

        BigInteger phi = p.subtract(BigInteger.ONE);
        phi = phi.multiply(q.subtract(BigInteger.ONE));
        BigInteger e = BigInteger.ZERO;
        do{
            e = Util.getRandomBigInteger(phi);
        }while(!e.gcd(phi).equals(BigInteger.ONE));
        BigInteger d = Util.toPositiveMod(Util.modularInverse(e,phi), phi);
        return new BigInteger[]{e,n,d};

    }
    public static BigInteger[] encrypt(byte[] msg,BigInteger e,BigInteger n){
        int bytes_per_block = (n.bitLength()-1)/8;
        int blocks = (int) Math.ceil((double) msg.length /bytes_per_block);
        BigInteger[] cipher = new BigInteger[blocks];
        for (int i = 0; i < blocks; i++) {
            byte[] bytes = Arrays.copyOfRange(msg, bytes_per_block * i,bytes_per_block*(i+1));
            cipher[i] = new BigInteger(1,bytes);
            cipher[i] = cipher[i].modPow(e,n);
        }
        return cipher;
    }

    public static BigInteger encryptSingleBlock(byte[] msg,BigInteger e,BigInteger n){
        BigInteger cipher =  new BigInteger(1,msg);
        return cipher.modPow(e,n);
    }
    public static BigInteger decryptSingleBlock(byte[] cipher,BigInteger n,BigInteger d){
        BigInteger deciphered = new BigInteger(1,cipher);
        return deciphered.modPow(d,n);
    }


}
