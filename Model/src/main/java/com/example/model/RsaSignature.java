package com.example.model;
import com.example.model.Data.RsaPrivateKey;
import com.example.model.Data.RsaPublicKey;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RsaSignature {
    private static final MessageDigest digest;
    static {
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    public static byte[] getSignature(byte[] msg, RsaPublicKey publicKey){
        digest.update(msg);
        BigInteger signature = new BigInteger(1,digest.digest());
        return RsaEncoder.encryptSingleBlock(signature.toByteArray(),publicKey).toByteArray();
    }

    public static boolean isSignatureValid(byte[] msg,byte[] sendSignature, RsaPrivateKey privateKey){
        digest.update(msg);
        BigInteger decryptedSignature = RsaEncoder.decryptSingleBlock(sendSignature,privateKey);
        return decryptedSignature.compareTo(new BigInteger(1,digest.digest())) == 0;
    }

    public static byte[] getBlindMsg(byte[] msg,RsaPublicKey publicKey){
        return Util.bigIntegerArrayToByteArray(RsaEncoder.encrypt(msg,publicKey));
    }

    public static byte[] getBlindSignature(byte[] msg,RsaPublicKey blindKey,RsaPublicKey signatureKey){
        return getSignature(getBlindMsg(msg,blindKey),signatureKey);
    }

    public static boolean isBlindSignatureValid(byte[] msg,byte[] sendSignature,RsaPublicKey blindKey,RsaPrivateKey signatureKey){
        return isSignatureValid(getBlindMsg(msg,blindKey),sendSignature,signatureKey);
    }




}
