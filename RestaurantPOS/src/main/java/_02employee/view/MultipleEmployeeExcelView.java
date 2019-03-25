//package _02employee.view;
//  
//import java.text.DecimalFormat;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFFont;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.CreationHelper;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.springframework.web.servlet.view.document.AbstractXlsView;
//
//import _00.init.util.GlobalService;
//import _00model.EmployeeBean;
//import _00model.MenuBean;
//
//public class MultipleEmployeeExcelView extends AbstractXlsView  {
//	
//	Sheet employeeSheet;	
//	String employeeSheetName = "employee sheet 1";
//	HSSFFont chiTextFont = null;
//	HSSFFont engTextFont = null;
//	HSSFFont titleFont = null;
//	int rowCount = 0;
//	int colCount = 0;
//	short fontSize = 16;
//	@Override
//	protected void buildExcelDocument(Map<String, Object> model,
//			Workbook workbook, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		rowCount = 0;
//		colCount = 0;
//		setSheetProperties(workbook);
//        createExcelHeaders(workbook);
//		populateExcelCells(model, workbook);
//
//	}
//
//	private void setSheetProperties(Workbook workbook) {
//		// 由 workbook產生Sheet物件
//		employeeSheet = workbook.createSheet(employeeSheetName);
//		// 由 workbook產生HSSFont物件
//		chiTextFont= (HSSFFont)workbook.createFont();
//		// 設定字型名稱
//		chiTextFont.setFontName("新細明體");
//		// 設定字號		
//		chiTextFont.setFontHeightInPoints(fontSize);
//		//--------------------------------------
//		// 由 workbook產生HSSFont物件
//		engTextFont= (HSSFFont)workbook.createFont();
//		// 設定字型名稱
//		engTextFont.setFontName("Arial");
//		// 設定字號
//		engTextFont.setFontHeightInPoints(fontSize);
//		
//		
//		titleFont = chiTextFont;
//	}
//
//	@SuppressWarnings({ "unchecked", "unused" })
//	private void populateExcelCells(Map<String, Object> model, Workbook workbook) {
//		Sheet employeeSheet = workbook.getSheet(employeeSheetName);
//		
//		HSSFCellStyle styleCenter = (HSSFCellStyle)workbook.createCellStyle();
//		styleCenter.setFont(engTextFont);
//		
//		styleCenter.setFillForegroundColor(IndexedColors.WHITE.index);
//		styleCenter.setFillPattern(CellStyle.SOLID_FOREGROUND);
//		styleCenter.setAlignment(CellStyle.ALIGN_CENTER);
//		
//		styleCenter.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
//		styleCenter.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
//		styleCenter.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
//		styleCenter.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
//		
//		CellStyle styleName = workbook.createCellStyle();
//		styleName.setFillForegroundColor(IndexedColors.WHITE.index);
//		styleName.setFillPattern(CellStyle.SOLID_FOREGROUND);
//		styleName.setAlignment(CellStyle.ALIGN_CENTER);
//		styleName.setFont(chiTextFont);
//		
//		styleName.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
//		styleName.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
//		styleName.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
//		styleName.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
//		
//		CellStyle styleRight = workbook.createCellStyle();
//		styleRight.setFillForegroundColor(IndexedColors.WHITE.index);
//		styleRight.setFillPattern(CellStyle.SOLID_FOREGROUND);
//		styleRight.setAlignment(CellStyle.ALIGN_RIGHT);
//		styleRight.setFont(engTextFont);
//		
//		styleRight.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
//		styleRight.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
//		styleRight.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
//		styleRight.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
//
//		
//        HSSFCellStyle styleDate = (HSSFCellStyle)workbook.createCellStyle();
//		
//		CreationHelper createHelper = workbook.getCreationHelper();
//		styleDate.setDataFormat(
//		    createHelper.createDataFormat().getFormat("yyyy/mm/dd"));
//		styleDate.setFont(engTextFont);
//		styleDate.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
//		styleDate.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
//		styleDate.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
//		styleDate.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
//		styleDate.setAlignment(CellStyle.ALIGN_CENTER);
//		
//        List<EmployeeBean> employeeBeans = (List<EmployeeBean>) model.get("allEmployeeBean");
//        
//		Set<String> set = model.keySet();
//		Row row = null;
//		Cell cell = null;
//		for(EmployeeBean m : employeeBeans) {
//			colCount = 0;
//			row = employeeSheet.createRow(rowCount++);
//			
//
//			
//			cell = row.createCell(colCount++);
//			cell.setCellStyle(styleCenter);
//			cell.setCellValue(rowCount-1);
//			
//			//員工編號
//			cell = row.createCell(colCount++);
//			cell.setCellStyle(styleRight);
//			DecimalFormat  dfNo = new DecimalFormat("###");
//			cell.setCellValue(dfNo.format(m.getEmpNo()));
//			
//			//員工姓名
//			cell = row.createCell(colCount++);
//			cell.setCellStyle(styleName);
//			cell.setCellValue(m.getEmpName());
//			
//			//員工職位
//			cell = row.createCell(colCount++);
//			cell.setCellStyle(styleName);
//			cell.setCellValue(m.getPosition());
//			
//			//員工性別
//			cell = row.createCell(colCount++);
//			cell.setCellStyle(styleName);
//			cell.setCellValue(m.getGender());
//			
//			//員工電話
//			cell = row.createCell(colCount++);
//			cell.setCellStyle(styleName);
//			cell.setCellValue(m.getTel());
//			
//			//員工地址
//			cell = row.createCell(colCount++);
//			cell.setCellStyle(styleName);
//			cell.setCellValue(m.getAddr());
//			
//			//員工狀態
//			cell = row.createCell(colCount++);
//			cell.setCellStyle(styleName);
//			cell.setCellValue(m.getStatus());
//			
//			//員工備註
//			cell = row.createCell(colCount++);
//			cell.setCellStyle(styleName);
//			cell.setCellValue(m.getRemark());
//			
////			cell = row.createCell(colCount++);
////			cell.setCellStyle(styleDate);
////			cell.setCellValue(m.getBirthday());
//		}
//		int columnCount = employeeSheet.getRow(0).getLastCellNum();
//		for (int i=0; i < columnCount; i++){
//			employeeSheet.autoSizeColumn(i);
//		}
//	}
//
//	private void createExcelHeaders(Workbook workbook) {
//		String[] labels = {GlobalService.EMPLOYEE_EXCEL_HEADER_COUNT, GlobalService.EMPLOYEE_EXCEL_HEADER_EMPLOYEE_NO, GlobalService.EMPLOYEE_EXCEL_HEADER_EMPLOYEE_NAME, GlobalService.EMPLOYEE_EXCEL_HEADER_EMPLOYEE_POSITION, GlobalService.EMPLOYEE_EXCEL_HEADER_EMPLOYEE_GENDER, GlobalService.EMPLOYEE_EXCEL_HEADER_EMPLOYEE_TEL,GlobalService.EMPLOYEE_EXCEL_HEADER_EMPLOYEE_ADDR,GlobalService.EMPLOYEE_EXCEL_HEADER_EMPLOYEE_STATUS,GlobalService.EMPLOYEE_EXCEL_HEADER_EMPLOYEE_REMARK};
//		
//		
//		CellStyle titleStyle = workbook.createCellStyle();
//		
//		titleStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
//		titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//		titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
//		titleStyle.setFont(titleFont);
//		
//		titleStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
//		titleStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
//		titleStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
//		titleStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
//		
//		
//		// 建立Excel表的標頭
//		Row row = null;
//		Cell cell = null;
//		row = employeeSheet.createRow(rowCount++);
//		colCount = 0;
//		// Create header cells
//		for(int n =0; n < labels.length; n++) {
//			cell = row.createCell(colCount++);
//			cell.setCellStyle(titleStyle);
//			cell.setCellValue(labels[n]);
//		}
//	}
//
//}
