package _05financial.abstractView.impl;
 
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import _00model.CumulativeTurnoverBean;
import _00model.TargetTurnoverBean;
import _05financial.abstractView.AbstractITextPdfViewTest;

public class PDFBuilder extends AbstractITextPdfViewTest {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
	}
//	@SuppressWarnings("unchecked")
//	@Override
//	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
//			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		// get data model which is passed by the Spring container
////		List<Object> listGoalReportPdf = (List<Object>) model.get("listGoalReportPdf");
//		List<CumulativeTurnoverBean> listgoalCum = (List<CumulativeTurnoverBean>) model.get("listgoalCum");
//		List<TargetTurnoverBean> listgoalturn = (List<TargetTurnoverBean>) model.get("listgoalturn");
////        document.add(new Paragraph("Recommended books for Spring framework"));
//         
//        PdfPTable table = new PdfPTable(5);
//        table.setWidthPercentage(100.0f);
//        table.setWidths(new float[] {3.0f, 2.0f, 2.0f, 2.0f, 1.0f});
//        table.setSpacingBefore(10);
//         
//        // define font for table header row
//        Font font = FontFactory.getFont(FontFactory.HELVETICA);
//        font.setColor(BaseColor.WHITE);
//         
//        // define table header cell
//        PdfPCell cell = new PdfPCell();
//        cell.setBackgroundColor(BaseColor.BLUE);
//        cell.setPadding(5);
//         
//        // write table header
//        cell.setPhrase(new Phrase("日期", font));
//        table.addCell(cell);
//         
//        cell.setPhrase(new Phrase("目標營業額", font));
//        table.addCell(cell);
// 
//        cell.setPhrase(new Phrase("累計營業額", font));
//        table.addCell(cell);
//         
//        cell.setPhrase(new Phrase("差額", font));
//        table.addCell(cell);
//         
//        cell.setPhrase(new Phrase("達成率", font));
//        table.addCell(cell);
//         
//        // write table row data
//       
////            table.addCell((PdfPCell) listGoalReportPdf.get(1));
////            table.addCell((PdfPCell) listGoalReportPdf.get(8));
////            table.addCell((PdfPCell) listGoalReportPdf.get(5));
////            table.addCell((PdfPCell) listGoalReportPdf.get(2));
////            table.addCell((PdfPCell) listGoalReportPdf.get(4));
//        
//         
//        document.add(table);
//         
//    }
		
	}


