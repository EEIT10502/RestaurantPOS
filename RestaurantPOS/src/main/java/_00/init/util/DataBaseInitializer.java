package _00.init.util;

import org.hibernate.SessionFactory;

import _00.init.tableInit.AttendenceTableInit;
import _00.init.tableInit.CalendarTableInit;
import _00.init.tableInit.CumulativeTurnoverTableInit;
import _00.init.tableInit.EmployeeTableInit;
import _00.init.tableInit.ManagerTableInit;
import _00.init.tableInit.MenuTableInit;
import _00.init.tableInit.OrderDetailTableInit;
import _00.init.tableInit.OrderTableInit;
import _00.init.tableInit.ScheduleTableInit;
import _00.init.tableInit.TargetTurnoverTableInit;

public class DataBaseInitializer {

	public static void main(String[] args) {
		
		SessionFactory factory= HibernateUtils.getSessionFactory();
		
		//建立Menu資料
		new MenuTableInit(factory).initMenu();
		
		//建立Manger資料 
		new ManagerTableInit(factory).initManager();
		
		//建立Employee資料
		new EmployeeTableInit(factory).InitEmployee();
		
		//建立CumulativeTurnover資料
		new CumulativeTurnoverTableInit(factory).initCumulativeTurnover();
		
		
		//建立TargetTurnover資料
		new TargetTurnoverTableInit(factory).initTargetTurnover();
		
		
		//建立Order資料
		new OrderTableInit(factory).initOrder();
		
		//建立OrderDetailBean資料
		new OrderDetailTableInit(factory).initOrderDetail();
		
		//建立Schedule資料
		new ScheduleTableInit(factory).initSchedule();
		
		//建立Calendar資料
		new CalendarTableInit(factory).initCalendar();
		
		//建立Attendence資料
		new AttendenceTableInit(factory).initAttendence();
		
		factory.close();
		System.out.println("建構表格完成!");
	}

}
