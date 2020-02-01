package com.epoint.mealorder.util;

import java.util.ResourceBundle;

public class ConfigUtil {
	public static String getJDBCConfigValue(String configname) {
		String result = null;
		ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");
		if (resourceBundle.containsKey(configname)) {
			result = resourceBundle.getString(configname);
			if (result.contains("") && result != null) {
				result = result.trim();
			}
		}
		return result;
	}
}
