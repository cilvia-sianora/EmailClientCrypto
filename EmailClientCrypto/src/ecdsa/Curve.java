package ecdsa;

import java.util.ArrayList;
import com.google.common.math.LongMath;
import java.math.BigInteger;
import java.math.RoundingMode;


/**
 *
 * @author 
 */
public class Curve {
    //y^2 = x^3+ax+b
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private BigInteger a;       //Curve parameter
    private BigInteger b;       //Curve parameter
    private BigInteger p;       //Curve modulus prime
    private BigInteger nEFp;    //The number of points on elliptic curve
    private Point G;            //the generator point, a point on the elliptic curve chosen for cryptographic operations
    private BigInteger n;       //Order of Point G
    private String mode;        //The defined mode of curve
    public ArrayList<Point> ellipticGroup;

    public Curve(String mode) {
        this.mode = mode; 
        switch(mode){
            case "P-256": // E : y2 = x3 - 3x + b (mod p)
                a = BigInteger.valueOf(-3);
                b = new BigInteger("5ac635d8aa3a93e7b3ebbd55769886bc651d06b0cc53b0f63bce3c3e27d2604b", 16);
                p = new BigInteger("115792089210356248762697446949407573530086143415290314195533631308867097853951");
                G = new Point(new BigInteger("6b17d1f2e12c4247f8bce6e563a440f277037d812deb33a0f4a13945d898c296", 16), 
                            new BigInteger("4fe342e2fe1a7f9b8ee7eb4a7c0f9e162bce33576b315ececbb6406837bf51f5", 16),
                            a, p);
                n = new BigInteger("115792089210356248762697446949407573529996955224135760342422259061068512044369");
                break;
            case "P-192": // E : y2 = x3 - 3x + b (mod p)
                a = BigInteger.valueOf(-3);
                b = new BigInteger("64210519e59c80e70fa7e9ab72243049feb8deecc146b9b1", 16);
                p = new BigInteger("6277101735386680763835789423207666416083908700390324961279");
                G = new Point(new BigInteger("188da80eb03090f67cbf20eb43a18800f4ff0afd82ff1012", 16), 
                            new BigInteger("07192b95ffc8da78631011ed6b24cdd573f977a11e794811", 16),
                            a, p);
                n = new BigInteger("6277101735386680763835789423176059013767194773182842284081");
                break;                
            default:        
                break;
        }
    }
    
    public BigInteger getA() {
        return a;
    }

    public BigInteger getP() {
        return p;
    }
    
    public Point getG(){
        return G;
    }
    
    public BigInteger getN(){
        return n;
    }
    
    private void setG(){
        
    }
    
    //Find all elliptic group of equation y^2 = x^3+ax+b
    private void setEllipticGroup(){
       ellipticGroup = new ArrayList<>();
       BigInteger y2, aCongruence, y, y3;
       BigInteger x = BigInteger.ZERO;
       n = BigInteger.ZERO;
       while (x.compareTo(this.p.subtract(BigInteger.ONE)) <= 0){
            y2 = x.pow(3).add(this.a.multiply(x)).add(this.b);
            aCongruence = y2.mod(this.p);
            for (BigInteger j = BigInteger.ONE; j.compareTo(this.p.subtract(BigInteger.ONE)) <= 0; j = j.add(BigInteger.ONE)){
                y3 = this.p.multiply(j).add(aCongruence);
                if (isPerfectSquare(y3)) {
                    y = sqrt(y3);
                    Point po = new Point(x,y,a,p);
                    if (!isPointInGroup(po)){
                        ellipticGroup.add(po);
                        n = n.add(BigInteger.ONE);
                    }
                    Point pp = new Point(x, this.p.subtract(y), a, p);
                    if (!isPointInGroup(pp)) {
                        ellipticGroup.add(pp);
                        n = n.add(BigInteger.ONE);
                    }
                    
                }
            }
            x = x.add(BigInteger.ONE);
       }
    }
    
    private boolean isPointInGroup(Point p){
        boolean found = false;
        int i = 0;
        while (!found && i<ellipticGroup.size()) {
            if (p.isEqual(ellipticGroup.get(i))) {
                found = true;
            }
            i++;
        }
        return found;
    }
    
    private boolean isPerfectSquare(BigInteger n){
        BigInteger sqrt = sqrt(n);
        return isSqrt(n, sqrt);
    }

    /**
     * Computes the integer square root of a number.
     *
     * @param n  The number.
     *
     * @return  The integer square root, i.e. the largest number whose square
     *     doesn't exceed n.
     */
    private BigInteger sqrt(BigInteger n){
        if (n.signum() >= 0)
        {
            final int bitLength = n.bitLength();
            BigInteger root = BigInteger.ONE.shiftLeft(bitLength / 2);

            while (!isSqrt(n, root))
            {
                root = root.add(n.divide(root)).divide(TWO);
            }
            return root;
        }
        else
        {
            throw new ArithmeticException("square root of negative number");
        }
    }

    private boolean isSqrt(BigInteger n, BigInteger root){
        final BigInteger lowerBound = root.pow(2);
        final BigInteger upperBound = root.add(BigInteger.ONE).pow(2);
        return lowerBound.compareTo(n) <= 0
            && n.compareTo(upperBound) < 0;
    }
}