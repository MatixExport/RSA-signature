package matix.export;
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


    public static byte[] getSignature(byte[] msg, BigInteger e,BigInteger n){
        digest.update(msg);
        BigInteger signature = new BigInteger(digest.digest(msg));
        return RsaEncoder.encryptSingleBlock(signature.toByteArray(),e,n).toByteArray();
    }

    public static boolean isSignatureValid(byte[] msg,byte[] send_signature,BigInteger e,BigInteger n){
        BigInteger signature =new BigInteger(1,getSignature(msg,e,n));
        return signature.compareTo(new BigInteger(1,send_signature)) == 0;
    }


}
