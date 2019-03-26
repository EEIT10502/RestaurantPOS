package _00.init.util;

public class GlobalService {

	// productInsert所需資料_開始
	public static final String Product_Cate_Rice = "飯類";
	public static final String Product_Cate_Soup = "湯類";
	public static final String PRODUCT_CATE_NOODLE = "麵類";
	public static final String PRODUCT_CATE_VEGETABLE = "菜類";
	public static final String PRODUCT_CATE_SIDEDISH = "小菜類";

	public static final String Product_Status_Launched_Already = "販售";
	public static final String Product_Status_No_Longer_Be_Sold = "停售";
	// productInsert所需資料_結束

	// product查詢所需資料_開始
	
	public static final String EXCEL_HEADER_COUNT = "#";
	public static final String EXCEL_HEADER_PRODUCTNO = "商品編號";
	public static final String EXCEL_HEADER_PRODUCTNAME = "商品名稱";
	public static final String EXCEL_HEADER_PRICE = "價格";
	public static final String EXCEL_HEADER_CATE = "類別";
	public static final String EXCEL_HEADER_PRODUCTSTATUS = "狀態";
	// product查詢所需資料_結束

	// product匯出Excel所需資料_開始
	public static final int ITEMS_PER_PAGE = 5;
	// product匯出Excel所需資料_結束

	// 下面兩個是給出單機的IP位置和編碼方式
	public static final String IP = "192.168.1.100";
	public static final String Encoding = "BIG5";

	
	// employee
	// 職位
	public static final String Employee_No = "1000";
	public static final String Employee_CATE_waiter = "服務生";
	public static final String Employee_CATE_EChef = "廚師";
	public static final String Employee_CATE_MChef = "主廚";
	public static final String Employee_CATE_manager = "經理";
	// 就業狀態
	public static final String Employee_Status_In_Service = "在職";
	public static final String Employee_Status_Resignate = "離職";
	public static final String Employee_Status_Leave_of_absence = "留職停薪";
	//性別
	public static final String Employee_Gender_Male = "男性";
	public static final String Employee_Gender_Female = "女性";

	// Employee查詢所需資料_開始
	public static final int Employees_PER_PAGE = 5;
	// Employee查詢所需資料_結束
	
	//報表分頁
	public static final int REPORT_PER_PAGE = 10;
}