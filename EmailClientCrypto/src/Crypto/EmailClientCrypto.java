/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypto;

import ecdsa.SHA1;

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
		String t = "A";
//		for(int i=0;i<50;i++){
//			t += "A";
//		}
		//sha.textInput = t;
		int tes = 0x7fffffff + 0x1;
//		System.out.println(Integer.rotateLeft(4,2));
//		System.out.println(sha.shiftBitToLeft(4,2));
//		System.out.println(0xF ^ 0x1);
		
		StringBuilder n1 = new StringBuilder("101101");
		StringBuilder n2 = new StringBuilder("010001");
		
		int a = 2147483646;
		int b = 3;
		//System.out.println(a-b);
		
		long sum = a + 2;
//		System.out.print(" "+sum);
//		if(sum > Integer.MAX_VALUE){
//			System.out.print(" masuk if");
//			sum -= Integer.MAX_VALUE;
//			System.out.print(" "+sum);
//			sum += Integer.MIN_VALUE - 1;
//		}
//		System.out.println(sum);
//		
//		System.out.println(Integer.MAX_VALUE);
//		System.out.println(Integer.MIN_VALUE);
//		System.out.println(Integer.toHexString(2147483647));
//		System.out.println(Integer.toBinaryString(87));
//		System.out.println(Integer.toBinaryString(~87));
//		System.out.println(sha.and(n1, n2));
//		System.out.println(sha.not(n1));
//		System.out.println(sha.or(n1,n2));
//		System.out.println("HexToBinary");
//		System.out.println(sha.hexToBinary("52301"));
//		System.out.println(sha.hexToBinary("67452301"));
//		System.out.println(sha.hexToBinary("EFCDAB89"));
//		System.out.println(tes);
		int i = 255;
		//System.out.println(Integer.parseInt("255",2));
		
		System.out.println(t);
		System.out.println("--SHA1--");
		System.out.println(sha.hashing(t));
	}
	
}
