package com.shoppingwebapp.Controller;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.shoppingwebapp.DTO.ChartImgData;
import com.shoppingwebapp.Dao.ChartDataRepository;
import com.shoppingwebapp.Dao.RequestDataRepository;
import com.shoppingwebapp.Model.ChartData;
import com.shoppingwebapp.Service.BDataManageService;

@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = { "Origin", "Content-Type", "Accept" }, methods = {
		RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })

@RestController
public class BDataManageController {
	
	@Autowired
	private BDataManageService bDataManageService;
	
	@Autowired
	private RequestDataRepository requestRe;
	
	@Autowired
	private ChartDataRepository chartRepo;

	@ResponseBody
	@GetMapping("/getChart1Data")
	public List<ChartData> getChart1Data() {
		System.out.println("BDataManageController: getChart1Data()");
		return bDataManageService.findChartData2("chart1");
	}
	
	@ResponseBody
	@GetMapping("/getChart2Data")
	public List<ChartData> getChart2Data() {
		System.out.println("BDataManageController: getChart2Data()");
		return bDataManageService.findChartData2("chart2");
	}
	
	@ResponseBody
	@GetMapping("/getChart3Data")
	public List<ChartData> getChart3Data() {
		System.out.println("BDataManageController: getChart3Data()");
		return bDataManageService.findChartData2("chart3");
	}
	
	@ResponseBody
	@GetMapping("/getChart4Data")
	public List<ChartData> getChart4Data() {
		System.out.println("BDataManageController: getChart4Data()");
		return bDataManageService.findChartData2("chart4");
	}
	
	@ResponseBody
	@GetMapping("/getChart5Data")
	public List<ChartData> getChart5Data() {
		System.out.println("BDataManageController: getChart5Data()");
		return bDataManageService.findChartData("chart5");
	}
	
	@ResponseBody
	@GetMapping("/getChart6Data")
	public List<String> getChart6Data() {
		System.out.println("BDataManageController: getChart6Data()");
		return chartRepo.getChart6Data();
	}
	
	@ResponseBody
	@GetMapping("/getChart7Data")
	public List<String[]> getChart7Data() {
		System.out.println("BDataManageController: getChart7Data()");
		return requestRe.findChart7Data();
	}
	
	@ResponseBody
	@GetMapping("/getChart8Data")
	public String[] getChart8Data() {
		System.out.println("BDataManageController: getChart8Data()");
		return requestRe.findChart8Data();
	}
	
	@PostMapping("/exportChartAsPDF")
    public ResponseEntity<byte[]> exportChartAsPDF(@RequestBody ChartImgData chartImgData) {
        try {
            byte[] pdfBytes = generatePDF(chartImgData.getImgData());
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=chart.pdf")
                    .body(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    private byte[] generatePDF(String base64Image) throws Exception {
        // 去除base64前綴
        String base64ImageData = base64Image.split(",")[1];

        // 轉換為byte[]
        byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64ImageData);

        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();
        
        String title = "Travel website: Chart Data";
        Paragraph para = new Paragraph(title);
        document.add(para);

        Image img = Image.getInstance(imageBytes);
        img.scaleToFit(PageSize.A4.getWidth() - 20, PageSize.A4.getHeight() - 20);
        img.setAlignment(Image.ALIGN_CENTER);
        document.add(img);

        document.close();
        return baos.toByteArray();
    }
}
