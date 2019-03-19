package _01foodAndBeverages.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SeqUtils {

	public static String getSeqNo() throws Exception {
		DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		return formatter.format(date);
	}
	
}
