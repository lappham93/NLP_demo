package com.mit.utils;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class StringUtils {

	private static final String EMAIL_PATTERN ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);

	public StringUtils() {
	}

	/**
	 * Validate hex with regular expression
	 *
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public static boolean validateEmail(String email) {
		Matcher matcher = emailPattern.matcher(email);
		return matcher.matches();

	}

	public static List<Integer> stringToList(String listStr, boolean removeZero) {
		String[] parts = listStr.split("\\s*,\\s*");
		List<Integer> list = new ArrayList<Integer>(parts.length);
		for (String part: parts) {
			int num = NumberUtils.toInt(part);
			if (!removeZero || num != 0) {
				list.add(num);
			}
		}
		return list;
	}

	public static List<Long> stringToLongList(String listStr, boolean removeZero) {
		String[] parts = listStr.split("\\s*,\\s*");
		List<Long> list = new ArrayList<Long>(parts.length);
		for (String part: parts) {
			long num = NumberUtils.toLong(part);
			if (!removeZero || num != 0) {
				list.add(num);
			}
		}
		return list;
	}

	public static byte[] hexStringToBytes(String hexString) {
		final byte[] tokenBytes = new byte[hexString.length() / 2];

		for (int i = 0; i < hexString.length(); i += 2) {
			tokenBytes[i / 2] = (byte) (Integer.parseInt(hexString.substring(i, i + 2), 16));
		}

		return tokenBytes;
	}

	public static String bytesToHexString(final byte[] tokenBytes) {

		final StringBuilder builder = new StringBuilder();

		for (final byte b : tokenBytes) {
			final String hexString = Integer.toHexString(b & 0xff);

			if (hexString.length() == 1) {
				// We need a leading zero
				builder.append("0");
			}

			builder.append(hexString);
		}

		return builder.toString();
	}

	public static String buildAddress(String addr, String addr2, String city, String state, String country, String zipCode) {
		StringBuilder builder = new StringBuilder();

		if(addr != null && !addr.isEmpty()) {
			builder.append(addr);
		}

		if(addr2 != null && !addr2.isEmpty()) {
			builder.append(addr2);
		}

		if(city != null && !city.isEmpty()) {
			builder.append(" " + city);
		}

		if(state != null && !state.isEmpty()) {
			builder.append(", " + state);
		}

		if(country != null && !country.isEmpty()) {
			builder.append(", " + country);
		}

		if(zipCode != null && !zipCode.isEmpty()) {
			builder.append(" " + zipCode);
		}

		return builder.toString();
	}

	public static BigDecimal toBigDecimal(String val) {
		BigDecimal d;

		try {
			d = new BigDecimal(val);
		} catch (NumberFormatException e) {
			d = new BigDecimal(0);
		}

		return d;
	}
	
	public static String genNormalKey(int count) {
		return RandomStringUtils.random(count, "abcdefghijklmnopqrstvuywzx01234567890-ABCDEFGHJKLZXCVBNMQWERTYUIOP");
	}
	
	public static String genStrongKey(int count) {
		return RandomStringUtils.random(count, "abcdefghijklmnopqrstvuywzx01234567890-ABCDEFGHJKLZXCVBNMQWERTYUIOP+/") + 
				"+" + genNormalKey(12) + "==";
	}
	
	public static String removeAccent(String s) {
		  String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		  Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		  return pattern.matcher(temp).replaceAll("");
	 }
}