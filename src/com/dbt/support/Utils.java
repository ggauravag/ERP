package com.dbt.support;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Random;

import org.apache.commons.lang3.text.WordUtils;



public class Utils {
	
	
	
	
	static public String customFormat(String pattern, int value) {
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String output = myFormatter.format((double) value);
		return output;
	}
	
	static public String customFormat(int value) {
		DecimalFormat myFormatter = new DecimalFormat("###,###.###");
		String output = myFormatter.format((double) value);
		return output;
	}
	
	static public String customFormat(String pattern, double value) {
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String output = myFormatter.format(value);
		return output;
	}

	private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	private static final int RANDOM_STRING_LENGTH = 8;

	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[@#$%]).{8,20})";
	/*
	    ((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})
	    
	    Explanation - 
		(					# Start of group
		  (?=.*\d)			#   must contains one digit from 0-9
		  (?=.*[a-z])		#   must contains one lowercase characters
		  (?=.*[A-Z])		#   must contains one uppercase characters
		  (?=.*[@#$%])		#   must contains one special symbols in the list "@#$%"
		       .			#   match anything with previous condition checking
		  {6,20}			#   length at least 6 characters and maximum of 20	
		)					# End of group
		
	 */
	
	
	/**
	 * Validate password with regular expression
	 * 
	 * @param password password for validation
	 * @return true valid password, false invalid password
	 */
	/*public static String validatePassword(final String password, String confirmPassword) {		
		Map mapSecureParams = AdminDao.getSecureParameters();
		int passwordLength = Integer.parseInt((String) mapSecureParams.get(Constants.passwordLength));
		String isIncludeSpecialChars = (String) mapSecureParams.get(Constants.isIncludeSpecialChars);
		
		if (password.length() < passwordLength) {
			return "Password length can't be less then : " + passwordLength;
		} else if(confirmPassword != null && !password.equals(confirmPassword)){
			return "Password and Confirm password are not same.";
		}else if("Yes".equals(isIncludeSpecialChars) ){
			Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
			Matcher matcher = pattern.matcher(password);

			if(matcher.matches()== false){
				return "Password must contain atleast one number and one special character(@,#,$,%).";
			}
		}
		return null;
	}*/
	
	public static String getPasswordToken()
	{
		String token = "";
		for(int i = 0; i < 10; i++)
		{
			token += getRandomNumber();
		}
		return getMD5(token);
	}

	public static void main(String[] args) {
		System.out.println(getMD5("7562344545"));
		System.out.println(WordUtils.capitalizeFully("gAURAV"));
	}

	/**
	 * This method generates random string
	 * 
	 * @return
	 */
	public static String getPassword() {

		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int number = getRandomNumber();
			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	/**
	 * This method generates random numbers
	 * 
	 * @return int
	 */
	private static int getRandomNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(CHAR_LIST.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}
	
	
	public static String getOTP(int length)
	{
		String a = "";
		Random random = new Random();
		for(int i = 0 ;i < length; i++)
		{
			a += random.nextInt(9);
		}

		return a;
	}

	public static String getMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);

			// Now we need to zero pad it if you actually want the full 32
			// chars.
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
