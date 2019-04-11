package dib.dib.Utils;

import java.io.File;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class UtilCustom {
	
	public static void checkFolderExist(String path) {
		File documentFolder = new File(path);
		if (!documentFolder.exists() || documentFolder == null) {
			documentFolder.mkdir();
		}
	}
	
	public static String normalizeName(String str) {
		try {
			String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
			Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
			return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll("đ", "d");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
