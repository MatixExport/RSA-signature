package matix.export;

import static org.junit.jupiter.api.Assertions.*;

import matix.export.Data.RsaKeySet;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

class RsaEncoderTest {

    @Test
    void gdcTest(){
        System.out.println(Util.modularInverse(BigInteger.valueOf(8),BigInteger.valueOf(35)));
        System.out.println(Util.toPositiveMod(Util.modularInverse(BigInteger.valueOf(8),BigInteger.valueOf(35)), BigInteger.valueOf(35)));

    }
    @Test
    void encryptTest(){
        long p =53;
        long q = 67;
        //{e,n,d}
        RsaKeySet keys = RsaEncoder.generateKeys(512);
        byte[] msg = {0b00000000,0b01010101,0b00001110};
        BigInteger[] cipher = RsaEncoder.encrypt(msg,keys.getPublicKey());
        BigInteger deciphered = RsaEncoder.decryptSingleBlock(cipher[0].toByteArray(),keys.getPrivateKey());
        System.out.println((deciphered));

    }
    @Test
    void singleBlockCipherTest(){
        RsaKeySet keys = RsaEncoder.generateKeys(512);
        byte[] msg = {0b00000000,0b01010101,0b00001110};
        BigInteger original_msg =  new BigInteger(1,msg);
        BigInteger cipher = RsaEncoder.encryptSingleBlock(msg,keys.getPublicKey());
        assertEquals(original_msg,RsaEncoder.decryptSingleBlock(cipher.toByteArray(),keys.getPrivateKey()));
    }





}