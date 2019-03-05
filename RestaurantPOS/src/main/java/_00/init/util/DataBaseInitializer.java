package _00.init.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _00.init.tableInit.ManagerTableInit;
import _00.init.tableInit.MenuTableInit;

public class DataBaseInitializer {

	public static void main(String[] args) {
		
		SessionFactory factory= HibernateUtils.getSessionFactory();
		
		//建立Menu資料
		//new MenuTableInit(factory).initMenu();
		
		//建立Manger資料 ，之後密碼會改暗碼
		//new ManagerTableInit(factory).initManager();
		factory.close();
		System.out.println("建構表格完成!");
	}

}
