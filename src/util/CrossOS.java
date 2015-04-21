package util;

import java.io.File;

public class CrossOS {
	public static String fileSeparator(String path) {
		String[] fileSeparators = {"/", "\\"};
		for (String fs : fileSeparators) {
			path = path.replace(fs, File.separator);
		}
		
		return path;
	}
	
	public static void main(String [] args) {
		String unixPath = "data/deceptive_from_MTurk/";
		String windowPath = "data\\deceptive_from_MTurk\\";
		
		System.out.println(fileSeparator(unixPath));
		System.out.println(fileSeparator(windowPath));
	}
}
