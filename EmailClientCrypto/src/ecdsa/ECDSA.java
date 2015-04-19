/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdsa;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author User
 */
public class ECDSA {
    private BigInteger dA;    //private key of sender 'A', a randomly selected int less than n
    private BigInteger n;     //the order of the curve
    private Curve curve;    //the elliptic curve
    private Point G;        //the generator point, an elliptic curve domain parameter
    private Point QA;    //public key of sender 'A'
    
    //Default constructor
    public ECDSA() {
        dA = BigInteger.ZERO;
        curve = new Curve("P-256");
        n = curve.getN();
        G = curve.getG();
        QA = new Point();
    }
    
    //Generate private key and public key
    public void generateKey(){
        dA = randomBigInteger(n.subtract(BigInteger.ONE));
        QA = G.multiplication(this.dA);        
    }
    
     //Generate public key
    public void generatePubKey(){
        QA = G.multiplication(this.dA);
    }

    //Getter for attribute QA
    public Point getQA() {
        return QA;
    }
    
    //Setter for attribute QA
    public void setQA(Point QA){
        this.QA = QA;
    }
    
    //Getter for attribute dA
    public BigInteger getdA() {
        return dA;
    }
    
    //Setter for attribute dA
    public void setdA(BigInteger dA){
        this.dA = dA;
    }
    
    //BigInteger random generator in closed set [1, n]
    private BigInteger randomBigInteger(BigInteger n) {
        Random rnd = new Random();
        int maxNumBitLength = n.bitLength();
        BigInteger aRandomBigInt;
        do {
            aRandomBigInt = new BigInteger(maxNumBitLength, rnd);
            // compare random number lessthan ginven number
        } while (aRandomBigInt.compareTo(n) > 0); 
        return aRandomBigInt;
    }
    
    //For signing a message m by sender A, using A’s private key dA
    //Returns signature in hex string representation
    public String signingMessage(String m) throws Exception{
        Point signPoint = signatureGeneration(m);
        String signPointString = signPoint.toHexString();
        return signPointString;
    }
    
    //For checking A's signature in message m. Signature is in hex string representation
    //Returns true if the signature is valid, returns false if it is invalid
    public boolean checkSignature(String m, String signature){
        int len = signature.length();
        Point signPoint = new Point();
        signPoint.setX(new BigInteger(signature.substring(0, len/2), 16));
        signPoint.setY(new BigInteger(signature.substring(len/2), 16));      
        return signatureVerification(m, signPoint); 
    }
    
    //For generating a signature using private key dA on message m
    //Returns signature in point representation
    private Point signatureGeneration(String m){ 
        BigInteger e, k, r, s = BigInteger.ZERO;
        SHA1 hash = new SHA1();
        e = new BigInteger(hash.hashing(m), 16);
        Point x1y1 = new Point();
        Random rand = new Random();
        do{
            k = randomBigInteger(n.subtract(BigInteger.ONE));
            x1y1 = G.multiplication(k);
            r = x1y1.getX().mod(n);
            if (! (r.compareTo(BigInteger.ZERO) == 0)){
                if (k.gcd(n).compareTo(BigInteger.ONE) == 0){
                    BigInteger temp = k.modInverse(n);
                    s = (temp.multiply((dA.multiply(r)).add(e))).mod(n);
                }
            }
        } while ((r.compareTo(BigInteger.ZERO) == 0) || (s.compareTo(BigInteger.ZERO) == 0));
        Point signature = new Point();
        signature.setX(r);
        signature.setY(s);
        return signature;
    }
    
    //Authenticate A's point signature
    //Returns true if the signature is valid, returns false if it is invalid
    private boolean signatureVerification(String m, Point signature){
        BigInteger r = signature.getX();
        BigInteger s = signature.getY();
        BigInteger e, w, u1, u2;
        if ((r.compareTo(BigInteger.ONE) >= 0) && 
            (r.compareTo(n.subtract(BigInteger.ONE)) <= 0) && 
            (s.compareTo(BigInteger.ONE) >= 0) && 
            (s.compareTo(n.subtract(BigInteger.ONE)) <= 0)){
            SHA1 hash = new SHA1();
            e = new BigInteger(hash.hashing(m), 16);
            w = s.modInverse(n);
            u1 = (e.multiply(w)).mod(n);
            u2 = (r.multiply(w)).mod(n);
            Point x1y1 = new Point();
            x1y1 = (G.multiplication(u1)).addition(QA.multiplication(u2));
            if ((x1y1.getX().mod(n)).compareTo(r.mod(n)) == 0){
                return true;
            } else {
                System.out.println("x1 = " + x1y1.getX().mod(n) + " | " + "r(mod n) = " + r.mod(n));
                return false;
            }
        } else {
            return false;
        }
    }
}
