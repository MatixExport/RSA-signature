package matix.export;
import matix.export.Data.RsaPublicKey;
import matix.export.RsaEncoder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RsaSignature {
    private static MessageDigest digest;
    static {
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    public static byte[] getSignature(byte[] msg, RsaPublicKey publicKey){
        digest.update(msg);
        BigInteger signature = new BigInteger(digest.digest(msg));
        return RsaEncoder.encryptSingleBlock(signature.toByteArray(),publicKey).toByteArray();
    }

    public static boolean isSignatureValid(byte[] msg,byte[] sendSignature, RsaPublicKey publicKey){
        BigInteger signature =new BigInteger(1,getSignature(msg,publicKey));
        return signature.compareTo(new BigInteger(1,sendSignature)) == 0;
    }


}
