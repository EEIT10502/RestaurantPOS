package _00.init.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;



public class SystemUtils2018 {

	public static Blob fileToBlob(String imageFileName) throws IOException, SQLException {
		File imageFile = new File(imageFileName);
		long size = imageFile.length();
		byte[] b = new byte[(int) size];
		SerialBlob sb = null;
		try (FileInputStream fis = new FileInputStream(imageFile);) {
			fis.read(b);
			sb = new SerialBlob(b);
		}
		return sb;
	}

	public static Clob fileToClob(String textFileName) throws IOException, SQLException {
		Clob clob = null;
		try (InputStreamReader isr = new InputStreamReader(new FileInputStream(textFileName), "UTF-8");) {
			char[] c = new char[8192];
			StringBuffer buf = new StringBuffer();
			int len = 0;
			while ((len = isr.read(c)) != -1) {
				buf.append(new String(c, 0, len));
			}
			char[] ca = buf.toString().toCharArray();
			clob = new SerialClob(ca);
		}
		return clob;
	}

	public static void clobToFile(Clob clob, File file, String encoding) throws IOException, SQLException {
		try (Reader reader = clob.getCharacterStream();
				BufferedReader br = new BufferedReader(reader);
				FileOutputStream fos = new FileOutputStream(file);
				OutputStreamWriter osw = new OutputStreamWriter(fos, encoding);
				PrintWriter out = new PrintWriter(osw);) {
			String line = null;
			while ((line = br.readLine()) != null) {
				out.println(line);
			}
		}
	}

	public static Blob fileToBlob(InputStream is, long size) throws IOException, SQLException {
		byte[] b = new byte[(int) size];
		SerialBlob sb = null;
		is.read(b);
		sb = new SerialBlob(b);
		return sb;
	}

	public static String extractFileName(String pathName) throws IOException, SQLException {
		return pathName.substring(pathName.lastIndexOf("/") + 1);
	}
	
	
	public static Date strToDate(String strDate) {
		String str = strDate;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = null;
		try {
			d = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		java.sql.Date date = new java.sql.Date(d.getTime());
		return date;
	}

	public static Time strToTime(String strDate) {
		String str = strDate;
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
		java.util.Date d = null;
		try {
			d = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Time date = new java.sql.Time(d.getTime());
		return date;
	}
	public static String getDate() {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String strDate = format.format(date);
		
		return strDate;
		
	}
	public static String getTime() {
		
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
		Date date = new Date();
		String strDate = format.format(date);
		
		return strDate;
		
	}
	
	public static String getTimeForPrinter() {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String strDate = format.format(date);
		
		return strDate;
	}
	
	public static String getMonth() {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		String strDate = format.format(date);
		
		return strDate;
		
	}
	/**將傳入的參數相除轉換成%數顯示
	 * 累計營業額/目標營業額 =目標達成率
	 * @param cumulativeTurnover  累計營業額
	 * @param targetTurnover 	目標營業額
	 * @return 回傳一個字串(如:45%)
	 */
	public static String getAchievingRate(int cumulativeTurnover,int targetTurnover) {
		String rate ="";
		
		float achievingRate = (float)cumulativeTurnover/targetTurnover;
		rate = Integer.toString((int) (achievingRate*100))+"%";
		return rate;
	}
	//測試你的方法
	public static void main(String[] args) {
		
		
		
	}

}
