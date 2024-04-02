package matix.export.Data;

import java.math.BigInteger;

public class RsaPrivateKey {
    private final BigInteger n;
    private final BigInteger d;

    public RsaPrivateKey(BigInteger n, BigInteger d) {
        this.n = n;
        this.d = d;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getD() {
        return d;
    }
}
