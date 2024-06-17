package com.shoppingwebapp.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.shoppingwebapp.Dao.ChartDataRepository;
import com.shoppingwebapp.Dao.SessionPageViewsRepository;
import com.shoppingwebapp.Model.ChartData;

import jakarta.transaction.Transactional;

@Service
public class DataProcessorService {

	@Autowired
	private SessionPageViewsRepository rawDataRepository;

	@Autowired
	private ChartDataRepository chartDataRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String month;

	public DataProcessorService() {
		// 取得現在月份日期並轉為string
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
		month = currentDate.format(formatter);
	}
	
	@Transactional
	public void processAndUpdateDataChart5() {
		String getRaw = "SELECT 'chart5' chart_name,"
				+ "(SELECT COUNT(to_path) traffic_count FROM session_page_views s\r\n"
				+ "WHERE s.month =  "+ month +"  AND to_path = '/categoryTraffic') as traffic,\r\n"
				+ "(SELECT COUNT(to_path) traffic_count FROM session_page_views s\r\n"
				+ "WHERE s.month =  "+ month +" AND to_path = '/categoryHotel') as hotel,\r\n"
				+ "(SELECT COUNT(to_path) traffic_count FROM session_page_views s\r\n"
				+ "WHERE s.month =  "+ month +" AND to_path = '/categoryTouristSpot') as touristSpot";
		
		List<Map<String, Object>> rawList = jdbcTemplate.queryForList(getRaw);
		String chartName = null;
		String traffic = null;
		String hotel = null;
		String touristSpot = null;
		
		chartName = rawList.get(0).get("chart_name").toString();
		traffic = rawList.get(0).get("traffic").toString();
		hotel = rawList.get(0).get("hotel").toString();
		touristSpot = rawList.get(0).get("touristSpot").toString();

		ChartData data = new ChartData();
		data.setChartName(chartName);
		data.setData1(traffic);
		data.setData2(hotel);
		data.setData3(touristSpot);
		data.setMonth(month);
		Integer id;
		
		if (chartDataRepository.getId(chartName, month) != null) {
			id = chartDataRepository.getId(chartName, month);
			data.setId(id);
			chartDataRepository.save(data);
		} else {
			chartDataRepository.save(data);
		}
	}

	@Transactional
	public void processAndUpdateDataChart3() {
		String getRaw = "SELECT 'chart3' chart_name, SUM(x.total_page)/COUNT(x.sid) avgPages FROM\r\n"
				+ "(SELECT session_id sid, COUNT(to_path) total_page FROM `session_page_views` \r\n" + "WHERE month = "
				+ month + "\r\n" + "group by sid) as x";

		// 抓原始數據
		List<Map<String, Object>> rawList = jdbcTemplate.queryForList(getRaw);
		String chartName = null;
		String avgPages = null;

		chartName = rawList.get(0).get("chart_name").toString();
		avgPages = rawList.get(0).get("avgPages").toString();

		ChartData data = new ChartData();
		data.setChartName(chartName);
		data.setData1(avgPages);
		data.setMonth(month);
		Integer id;

		if (chartDataRepository.getId(chartName, month) != null) {
			id = chartDataRepository.getId(chartName, month);
			data.setId(id);
			chartDataRepository.save(data);
		} else {
			chartDataRepository.save(data);
		}
	}

	@Transactional
	public void processAndUpdateDataChart4() {

		String getRaw = "SELECT 'chart4' chart_name,SUM(x.diff)/COUNT(session_id) avgTime from\r\n"
				+ "(SELECT session_id, MAX(timestamp)-MIN(timestamp) diff FROM `session_page_views`\r\n"
				+ "WHERE month = " + month + "\r\n" + "GROUP BY session_id) as x";

		// 抓原始數據
		List<Map<String, Object>> rawList = jdbcTemplate.queryForList(getRaw);
		String chartName = null;
		String avgTime = null;
		int temp;

		// 取得要的欄位資料
//		System.out.println(rawList.size());
		chartName = rawList.get(0).get("chart_name").toString();
//		System.out.println(2);
		temp = (int) Double.parseDouble(rawList.get(0).get("avgTime").toString());
//		System.out.println(temp);
		avgTime = temp / (60.0) + "";
//		System.out.println("chartName: " + chartName + ",avgTime: " + avgTime + ",month: " + month);

		// 放入資料
		ChartData data = new ChartData();
		data.setChartName(chartName);
		data.setData1(avgTime);
		data.setMonth(month);
		Integer id;

		// 確認要放到的表裏面有同樣資料就update，沒有就insert
		if (chartDataRepository.getId(chartName, month) != null) {
//			System.out.println("Already Exist");
			// 有就抓id並更新
			id = chartDataRepository.getId(chartName, month);
			data.setId(id);
			chartDataRepository.save(data);
		} else {
//			System.out.println("No data");
			chartDataRepository.save(data);
		}

	}
}
