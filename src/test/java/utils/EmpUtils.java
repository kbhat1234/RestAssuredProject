package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class EmpUtils {

	public static String name() {
		String generatedString = RandomStringUtils.randomAlphabetic(4);
		return ("Karthik " + generatedString);
	}
	
	public static String job() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return (generatedString);
	}
}