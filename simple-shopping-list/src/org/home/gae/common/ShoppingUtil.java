package org.home.gae.common;

public class ShoppingUtil {
	
public static boolean isNumber(String potenialNumber) {
	try {
		//TODO cut off leading and following spaces 
		Integer.parseInt(potenialNumber);
		return true;
	} catch(NumberFormatException ex) {
		//TODO loging
		throw ex;
	}
	//return false;
}

}
