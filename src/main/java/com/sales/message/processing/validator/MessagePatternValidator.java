package com.sales.message.processing.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regex class to validate allowed Message Types pattern with entered Messages
 * @author Shruti Agarwal
 * 
 */
public class MessagePatternValidator {
	public static boolean isPatternMatch(String patternRegex, String line) {
		Pattern pattern = Pattern.compile(patternRegex);
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
			return true;
		} else {
			return false;
		}
	}

	public static List<String> getMessageTokens(String patternRegex, String line) {

		Matcher matcher = getPatternMatcher(patternRegex, line);
		int totalCount = matcher.groupCount();
		List<String> msgTokens = new ArrayList<String>();
		for (int i = 0; i <= totalCount; i++) {
			msgTokens.add(matcher.group(i));
		}

		return msgTokens;
	}

	private static Matcher getPatternMatcher(String patternRegex, String line) {
		Pattern pattern = Pattern.compile(patternRegex);
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
			return matcher;
		} else {
			return null;
		}
	}
}
