package matix.export;

import matix.export.Data.RsaPrivateKey;
import matix.export.Data.RsaPublicKey;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.*;

class Base64CharsetAdapterTest {

    @Test
    void publicToBase64String() throws NoSuchAlgorithmException, InvalidKeySpecException {
        RsaPublicKey publicKey= RsaEncoder.generateKeys(512).getPublicKey();
        String encodedKey = Base64CharsetAdapter.publicToBase64String(publicKey);
        RsaPublicKey publicKey1 = Base64CharsetAdapter.publicFromBase64String(encodedKey);
        assertEquals(publicKey.getE(),publicKey1.getE());
        assertEquals(publicKey.getN(),publicKey1.getN());
    }
    @Test
    void privateToBase64String() throws NoSuchAlgorithmException, InvalidKeySpecException {
        RsaPrivateKey privateKey= RsaEncoder.generateKeys(512).getPrivateKey();
        String encodedKey = Base64CharsetAdapter.privateToBase64String(privateKey);
        RsaPrivateKey privateKey1 = Base64CharsetAdapter.privateFromBase64String(encodedKey);
        assertEquals(privateKey.getN(),privateKey1.getN());
        assertEquals(privateKey.getD(),privateKey1.getD());
    }
}