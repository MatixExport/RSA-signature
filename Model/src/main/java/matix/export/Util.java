package matix.export;

import java.math.BigInteger;
import java.util.Random;
import java.security.SecureRandom;

public class Util {

    static public BigInteger gcd(BigInteger a, BigInteger b){
        if(a.equals(b)){
            return a;
        }
        if(a.compareTo(b) < 0){
            return gcd(a,b.subtract(a));
        }
        return gcd(a.subtract(b),b);
    }

//    static public BigInteger full_eucalidian(BigInteger a, BigInteger b){
//        BigInteger temp;
//        BigInteger p_2,p_1,Q_2,Q_1,p_current,Q_current;
//        p_2 = BigInteger.ZERO;
//        p_1 = BigInteger.ONE;
//        Q_2 = BigInteger.ONE;
//        Q_1 = BigInteger.ZERO;
//       while(!b.equals(BigInteger.ZERO)){
//           temp = a;
//           a = b;
//           b = temp.mod(b);
//           BigInteger q = temp.subtract(b).divide(a);
//            if(b.compareTo(a) > 0){
//                temp = a;
//                a = b;
//                b = temp;
//            }
//
//
//            p_current = q.multiply(p_1).add(p_2);
//            Q_current = q.multiply(Q_1).add(Q_2);
//            Q_2 = Q_1;
//            p_2 = p_1;
//            Q_1 = Q_current;
//            p_1 = p_current;
//            System.out.println(q);
//            System.out.println(p_current);
//            System.out.println(Q_current);
//            System.out.println("---------------");
//
//       }
//       return a;
//    }

    static public BigInteger modularInverse(BigInteger a, BigInteger b){
        BigInteger temp;
        BigInteger Q_2,Q_1,Q_current;
        Q_2 = BigInteger.ONE;
        Q_1 = BigInteger.ZERO;
        int i =1;
        while(!b.equals(BigInteger.ZERO)){
            temp = a;
            a = b;
            b = temp.mod(b);
            BigInteger q = temp.subtract(b).divide(a);
            if(b.compareTo(a) > 0){
                temp = a;
                a = b;
                b = temp;
            }
            Q_current = q.multiply(Q_1).add(Q_2);
            Q_2 = Q_1;
            Q_1 = Q_current;
            i++;
        }
        return BigInteger.ONE.negate().pow(i-1).multiply(Q_2);
    }
    static public BigInteger toPositiveMod(BigInteger integer,BigInteger mod){
        if(integer.compareTo(BigInteger.ZERO) >= 0){
            return integer;
        }
        return integer.mod(mod).add(mod).mod(mod);
    }

    static public BigInteger totient(BigInteger n){
        BigInteger count = BigInteger.valueOf(0);
        for (int i = 1; n.compareTo(BigInteger.valueOf(i)) > 0; i++) {
            if(gcd(BigInteger.valueOf(i),n).intValue() == 1){
                count = count.add(BigInteger.valueOf(i));
            }
        }
        return count;
    }

    static public BigInteger getRandomBigInteger(BigInteger limit){
        Random generator = new SecureRandom();
        BigInteger randomNumber;
        do {
            randomNumber = new BigInteger(limit.bitLength(), generator);
        } while (randomNumber.compareTo(limit) >= 0);
        return randomNumber;
    }


    static public long getRandomNumber(long min, long max) {
        Random random = new SecureRandom();
        return random.nextLong(max - min) + min;
    }

    static boolean isPrime(long n)
    {

        if (n <= 1)
            return false;

        else if (n == 2)
            return true;

        else if (n % 2 == 0)
            return false;

        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    static public long getRandomPrimeNumber(long min, long max){
        long randomNumber = 0;
        do{
            randomNumber = getRandomNumber(min,max);
        }while(!isPrime(randomNumber));
        return randomNumber;
    }
}
