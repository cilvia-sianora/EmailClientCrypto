/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CipherBlock;

/**
 *
 * @author 
 */
public class Kristik {
    /*CLASS ATTRIBUTES*/
    private String key;         //the key string
    private int blockLen;       //size of block, number of char in a block
    
    //Default constructor
    public Kristik(){
        key = "";
        blockLen = key.length();
    }
    
    //Setter for key atribute   
    public void setKey(String s){
        this.key = s;
        this.blockLen = s.length();
    }
    
    //Parsing a character into hexadecimal 
    private int[] charToHexa(char c){
        int[] hex = new int[2];
        String h = Integer.toHexString((int) c);
        if (h.length() == 1){
            h = "0" + h;
        }
        hex[0] = Integer.parseInt(h.substring(0,1), 16);
        hex[1] = Integer.parseInt(h.substring(1), 16);
        return hex;
    }
    
    //Parsing hexadecimal into a character
    private char hexaToChar(int[] i){
        char c;
        String h = "" + Integer.toHexString(i[0]) + Integer.toHexString(i[1]);
        c = (char) Integer.parseInt(h, 16);
        return c;
    }
        
    /*Inner function*/
    private int encInnerFunction(int r, int k){
        return (r+k)%16;
    }
    
    private int decInnerFunction(int r, int k){
        return (r+16-k)%16;
    }
    
    /*Feistel*/
    private int[] encFeistel(int[] lr, int k){
        int[] result = new int[2];
        result[0] = lr[1];
        result[1] = encInnerFunction(lr[1], k);
        result[1] = result[1] ^ lr[0];
        return result;
    }
    
    private int[] decFeistel(int[] lr, int k){
        int[] result = new int[2];
        result[1] = lr[0];
        result[0] = encInnerFunction(lr[0], k);
        result[0] = result[0] ^ lr[1];
        return result;
    }
    
    /*Enkripsi*/
    private String encryptor(String plainBlock){
        int n = blockLen;
        String cipher = "";
        int[] innerKey = new int[n];
        for (int i=0; i<n; i++){
            innerKey[i] = charToHexa(key.charAt(i/2))[i%2];
        }
        for (char c: plainBlock.toCharArray()){
            int[] lr = charToHexa(c);
            for (int i=0; i<n; i++){
                lr = encFeistel(lr, innerKey[i]);
                innerKey[i] = lr[0];
            }
            cipher += hexaToChar(lr);
        }
        return cipher;
    }
    
    /*Dekripsi*/
    private String decryptor(String cipherBlock){
        int n = blockLen;     //number of iterations
        String plain = "";
        int[] innerKey = new int[n];
        for (int i=0; i<n; i++){
            innerKey[n-i-1] = charToHexa(key.charAt(i/2))[i%2];
        }
        for (char c: cipherBlock.toCharArray()){
            int[] lr = charToHexa(c);
            for (int i=0; i<n; i++){
                int tmp = lr[0];
                lr = decFeistel(lr, innerKey[i]);
                innerKey[i] = tmp;
            }
            plain += hexaToChar(lr);
        }
        return plain;
    }
    
    /* ECB Mode */
    public String encECB(String plaintext){
        String ciphertext = "";
        //Insert text addtion into plaintext
        int len = plaintext.length();
        if (len % blockLen != 0){
            String str = new Character((char) Integer.parseInt("11111111", 2)).toString();
            for (int i=len%blockLen; i<blockLen; i++){
                plaintext += str;
                len++;
            }
        }
        //Main process
        for (int i=0; i<len; i+=blockLen){
            String plainBlock = plaintext.substring(i, i+blockLen);
            ciphertext += encryptor(plainBlock);
        }
        return ciphertext;
    }
    
    public String decECB(String ciphertext){
        String plaintext = "";
        //Insert text addtion into plaintext
        int len = ciphertext.length();
        if (len % blockLen != 0){
            String str = new Character((char) Integer.parseInt("11111111", 2)).toString();
            for (int i=len%blockLen; i<blockLen; i++){
                ciphertext += str;
                len++;
            }
        }
        //Main process
        for (int i=0; i<len; i+=blockLen){
            String cipherBlock = ciphertext.substring(i, i+blockLen);
            plaintext += decryptor(cipherBlock);
        }
        return plaintext;
    }
}
