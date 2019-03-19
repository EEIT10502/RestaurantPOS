package _00.init.printerUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;

import _00.init.util.GlobalService;
import _00.init.util.SystemUtils2018;
import _00model.CumulativeTurnoverBean;
import _00model.OrderBean;
import _00model.OrderDetailBean;
import _00model.TargetTurnoverBean;
import _00.init.printerUtils.*;

public class MainPrinter {

	private static PosPrinter posPrinter;
	
	//列印給客人的帳單
	public static void printBill(OrderBean orderBean,Set<OrderDetailBean> OrderDetailBeanSet) {
		
	try {
		
		posPrinter = new PosPrinter(GlobalService.IP, 9100,GlobalService.Encoding); // 第一個引數是印表機網口IP
		// 初始化印表機
		posPrinter.initPos();
		posPrinter.bold(true);
		posPrinter.printText("EEIT105");
		posPrinter.printLocation(1);
		posPrinter.printTextNewLine("----------------------------------------------");
		posPrinter.bold(false);
		posPrinter.printLocation(0);
		posPrinter.printTextNewLine("訂 單 號: "+orderBean.getOrderNo());
		posPrinter.printTextNewLine("叫 號 機: "+orderBean.getCallNo());
		posPrinter.printTextNewLine("人  數: "+orderBean.getCusFlow());
		posPrinter.printTextNewLine("訂單日期: "+SystemUtils2018.getTimeForPrinter());
		posPrinter.printTextNewLine("付款方式:現金");
		
		posPrinter.printLine(2);
		posPrinter.printText("品項");
		posPrinter.printLocation(20, 1);
		posPrinter.printText("單價");
		posPrinter.printLocation(99, 1);
		posPrinter.printWordSpace(2);
		posPrinter.printText("數量");
		posPrinter.printWordSpace(5);
		posPrinter.printText("小計");
		posPrinter.printTextNewLine("----------------------------------------------");
		for (OrderDetailBean foods : OrderDetailBeanSet) {
			posPrinter.printTextNewLine(foods.getProductName());
			posPrinter.printLocation(26, 1);
			posPrinter.printText(foods.getProductPrice().toString());
			posPrinter.printLocation(103, 1);
			posPrinter.printWordSpace(3);
			posPrinter.printText(foods.getQty().toString());
			posPrinter.printWordSpace(7);
			String subtotal = Integer.toString((foods.getQty()*foods.getProductPrice()));
			posPrinter.printText(subtotal);
		}
		posPrinter.printTextNewLine("----------------------------------------------");
		posPrinter.printLocation(99, 1);
		posPrinter.printWordSpace(2);
		posPrinter.printText("總計:");
		posPrinter.printWordSpace(5);
		posPrinter.printText(Integer.toString(orderBean.getTotalPrice()));
		posPrinter.printTextNewLine("外帶請勿隔餐食用");
		posPrinter.printLocation(1);
		posPrinter.printLine();
		posPrinter.printTextNewLine("掃一掃");
		posPrinter.printLocation(1);
		posPrinter.printLine(2);
		// 列印二維碼
		posPrinter.qrCode("https://reurl.cc/VNeGn");
		// 切紙
		posPrinter.feedAndCut();
		posPrinter.closeIOAndSocket();
		posPrinter = null;
	} catch (IOException e) {
			e.printStackTrace();
			
	}
	
	}
	
