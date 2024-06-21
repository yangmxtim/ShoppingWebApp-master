package com.shoppingwebapp.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingwebapp.Dao.ChartDataRepository;
import com.shoppingwebapp.Dao.SessionPageViewsRepository;
import com.shoppingwebapp.Model.ChartData;

@Service
public class BDataManageService {
	
	@Autowired
	SessionPageViewsRepository sessionRe;
	
	@Autowired
	ChartDataRepository chartRe;

	public List<ArrayList<Integer>> findFlowCount() {
		return sessionRe.findFlowCount();
	}
	
	public String findChart1AvgPages() {
		List<Integer> IntList = sessionRe.findAvgPages();
		Integer totalPages = 0;
		Double numOfData = 0.0;
		for(Integer integer: IntList) {
			totalPages += integer;
			numOfData++;
		}
		String avgStr =  String.format("%.2f", totalPages/numOfData);
		System.out.println("totalPages: " + totalPages + ", numOfData: " + numOfData + ", avgStr: " + avgStr );
		return avgStr;
	}
	
	
	public List<ChartData> findChartData(String name) {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
		String month = currentDate.format(formatter);
		
		return chartRe.findByNameAndMonth(name, month);
	}
	
	public List<ChartData> findChartData2(String name) {
		return chartRe.findByName(name);
	}
}
