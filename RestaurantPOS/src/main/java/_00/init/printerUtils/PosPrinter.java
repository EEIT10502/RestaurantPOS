package _00.init.printerUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by haoguibao on 16/2/18. Description : 封裝Pos機列印工具類 Revision :
 */
public class PosPrinter {
//定義編碼方式 
	private static String encoding = null;
	private Socket sock = null;
// 通過socket流進行讀寫 
	private OutputStream socketOut = null;
	private OutputStreamWriter writer = null;

	/**
	 * 初始化Pos例項
	 * 
	 * @param ip       印表機IP
	 * @param port     印表機埠號
	 * @param encoding 編碼
	 * @throws IOException
	 */
	public PosPrinter(String ip, int port, String encoding) throws IOException {
		sock = new Socket(ip, port);
		socketOut = new DataOutputStream(sock.getOutputStream());
		this.encoding = encoding;
		writer = new OutputStreamWriter(socketOut, encoding);
	}

	/**
	 * 關閉IO流和Socket
	 * 
	 * @throws IOException
	 */
	protected void closeIOAndSocket() throws IOException {
		writer.close();
		socketOut.close();
		sock.close();
	}

/** 
* 列印二維碼 
* 
* @param qrData 二維碼的內容 
* @throws IOException 
*/ 
	protected void qrCode(String qrData) throws IOException { 
		int moduleSize = 8; 
		int length = qrData.getBytes(encoding).length; 
		//列印二維碼矩陣 
		writer.write(0x1D);// init 
		writer.write("(k");// adjust height of barcode 
		writer.write(length+3); // pl  這裡要用"+"3
		writer.write(0); // ph 
		writer.write(49); // cn 
		writer.write(80); // fn 
		writer.write(48); // 
		writer.write(qrData); 
		writer.write(0x1D); 
		writer.write("(k"); 
		writer.write(3); 
		writer.write(0); 
		writer.write(49); 
		writer.write(69); 
		writer.write(48); 
		writer.write(0x1D); 
		writer.write("(k"); 
		writer.write(3); 
		writer.write(0); 
		writer.write(49); 
		writer.write(67); 
		writer.write(moduleSize); 
		writer.write(0x1D); 
		writer.write("(k"); 
		writer.write(3); // pl 
		writer.write(0); // ph 
		writer.write(49); // cn 
		writer.write(81); // fn 
		writer.write(48); // m 
		writer.flush(); 
		} 

	/**
	 * 進紙並全部切割
	 * 
	 * @return
	 * @throws IOException
	 */

    public void feedAndCut() throws IOException {
	//protected void feedAndCut() throws IOException {
		writer.write(0x1D);
		writer.write(86);
		writer.write(65);
//    writer.write(0); 
//切紙前走紙多少 
		writer.write(100);
		writer.flush();
//另外一種切紙的方式 
//    byte[] bytes = {29, 86, 0}; 
//    socketOut.write(bytes); 
	}

	/**
	 * 列印換行
	 * 
	 * @return length 需要列印的空行數
	 * @throws IOException
	 */
	protected void printLine(int lineNum) throws IOException {
		for (int i = 0; i < lineNum; i++) {
			writer.write("\n");
		}
		writer.flush();
	}

	/**
	 * 列印換行(只換一行)
	 * 
	 * @throws IOException
	 */
	protected void printLine() throws IOException {
		writer.write("\n");
		writer.flush();
	}

	/**
	 * 列印空白(一個Tab的位置，約4個漢字)
	 * 
	 * @param length 需要列印空白的長度,
	 * @throws IOException
	 */
	protected void printTabSpace(int length) throws IOException {
		for (int i = 0; i < length; i++) {
			writer.write("\t");
		}
		writer.flush();
	}

	/**
	 * 列印空白（一個漢字的位置）
	 * 
	 * @param length 需要列印空白的長度,
	 * @throws IOException
	 */
	protected void printWordSpace(int length) throws IOException {
		for (int i = 0; i < length; i++) {
			writer.write(" ");
		}
		writer.flush();
	}

	/**
	 * 列印位置調整
	 * 
	 * @param position 列印位置 0：居左(預設) 1：居中 2：居右
	 * @throws IOException
	 */
	protected void printLocation(int position) throws IOException {
		writer.write(0x1B);
		writer.write(97);
		writer.write(position);
		writer.flush();
	}

	/**
	 * 絕對列印位置
	 * 
	 * @throws IOException
	 */
	protected void printLocation(int light, int weight) throws IOException {
		writer.write(0x1B);
		writer.write(0x24);
		writer.write(light);
		writer.write(weight);
		writer.flush();
	}

	/**
	 * 列印文字
	 * 
	 * @param text
	 * @throws IOException
	 */
	protected void printText(String text) throws IOException {
		String s = text;
		byte[] content = s.getBytes("BIG5");
		socketOut.write(content);
		socketOut.flush();
	}

	/**
	 * 新起一行，列印文字
	 * 
	 * @param text
	 * @throws IOException
	 */
	protected void printTextNewLine(String text) throws IOException {
//換行 
		writer.write("\n");
		writer.flush();
		String s = text;
		byte[] content = s.getBytes("BIG5");
		socketOut.write(content);
		socketOut.flush();
	}

	/**
	 * 初始化印表機
	 * 
	 * @throws IOException
	 */
	protected void initPos() throws IOException {
		writer.write(0x1B);
		writer.write(0x40);
		writer.flush();
	}

	/**
	 * 加粗
	 * 
	 * @param flag false為不加粗
	 * @return
	 * @throws IOException
	 */
	protected void bold(boolean flag) throws IOException {
		if (flag) {
//常規粗細 
			writer.write(0x1B);
			writer.write(69);
			writer.write(0xF);
			writer.flush();
		} else {
//加粗 
			writer.write(0x1B);
			writer.write(69);
			writer.write(0);
			writer.flush();
		}
	}

}
