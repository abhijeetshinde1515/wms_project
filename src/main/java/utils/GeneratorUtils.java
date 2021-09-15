package utils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *Generate Unique Names
 */
public class GeneratorUtils {

	public static String generateUniqueId(String prefix) {
		return prefix + "_" + generateUniqueId();
	}

	public static String generateUniqueId() {
		SimpleDateFormat format = new SimpleDateFormat("MMddkkmmssSSS");
		return format.format(new Date());
	}
	
	public static String generateUniqueEmail() {
		return "email_" + generateUniqueId() + "@test.com";
	}
	
	public static String generateUniqueMobileNumber() {
		SimpleDateFormat format = new SimpleDateFormat("MMddkkmmss");
		return format.format(new Date());
	}
	
	public static String generateTodaysDate() {
		SimpleDateFormat format = new SimpleDateFormat("YYYY/MM/DD");
		return format.format(new Date());
	}
}
