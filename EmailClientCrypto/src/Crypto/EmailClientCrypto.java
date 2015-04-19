/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypto;

/**
 *
 * @author Anggi
 */
public class EmailClientCrypto {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		SHA1 sha = new SHA1();
		String t = "";
		for(int i=0;i<50;i++){
			t += "A";
		}
		//sha.textInput = t;
		
		
		StringBuilder n1 = new StringBuilder("101101");
		StringBuilder n2 = new StringBuilder("010001");
		
		System.out.println(Integer.toBinaryString(87));
		System.out.println(Integer.toBinaryString(~87));
		System.out.println(sha.and(n1, n2));
		System.out.println(sha.not(n1));
		System.out.println(sha.or(n1,n2));
//		System.out.println("HexToBinary");
//		System.out.println(sha.hexToBinary("52301"));
//		System.out.println(sha.hexToBinary("67452301"));
//		System.out.println(sha.hexToBinary("EFCDAB89"));
		
		System.out.println(t);
		System.out.println("--SHA1--");
		System.out.println(sha.hashing(t));
	}
	
}
