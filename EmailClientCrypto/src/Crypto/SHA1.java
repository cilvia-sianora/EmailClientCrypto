package Crypto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cilvia
 */
public class SHA1 {
	public String textInput;
	private StringBuilder textInputSB;
	private StringBuilder textProcess;
	private StringBuilder lengthTextInput;
	private String h0,h1,h2,h3,h4;
	private String[] kt;
	
	public SHA1(){
		kt = new String[4];
	}
	
	/**
	 * change the text into binary string
	 * @param text initial text
	 * @return binary string of text
	 */
	public StringBuilder convertToBinaryString (String text) {
		StringBuilder result = new StringBuilder();
		byte[] b = text.getBytes();
		for (byte b1 : b) {
			int val = b1;
			for (int i = 0; i < 8; i++) {
			   result.append((val & 128) == 0 ? 0 : 1);
			   val <<= 1;
			}
		}
		return result;
	}
	
	// change binary string to text
	public String bitToText(String bit){
		String result = "";
		int ascii;
		String bitProcess;
		while(bit.length() % 8 != 0){
			bit = '0' + bit;
		}
		while(bit.length()>0){
			bitProcess = bit.substring(0,8);
			//System.out.println(bitProcess);
			//ascii = bitToInteger(bitProcess);
			ascii = Integer.parseInt(bitProcess,2);
			result += (char)ascii;
			bit = bit.substring(8);
		}
		return result;
	}
	
	public String hexToBinary(String hex) {
		String result = "";
		String temp;
		int val;
		for(int i=0;i<hex.length();i++){
			val = Integer.parseInt(String.valueOf(hex.charAt(i)),16);
			temp = Integer.toBinaryString(val);
			while(temp.length() < 4){
				temp = "0" + temp;
			}
			result += temp;
		}
		return result;
	}
	
	/**
	 * shift binary string to the left 
	 * @param binaryStr binary string in StringBuilder
	 * @param digit	amount of shifting
	 * @return binary string result in StringBuilder
	 */
	public StringBuilder shiftBitToLeft(StringBuilder binaryStr, int digit){
		StringBuilder result = new StringBuilder();
		result.append(binaryStr.substring(digit,binaryStr.length()));
		result.append(binaryStr.substring(0,digit));
		return result;
	}
	
	/**
	 * do the XOR operation between first and second string
	 * @param string1 the first string
	 * @param string2 the second string
	 * @return result of XOR operation
	 */
	public StringBuilder xor(StringBuilder string1, StringBuilder string2){
		StringBuilder result = new StringBuilder();
		
		// if length of the string2 different
		if(string1.length() > string2.length()){
			do{
				string2.insert(0, '0');
			} while(string2.length()<string1.length());
		} else if (string1.length() < string2.length()){
			do{
				string2.insert(0, '0');
			} while(string2.length()<string1.length());
			
		}
		
		// do the XOR
		for(int i=0;i<string1.length();i++){
			result.append((char) string1.charAt(i) ^ string2.charAt(i));
		}
		
		return result;
	}
	
	/**
	 * do the AND operation between first and second string
	 * @param string1 the first string
	 * @param string2 the second string
	 * @return result of AND operation
	 */
	public StringBuilder and(StringBuilder string1, StringBuilder string2){
		StringBuilder result = new StringBuilder();
		
		int val;
		String temp;
		for(int i=0;i<string1.length();i++){
			val = Integer.parseInt(String.valueOf(string1.charAt(i)),2) & Integer.parseInt(String.valueOf(string2.charAt(i)),2);
			temp = Integer.toBinaryString(val);
			result.append(temp.toString());
		}
		return result;
	}
	
	/**
	 * do the OR operation between first and second string
	 * @param string1 the first string
	 * @param string2 the second string
	 * @return result of OR operation
	 */
	public StringBuilder or(StringBuilder string1, StringBuilder string2){
		StringBuilder result = new StringBuilder();
		
		int val;
		String temp;
		for(int i=0;i<string1.length();i++){
			val = Integer.parseInt(String.valueOf(string1.charAt(i)),2) | Integer.parseInt(String.valueOf(string2.charAt(i)),2);
			temp = Integer.toBinaryString(val);
			result.append(temp.toString());
		}
		
		return result;
	}
	
	/**
	 * do the NOT operation for the string
	 * @param string1 the first string
	 * @return result of NOT operation
	 */
	public StringBuilder not(StringBuilder string1){
		StringBuilder result = new StringBuilder();
		
		for(int i=0;i<string1.length();i++){
			if(string1.charAt(i) == '0'){
				result.append("1");
			} else {
				result.append("0");
			}
		}
		
		return result;
	}
	
