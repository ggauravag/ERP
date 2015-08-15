package com.dbt.support;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESCrypto {
	private static final String ALGO = "AES";
	private static final byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B',
			'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

	/*
	 * public static void main(String args[]) {
	 * 
	 * String encrypted = AESCrypto.encrypt("nitin");
	 * System.out.println("Encrypted Text : "+encrypted);
	 * 
	 * 
	 * System.out.println("Decrypted Text : "+decrypt(encrypted)); }
	 */

	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGO);
		return key;
	}

	public static String encrypt(String Data) {
		String encryptedValue = "";
		try {
			Key key = generateKey();
			Cipher c = Cipher.getInstance(ALGO);
			c.init(Cipher.ENCRYPT_MODE, key);
			byte[] encVal = c.doFinal(Data.getBytes());
			encryptedValue = new BASE64Encoder().encode(encVal);
		}

		catch (Exception e) {
			Email.sendExceptionReport(e);
		}
		return encryptedValue;
	}

	public static String decrypt(String encryptedData) {
		String decryptedValue = "";
		try {
			Key key = generateKey();
			Cipher c = Cipher.getInstance(ALGO);
			c.init(Cipher.DECRYPT_MODE, key);
			byte[] decordedValue = new BASE64Decoder()
					.decodeBuffer(encryptedData);
			byte[] decValue = c.doFinal(decordedValue);
			decryptedValue = new String(decValue);
		}

		catch (Exception e) {
			Email.sendExceptionReport(e);
		}

		return decryptedValue;
	}

}