	//列印給廚房的出菜單
	public static void printForBK(OrderBean orderBean,Set<OrderDetailBean> OrderDetailBeanSet) {
		
		 Socket socket = null;
	        try {
	        	String printContent = "";
	            socket = new Socket();
	            socket.connect(new InetSocketAddress(GlobalService.IP, 9100), 4000);
	            
	           // System.out.println("CashierDesk=====小票机连接成功，IP：" + GlobalService.IP);
	            
	            OutputStream os = socket.getOutputStream();
	            
	            
	            printContent += PrinterCmdUtils.bothDouble();
	            printContent += PrinterCmdUtils.boldOn();
	            printContent += "廚房單\n";
	            printContent += "叫號機: "+orderBean.getCallNo()+"\n";  
	            printContent += PrinterCmdUtils.boldOff();
	            printContent += PrinterCmdUtils.ZoomCancel();
	            printContent += PrinterCmdUtils.longitudinalDouble();
	            printContent += "時  間:"+SystemUtils2018.getTimeForPrinter()+"\n";
	            printContent += "--數量--------------------品名------\n";
	            printContent += PrinterCmdUtils.bothDouble();
	            printContent += PrinterCmdUtils.boldOn();
	            for(OrderDetailBean set:OrderDetailBeanSet) {
	            	 printContent += " "+set.getQty()+"\t\t"+set.getProductName()+"\n\n";
	            	
	            	
	            }
	            printContent += PrinterCmdUtils.boldOff();
	            printContent += PrinterCmdUtils.ZoomCancel();
	           
	            // 切纸命令
	            String text = printContent + "\n\n\n\n\n\n";
	            byte[] CMD_INIT = { 27, 64 };
	            os.write(CMD_INIT);
	            os.write(text.getBytes("BIG5"));
	            final byte[] CMD_CUT = { 0x1D, 0x56, 0x01 };
	            os.write(CMD_CUT);
	           // System.out.println("CashierDesk=====小票机打印完成，IP：" +  GlobalService.IP);
	            

	        } catch (UnknownHostException e) {
	        
	            System.out.println("CashierDesk=====小票机连接失败，IP：" +  GlobalService.IP);
	            e.printStackTrace();
	        } catch (IOException e) {
	            System.out.println("CashierDesk=====小票机连接失败，IP：" +  GlobalService.IP);
	            e.printStackTrace();
	        } finally {
	            if (socket != null) {
	                try {
	                    socket.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
		
		}

	//列印日結清機的清機單
	public static void printDayCheck(Map<String, Object> mapData) {
		
		CumulativeTurnoverBean CTBean = (CumulativeTurnoverBean) mapData.get("CumulativeTurnover");
		TargetTurnoverBean TTBean = (TargetTurnoverBean) mapData.get("TargetTurnover");
		
		 Socket socket = null;
	        try {
	        	String printContent = "";
	            socket = new Socket();
	            socket.connect(new InetSocketAddress(GlobalService.IP, 9100), 4000);

	           // System.out.println("CashierDesk=====小票机连接成功，IP：" + GlobalService.IP);
	            
	            OutputStream os = socket.getOutputStream();
	            
	            
	            printContent += PrinterCmdUtils.bothDouble();
	            printContent += PrinterCmdUtils.boldOn();
	            printContent += "\t\t清機單\n";
	            printContent += PrinterCmdUtils.boldOff();
	            printContent += PrinterCmdUtils.ZoomCancel();
	            printContent += PrinterCmdUtils.longitudinalDouble();
	            printContent += "時  間:"+SystemUtils2018.getTimeForPrinter()+"\n\n";
	            printContent += PrinterCmdUtils.bothDouble();
	           
	            printContent += "現金收入: "+CTBean.getMoneyReceived()+"\n";
	            printContent += "現金短溢: "+CTBean.getShortoverAmount()+"\n";
	            printContent += "銷售金額: "+CTBean.getTurnover()+"\n";
	            printContent += "日累積營業額: "+CTBean.getCumulativeTurnover()+"\n";
	            printContent += "月目標營業額: "+TTBean.getTargetTurnover()+"\n";
	            printContent += "月目標達成率: "+SystemUtils2018.getAchievingRate(CTBean.getCumulativeTurnover(), TTBean.getTargetTurnover())+"\n";
	            printContent +="\n\n\n\n\n\n";
	            printContent +="數據分析: \n";
	            printContent +="今日來客數: "+mapData.get("來客數")+" 人\n";
	            printContent +="客平均消費: "+mapData.get("客平均消費")+" 元\n";
	            printContent +="平均翻桌率: "+mapData.get("翻桌率")+" 次\n";
	            printContent += PrinterCmdUtils.ZoomCancel();
	           
	            // 切纸命令
	            String text = printContent + "\n\n\n\n\n\n\n";
	            byte[] CMD_INIT = { 27, 64 };
	            os.write(CMD_INIT);
	            os.write(text.getBytes("BIG5"));
	            final byte[] CMD_CUT = { 0x1D, 0x56, 0x01 };
	            os.write(CMD_CUT);
	           // System.out.println("CashierDesk=====小票机打印完成，IP：" +  GlobalService.IP);
	            

	        } catch (UnknownHostException e) {
	        
	            System.out.println("CashierDesk=====小票机连接失败，IP：" +  GlobalService.IP);
	            e.printStackTrace();
	        } catch (IOException e) {
	            System.out.println("CashierDesk=====小票机连接失败，IP：" +  GlobalService.IP);
	            e.printStackTrace();
	        } finally {
	            if (socket != null) {
	                try {
	                    socket.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
		
	}
}
