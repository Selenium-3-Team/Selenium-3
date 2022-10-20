package core.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * This class create to contains all methods which support for random
 */
public class RandomHelper {

	/**
	 * Get random string based on given length
	 * 
	 * @param length - a number of integer for string length
	 * @return a random string
	 */
	public static String randomString(int length) {
		return RandomStringUtils.random(length, true, false);
	}

	/**
	 * Get random string with a prefix
	 * 
	 * @param prefix - a string of prefix
	 * @return a random string
	 */
	public static String getRandomString(String prefix) {
		return prefix.concat(new SimpleDateFormat("ddMMyyyyHHmmssSSS").format(new Date()));
	}

	/**
	 * Get random string based on combination between upper/lower case alpha and
	 * numbers
	 * 
	 * @return a random string
	 */
	public static String randomString() {
		String letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String number = "0123456789";
		String string = "";
		Random rd = new Random();
		int indx = getRandomNumber(6, 20);

		for (int i = 0; i < indx; i++) {
			int kt = rd.nextInt(2);
			if (kt == 0)// string will have letter(s)
			{
				int lt = rd.nextInt(52);
				string += Character.toString(letter.charAt(lt));
			} else // password will have number(s)
			{
				int lt = rd.nextInt(9);
				string += Character.toString(number.charAt(lt));
			}
		}
		return string;
	}

	/**
	 * Get random string with special characters based on given length
	 * 
	 * @param length - a number of integer for string length
	 * @return a random string
	 */
	public static String randomSpecialString(int length) {
		String specialCharacters = "~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
		String result = RandomStringUtils.random(length, specialCharacters);
		return result;
	}

	/**
	 * Get a random number with the upper bound (exclusive) must be positive
	 * 
	 * @param upperRange - a positive number of bound
	 * @return a number
	 */
	public static int getRandomNumber(int upperRange) {
		Random random = new Random();
		return random.nextInt(upperRange);
	}

	/**
	 * Get a random number
	 * 
	 * @return a number
	 */
	public static int randomNumbers() {
		Random random = new Random();
		return random.nextInt();
	}

	/**
	 * Get a random number based on given range
	 * 
	 * @param min - a min number
	 * @param max - a max number
	 * @return a number
	 */
	public static int getRandomNumber(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	/**
	 * Get date with given format
	 * 
	 * @param format - a string of format (dd-mm-yyyy,...)
	 * @return date
	 */
	public static String getDateNow(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = new Date();
		return formatter.format(date);
	}

}