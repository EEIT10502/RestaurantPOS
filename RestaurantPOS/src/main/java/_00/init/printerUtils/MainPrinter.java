package _00.init.printerUtils;

import java.io.IOException;
import java.util.Set;

import _00.init.util.GlobalService;
import _00.init.util.SystemUtils2018;
import _00model.OrderBean;
import _00model.OrderDetailBean;

public class MainPrinter {

	private static PosPrinter posPrinter;
	
	
	public static void printBill(OrderBean orderBean,Set<OrderDetailBean> OrderDetailBeanSet) {
	
	try {
		posPrinter = new PosPrinter(GlobalService.IP, 9100,GlobalService.Encoding); // 第一個引數是印表機網口IP
		// 初始化印表機
		posPrinter.initPos();
		posPrinter.bold(true);
//		posPrinter.printTabSpace(2);
//		posPrinter.printWordSpace(1);
//		posPrinter.printLocation(1);
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
}
