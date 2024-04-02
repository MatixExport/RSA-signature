package matix.export;

import matix.export.Data.RsaKeySet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import matix.export.RsaSignature;
import  matix.export.RsaEncoder;

import java.math.BigInteger;

class RsaSignatureTest {

    @Test
    void baseValidSignatureTest() {
        RsaKeySet keys = RsaEncoder.generateKeys(512);
        byte[] msg = {0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110};
        byte[] signature = RsaSignature.getSignature(msg,keys.getPublicKey());
        assertTrue(RsaSignature.isSignatureValid(msg,signature,keys.getPrivateKey()));
    }
    @Test
    void baseInvalidSignatureTest() {
        RsaKeySet keys = RsaEncoder.generateKeys(512);
        byte[] msg = {0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110};
        byte[] signature = RsaSignature.getSignature(msg,keys.getPublicKey());
        msg[0] = 0b01111111;
        assertFalse(RsaSignature.isSignatureValid(msg,signature,keys.getPrivateKey()));
    }

    @Test
    void blindValidSignatureTest() {
        RsaKeySet blindKeys = RsaEncoder.generateKeys(512);
        RsaKeySet signatureKeys = RsaEncoder.generateKeys(512);
        byte[] msg = {0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110};
        byte[] signature = RsaSignature.getBlindSignature(msg,blindKeys.getPublicKey(),signatureKeys.getPublicKey());
        assertTrue(RsaSignature.isBlindSignatureValid(msg,signature,blindKeys.getPublicKey(),signatureKeys.getPrivateKey()));
    }
    @Test
    void blindInvalidSignatureTest() {
        RsaKeySet blindKeys = RsaEncoder.generateKeys(512);
        RsaKeySet signatureKeys = RsaEncoder.generateKeys(512);
        byte[] msg = {0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110,0b00000000,0b01010101,0b00001110};
        byte[] signature = RsaSignature.getBlindSignature(msg,blindKeys.getPublicKey(),signatureKeys.getPublicKey());
        msg[0] = 0b01111111;
        assertFalse(RsaSignature.isBlindSignatureValid(msg,signature,blindKeys.getPublicKey(),signatureKeys.getPrivateKey()));
    }



}