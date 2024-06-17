package com.shoppingwebapp.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shoppingwebapp.Model.ChartData;

import jakarta.transaction.Transactional;

//@Repository
public interface ChartDataRepository extends JpaRepository<ChartData, Integer> {

	@Transactional
	@Query("SELECT c FROM ChartData c WHERE c.chartName = :name AND c.month = :month")
	List<ChartData> findByNameAndMonth(@Param(value = "name") String name,
			@Param(value = "month") String month);
		
	@Transactional
	@Query("SELECT c.id FROM ChartData c WHERE c.chartName = :name AND c.month = :month")
	Integer getId(@Param(value = "name") String name,
			@Param(value = "month") String month);
}