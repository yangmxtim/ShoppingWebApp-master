package com.shoppingwebapp.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shoppingwebapp.Model.SessionPageViews;

import jakarta.transaction.Transactional;

public interface SessionPageViewsRepository extends JpaRepository<SessionPageViews, Integer> {

	@Transactional
    @Query("SELECT DAY(s.timestamp) AS d, SUM(1) AS constSum FROM SessionPageViews s GROUP BY DAY(s.timestamp)")
	List<ArrayList<Integer>> findFlowCount();
	
	@Transactional
    @Query("SELECT COUNT(to_path) FROM SessionPageViews GROUP BY session_id")
	List<Integer> findAvgPages();
	
}
