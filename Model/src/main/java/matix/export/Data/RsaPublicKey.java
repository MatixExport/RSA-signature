package matix.export.Data;

import java.math.BigInteger;

public class RsaPublicKey {
    private final BigInteger n;
    private final BigInteger e;

    public RsaPublicKey(BigInteger n, BigInteger e) {
        this.n = n;
        this.e = e;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getE() {
        return e;
    }
}
