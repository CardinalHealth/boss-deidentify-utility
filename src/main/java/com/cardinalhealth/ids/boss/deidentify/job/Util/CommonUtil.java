/**
 * 
 */
package com.cardinalhealth.ids.boss.deidentify.job.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mohan.arora
 *
 */
public class CommonUtil {
	/**
	 * It will generate numeric masking.
	 * 
	 * @param value
	 * @return
	 */
	public static String numberMasking(String value) {
		StringBuilder sb = new StringBuilder();
		Map<Character, Character> map = getIntegerMapping();
		for (Character ch : value.toCharArray()) {
			if (map.containsKey(ch)) {
				sb.append(map.get(ch));
			}else {
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	/**
	 * Numeric mapping helper
	 * 
	 * @return
	 */
	private static Map<Character, Character> getIntegerMapping() {
		Map<Character, Character> map = new HashMap<>();
		map.put('0', '0');
		map.put('6', '1');
		map.put('7', '2');
		map.put('8', '3');
		map.put('5', '4');
		map.put('9', '5');
		map.put('1', '6');
		map.put('2', '7');
		map.put('3', '8');
		map.put('4', '9');

		return map;
	}
}
