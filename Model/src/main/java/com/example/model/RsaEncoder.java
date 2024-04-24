package com.example.model;
import java.math.BigInteger;
import java.util.Arrays;

import com.example.model.Data.RsaKeySet;
import com.example.model.Data.RsaPrivateKey;
import com.example.model.Data.RsaPublicKey;

public class RsaEncoder {

    public static RsaKeySet generateKeys(int keyLen){
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
        return new RsaKeySet(e,n,d);

    }
    public static BigInteger[] encrypt(byte[] msg, RsaPublicKey publicKey){
        int bytes_per_block = (publicKey.getN().bitLength()-1)/8;
        int blocks = (int) Math.ceil((double) msg.length /bytes_per_block);
        BigInteger[] cipher = new BigInteger[blocks];
        for (int i = 0; i < blocks; i++) {
            byte[] bytes = Arrays.copyOfRange(msg, bytes_per_block * i,bytes_per_block*(i+1));
            cipher[i] = encryptSingleBlock(bytes,publicKey);
        }
        return cipher;
    }

    public static BigInteger encryptSingleBlock(byte[] msg,RsaPublicKey publicKey){
        BigInteger cipher =  new BigInteger(1,msg);
        return cipher.modPow(publicKey.getE(),publicKey.getN());
    }
    public static BigInteger decryptSingleBlock(byte[] cipher, RsaPrivateKey privateKey){
        BigInteger deciphered = new BigInteger(1,cipher);
        return deciphered.modPow(privateKey.getD(),privateKey.getN());
    }


}
