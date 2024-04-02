package matix.export.Data;

import java.math.BigInteger;

public class RsaKeySet {
    private final RsaPrivateKey privateKey;
    private final RsaPublicKey publicKey;

    public RsaKeySet(BigInteger e,BigInteger n,BigInteger d) {
        this.privateKey = new RsaPrivateKey(n,d);
        this.publicKey = new RsaPublicKey(n,e);
    }

    public RsaPrivateKey getPrivateKey() {
        return privateKey;
    }

    public RsaPublicKey getPublicKey() {
        return publicKey;
    }
}
