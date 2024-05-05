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
        return RsaEncoder.encryptSingleBlock(digest.digest(),publicKey).toByteArray();
    }

    public static boolean isSignatureValid(byte[] msg,byte[] receivedSignature, RsaPrivateKey privateKey){
        digest.update(msg);
        BigInteger decryptedSignature = RsaEncoder.decryptSingleBlock(receivedSignature,privateKey);
        return decryptedSignature.compareTo(new BigInteger(1,digest.digest())) == 0;
    }

    public static byte[] getBlindSignature(byte[] msg,RsaPublicKey blindKey,RsaPublicKey signatureKey){
        return getSignature(getBlindMsg(msg,blindKey),signatureKey);
    }

    public static boolean isBlindSignatureValid(byte[] msg,byte[] sendSignature,RsaPublicKey blindKey,RsaPrivateKey signatureKey){
        return isSignatureValid(getBlindMsg(msg,blindKey),sendSignature,signatureKey);
    }

    public static byte[] getBlindMsg(byte[] msg,RsaPublicKey blindKey){
        return Util.bigIntegerArrayToByteArray(RsaEncoder.encrypt(msg, blindKey));
    }





}
