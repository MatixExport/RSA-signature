package com.example.model;

import com.example.model.Data.RsaPrivateKey;
import com.example.model.Data.RsaPublicKey;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;
import java.util.Base64;

public class Base64CharsetAdapter {


    private static String getStartHeader(String name){
        String pad = "-----";
        return pad+"BEGIN "+name+" KEY" + pad;
    }
    private static String getEndHeader(String name){
        return getStartHeader(name).replace("BEGIN","END");
    }

    private static String addHeaders(String data,String name){
//        return getStartHeader(name) + "" + data + "" + getEndHeader(name);
        return data;
    }

    private static String removeHeaders(String data,String name){
        return data.replaceAll("\n","").replace(getStartHeader(name),"").replace(getEndHeader(name),"");
    }

    public static String publicToBase64String(RsaPublicKey publicKey) throws NoSuchAlgorithmException,InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(publicKey.getN(),publicKey.getE());
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(rsaPublicKeySpec);
        return addHeaders(Base64.getEncoder().encodeToString(key.getEncoded()),"PUBLIC");
    }
    public static String privateToBase64String(RsaPrivateKey privateKey) throws NoSuchAlgorithmException,InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(privateKey.getN(),privateKey.getD());
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);
        return addHeaders(Base64.getEncoder().encodeToString(key.getEncoded()),"PRIVATE");
    }

    public static RsaPublicKey publicFromBase64String(String base64key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        base64key = removeHeaders(base64key,"PUBLIC");
        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(base64key));
        RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(keySpecX509);
        return new RsaPublicKey(pubKey.getModulus(),pubKey.getPublicExponent());
    }
    public static RsaPrivateKey privateFromBase64String(String base64key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        base64key = removeHeaders(base64key,"PRIVATE");
        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64key));
        RSAPrivateKey privateKey = (RSAPrivateKey) kf.generatePrivate(keySpecPKCS8);
        return new RsaPrivateKey(privateKey.getModulus(),privateKey.getPrivateExponent());
    }
}
