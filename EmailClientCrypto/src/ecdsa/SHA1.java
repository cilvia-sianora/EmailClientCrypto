package ecdsa;

/**
 *
 * @author Cilvia
 */
public class SHA1 {
	private byte[] textInput;
	private byte[] textProcess;
	private int[] h;
	private int[] kt;
	
	/**
	 * Constructor SHA1 class
	 */
	public SHA1(){
		h = new int[5];
		kt = new int[4];
	}
	
	/**
	 * shift binary string to the left 
	 * @param bits binary string in StringBuilder
	 * @param digit	amount of shifting
	 * @return binary string result in StringBuilder
	 */
	private int shiftBitToLeft(int bits, int digit){
		return (bits << digit) | (bits >>> (32 - digit));
	}
	
	/**
	 * Initialize buffer constants in SHA-1 algorithm
	 */
	private void initBuffers(){
		h[0] = 0x67452301;
		h[1] = 0xEFCDAB89;
		h[2] = 0x98BADCFE;
		h[3] = 0x10325476;
		h[4] = 0xC3D2E1F0;
		kt[0] = 0x5A827999;
		kt[1] = 0x6ED9EBA1;
		kt[2] = 0x8F1BBCDC;
		kt[3] = 0xCA62C1D6; 
	}
	
	/**
	 * Do the padding of message for SHA-1 algorithm
	 * @return message that have been padded
	 */
	private byte[] paddingBits(){
		int remainderLength = textInput.length % 64;
		int paddingLength = 64 - remainderLength;

		// generate the padding
		byte[] pad = new byte[paddingLength];
		pad[0] = (byte) 0x80; // 1 for the first bit
		long messageLength = textInput.length * 8;
		for (int i = 0; i < 8; i++) {
			pad[paddingLength - 1 - i] = (byte) ((messageLength >> (8 * i)) & 0x00000000000000FF);
		}

		// append the message and padding
		byte[] result = new byte[textInput.length + paddingLength];
		System.arraycopy(textInput, 0, result, 0, textInput.length);
		System.arraycopy(pad, 0, result, textInput.length, pad.length);

		return result;
	}
	
	/**
	 * Do the hashing with SHA-1 algorithm
	 * @param text message input
	 * @return string result of hash
	 */
	public String hashing(String text){
		byte[] chunk = new byte[64];
		int a,b,c,d,e,f,k,temp;
		int[] word = new int[80];
		
		// Pre processing
		textInput = text.getBytes();
		initBuffers();
		textProcess = paddingBits();

		// for each 512-bit chunk
		for (int j = 0; j < textProcess.length/64; j++) {
			// get the 512-bit chunk
			System.arraycopy(textProcess, 64 * j, chunk, 0, 64);
			
			// divide chunk into 16 words of 32-bit
			for(int i=0;i<16;i++){
				int t = 0;
                for (int in = 0; in < 4; in++) {
                    t = (chunk[i * 4 + in] & 0x000000FF) << (24 - in * 8);
                    word[i] = word[i] | t;
                }
			}
			
			for(int i=16;i<80;i++){
				word[i] = shiftBitToLeft(word[i-3] ^ word[i-8] ^ word[i-14] ^ word[i-16],1);
			}
			
			// initialize hash value
			a = h[0];
			b = h[1];
			c = h[2];
			d = h[3];
			e = h[4];
			
			// Main loop
			for(int i=0;i<80;i++){
				if(i>= 0 && i<=19){
					f = (b & c) | ((~b) & d) ;
					k = kt[0];
				} else if(i>= 20 && i<=39){
					f = b ^ c ^ d;
					k = kt[1];
				} else if(i>= 40 && i<=59){
					f = (b & c) | (b & d) | (c & d);
					k = kt[2];
				} else {
					f = b ^ c ^ d;
					k = kt[3];
				}
				
				temp = shiftBitToLeft(a,5) + f + e + k + word[i];
				e = d;
				d = c;
				c = shiftBitToLeft(b,30);
				b = a;
				a = temp;
			}
			
			// add hash to result
			h[0] = h[0] + a;
			h[1] = h[1] + b;
			h[2] = h[2] + c;
			h[3] = h[3] + d;
			h[4] = h[4] + e;
		}
		
		// append for the final result
		String res = "";
		for(int ctr=4;ctr>=0;ctr--){
			res = Integer.toHexString(h[ctr]) + res;
			while(res.length() % 8 != 0){
				res = "0" + res;
			}
		}
		
		return res;
	}
}