	/**
	 * Do the addition operation of binary string
	 * @param binaryStr binary string
	 * @param adder integer to be added to binaryStr
	 * @return binary string result
	 */
	public StringBuilder add(StringBuilder binaryStr, StringBuilder adder){
		StringBuilder result = new StringBuilder();
		int sum = Integer.parseInt(binaryStr.toString(), 2) + Integer.parseInt(adder.toString(), 2);
		if(sum > 255)
			sum -= 256;
		result.append(Integer.toBinaryString(sum));
		while (result.length() < binaryStr.length()){
			result.insert(0,'0');
		}
		return result;
	}
	
	public void initBuffers(){
		h0 = hexToBinary("67452301");
		h1 = hexToBinary("EFCDAB89");
		h2 = hexToBinary("98BADCFE");
		h3 = hexToBinary("10325476");
		h4 = hexToBinary("C3D2E1F0");
		kt[0] = hexToBinary("5A827999");
		kt[1] = hexToBinary("6ED9EBA1");
		kt[2] = hexToBinary("8F1BBCDC");
		kt[3] = hexToBinary("CA62C1D6"); 
	}
	
	public void paddingBits(){
		textInputSB = convertToBinaryString(textInput);
		textProcess = convertToBinaryString(textInput);
		textProcess.append("1");
		while(textProcess.length() % 512 != 448){
			textProcess.append("0");
		}
	}
	
	public void paddingLength(){
		lengthTextInput = new StringBuilder(Integer.toBinaryString(textInputSB.length()));
		while(lengthTextInput.length() < 64){
			lengthTextInput.insert(0,'0');
		}
		textProcess.append(lengthTextInput.substring(32,64));
		textProcess.append(lengthTextInput.substring(0,32));
	}
	
	public String hashing(String text){
		List<StringBuilder> chunks = new ArrayList<>();
		StringBuilder a = new StringBuilder();
		StringBuilder b = new StringBuilder();
		StringBuilder c = new StringBuilder();
		StringBuilder d = new StringBuilder();
		StringBuilder e = new StringBuilder();
		StringBuilder f = new StringBuilder();
		StringBuilder k = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		StringBuilder[] word = new StringBuilder[80];
		for(int i=0;i<80;i++){
			word[i] = new StringBuilder();
		}
		
		// Pre processing
		textInput = text;
		initBuffers();
		paddingBits();
		paddingLength();
		
		// divide into 512-bit chunk
		while(textProcess.length() > 0){
			chunks.add(new StringBuilder(textProcess.substring(0,512)));
			textProcess.delete(0,512);
		}
		
		// for each 512-bit chunk
		for(StringBuilder chunk: chunks){
			// divide chunk into 16 words of 32-bit
			for(int i=0;i<16;i++){
				word[i].append(chunk.substring(0,32));
				chunk.delete(0,32);
			}
			
			for(int i=16;i<80;i++){
				word[i] = shiftBitToLeft(xor(xor(xor(word[i-3],word[i-8]),word[i-14]),word[i-16]),1);
			}
			
			// initialize hash value
			a.append(h0);
			b.append(h1);
			c.append(h2);
			d.append(h3);
			e.append(h4);
			
			// Main loop
			for(int i=0;i<80;i++){
				if(i>= 0 && i<=19){
					f = or(and(b,c),and(not(b),d));
					k.append(kt[0]);
				} else if(i>= 20 && i<=39){
					f = xor(xor(b,c),d);
					k.append(kt[1]);
				} else if(i>= 40 && i<=59){
					f = or(or(and(b,c),and(b,d)),and(c,d));
					k.append(kt[2]);
				} else {
					f = xor(xor(b,c),d);
					k.append(kt[3]);
				}
				
				temp.append(add(add(add(add(shiftBitToLeft(a,5),f),e),k),word[i]));
				e.setLength(0);
				e.append(d);
				d.setLength(0);
				d.append(c);
				c.setLength(0);
				c.append(shiftBitToLeft(b,30));
				b.setLength(0);
				b.append(a);
				a.setLength(0);
				a.append(temp);
			}
			
			// add hash to result
			h0 = add(new StringBuilder(h0),a).toString();
			h1 = add(new StringBuilder(h1),b).toString();
			h2 = add(new StringBuilder(h2),c).toString();
			h3 = add(new StringBuilder(h3),d).toString();
			h4 = add(new StringBuilder(h4),e).toString();
			
			// erase the value in StringBuilder variable
			a.setLength(0);
			b.setLength(0);
			c.setLength(0);
			d.setLength(0);
			e.setLength(0);
			f.setLength(0);
			k.setLength(0);
			temp.setLength(0);
		}
		
		// final result
		return bitToText(h0+h1+h2+h3+h4);
	}
}
