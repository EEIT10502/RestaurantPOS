package _02employee.view;

import java.io.CharArrayWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import _00model.EmployeeBean;
import _00model.MenuBean;



public class MultipleMembersPdfView extends AbstractITextPdfView {
	final String fontPath = "c:\\windows\\fonts\\kaiu.ttf";
	BaseFont bfChinese;
	Font titleFont;

	Font normalFont;
	Font italicFont;
	Font underlineFont;

	ServletContext context;
	public MultipleMembersPdfView(ServletContext context) {
		this.context = context;
	}

	// 設定字型物件
	private void init() throws Exception {
		bfChinese = BaseFont.createFont(fontPath, "Identity-H", BaseFont.NOT_EMBEDDED);
		titleFont = new Font(bfChinese, 20, Font.BOLD);
		normalFont = new Font(bfChinese, 14, Font.NORMAL);
		italicFont = new Font(bfChinese, 12, Font.ITALIC);
		underlineFont = new Font(bfChinese, 40, Font.UNDERLINE);
	}

	

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		init();
		// 處理文章式資料
		//processArticle(document);
		// 由新頁開始列印表格
//		document.newPage();
		// 處理表格式資料		
		processTable(model, document, 0);

		
	}
	
	// 處理表格化資料
	private void processTable(Map<String, Object> model, Document document, int newLines) throws Exception {
		// 表格的前置資料
		for(int n=0; n < newLines; n++) {
			document.add( Chunk.NEWLINE );	
		}
		
		
		addTitle(document, "員工基本資料", titleFont);
		document.add( Chunk.NEWLINE );	
		String[] tableTitle = {"#", "員工編號", "員工姓名", "職位", "性別", "電話", "地址", "狀態", "備註"};
		PdfPTable table = createTable(model, tableTitle,  9);
		document.add(table);
		
	}
//
//	private void processArticle(Document document) throws Exception {
//		addTitle(document, "圖片搜尋「idiot」出現川普 Google執行長：無手動干預", titleFont);
//		String file01 = "/text/News01.txt";
//		addParagraph(document, file01, "UTF-8");
//		
//		addTitle(document, "\n\n高薪工作人人愛", titleFont);
//		String file02 = "/text/News02.txt";
//		addParagraph(document, file02, "UTF-8");
//		
//	}

	private PdfPTable createTable(Map<String, Object> model, String[] title, int columnCount ) {
		PdfPTable table = new PdfPTable(columnCount); // PDF文件的直欄數量
		setTableHeading(table, title); 
		setTableData(table, model);
		return table;
		// 處理表格的標頭 // .setVerticalAlignment(Element.ALIGN_MIDDLE);
	}

	private void setTableData(PdfPTable table, Map<String, Object> model) {
		System.out.println("model=" + model);
		@SuppressWarnings("unchecked")
		List<EmployeeBean> list =  (List<EmployeeBean>) model.get("allEmployee");
		for(EmployeeBean m : list) {
			MyTextPdfPCell cell1 = new MyTextPdfPCell();
			cell1.setPhrase(new Phrase(String.valueOf(m.getEmpId()), normalFont));
			table.addCell(cell1);
			//
			MyTextPdfPCell cell2 = new MyTextPdfPCell();
			cell2.setPhrase(new Phrase(String.valueOf(m.getEmpNo()), normalFont));
			table.addCell(cell2);
			//
			MyTextPdfPCell cell3 = new MyTextPdfPCell();
			cell3.setPhrase(new Phrase(m.getEmpName(), normalFont));
			table.addCell(cell3);
			
			MyTextPdfPCell cell4 = new MyTextPdfPCell();
			cell4.setPhrase(new Phrase(m.getPosition(), normalFont));
			table.addCell(cell4);
			
			MyTextPdfPCell cell5 = new MyTextPdfPCell();
			cell5.setPhrase(new Phrase(m.getGender(), normalFont));
			table.addCell(cell5);
			
			MyTextPdfPCell cell6 = new MyTextPdfPCell();
			cell6.setPhrase(new Phrase(m.getTel(), normalFont));
			table.addCell(cell6);
			
			MyTextPdfPCell cell7 = new MyTextPdfPCell();
			cell7.setPhrase(new Phrase(m.getAddr(), normalFont));
			table.addCell(cell7);
			
			MyTextPdfPCell cell8 = new MyTextPdfPCell();
			cell8.setPhrase(new Phrase(m.getStatus(), normalFont));
			table.addCell(cell8);
			
			MyTextPdfPCell cell9 = new MyTextPdfPCell();
			cell9.setPhrase(new Phrase(m.getRemark(), normalFont));
			table.addCell(cell9);
			
			
		}
	}

	private void setTableHeading(PdfPTable table, String[] title) {
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
		BaseColor color = new BaseColor(224, 224, 224);
		table.getDefaultCell().setBackgroundColor(color);
		for(int n = 0; n < title.length; n++) {
			PdfPCell cell1 = new PdfPCell();
			cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell1.setPaddingBottom(8F);
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell1.setPhrase(new Phrase(title[n], titleFont));
			table.addCell(cell1);
		}
		

		
	}
	
	private void addTitle(Document document, String title, Font font) throws Exception {
		Paragraph pg1 = new Paragraph();
		pg1.setFont(font);
		pg1.setAlignment(Paragraph.ALIGN_CENTER);
		pg1.add(title);
		document.add(pg1);
	}

//	private void addParagraph(Document document, String textFile, String charEncoding) throws Exception {
//		Paragraph pg1 = new Paragraph();
//		pg1.setFont(normalFont);
//		pg1.add(fileToString(textFile, charEncoding));
//		document.add( Chunk.NEWLINE );
//		document.add(pg1);
//	}

//	public String fileToString(String textFile, String charEncoding) throws Exception {
//		String msg = "";
//		try (
//			InputStream fis = context.getResourceAsStream(textFile);
//			InputStreamReader isr = new InputStreamReader(fis, charEncoding);
//			CharArrayWriter caw = new CharArrayWriter();
//		) {
//			char[] c = new char[8192];
//			int len = 0;
//			while ((len = isr.read(c)) != -1) {
//				caw.write(c, 0, len);
//			}
//			msg = caw.toString();
//		}
//		return msg;
//	}
	
	class MyTextPdfPCell extends PdfPCell{
		MyTextPdfPCell(){
	    	setBackgroundColor(BaseColor.LIGHT_GRAY);
	    	setPaddingBottom(8F);
	    	setHorizontalAlignment(Element.ALIGN_CENTER);
	    	setVerticalAlignment(Element.ALIGN_MIDDLE);
		}
	}
}
