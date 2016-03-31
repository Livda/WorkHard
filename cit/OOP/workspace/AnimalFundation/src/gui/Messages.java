/**
 * 
 */
package gui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class Messages {
	private static final String BUNDLE_NAME = "values.strings_fr"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private Messages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
