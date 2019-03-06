package _00.init;

import org.hibernate.SessionFactory;

//import _00_init.tables.ApplicationTableInit;
//import _00_init.tables.AttendenceTableInit;
//import _00_init.tables.CityTableInit;
//import _00_init.tables.CompanyTableInit;
//import _00_init.tables.ComplaintTableInit;
//import _00_init.tables.ExperienceTableInit;
//import _00_init.tables.InterviewTableInit;
//import _00_init.tables.JobTableInit;
//import _00_init.tables.MessageTableInit;
//import _00_init.tables.NotificationTableInit;
//import _00_init.tables.OrderTableInit;
//import _00_init.tables.ProductTableInit;
//import _00_init.tables.ResumeTableInit;
//import _00_init.tables.SalaryTableInit;
//import _00_init.tables.ScheduleTableInit;
//import _00_init.tables.SuggestionTableInit;
//import _00_init.tables.UserTableInit;
import _00.init.util.HibernateUtils;

public class DatabaseInit {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
//		new UserTableInit(factory).initUser();
//		new CompanyTableInit(factory).initCompany();
//		new ResumeTableInit(factory).initResume();
//		new ExperienceTableInit(factory).initExperience();
//		new NotificationTableInit(factory).initNotificaion();
//		new CityTableInit(factory).initCity();
//		new JobTableInit(factory).initJob();
//		new ScheduleTableInit(factory).initSchedule();
//		new ComplaintTableInit(factory).initSuggestion();
//		new SalaryTableInit(factory).initSalary();
//		new AttendenceTableInit(factory).initAttendence();
//		new ApplicationTableInit(factory).initApplicatoin();
//		new InterviewTableInit(factory).initInterview();
//		new ProductTableInit(factory).initProduct();
//		new OrderTableInit(factory).initOrder();
//		new MessageTableInit(factory).initMessage();
//		new SuggestionTableInit(factory).initSuggestion();
		factory.close();
	}
}