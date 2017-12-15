package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Crypter {
	public static String crypt (String data){		
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(data.getBytes("UTF-8"));			
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) 
			  hexString.append(String.format("%02X", 0xFF & b));		
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}		
			
	}
}