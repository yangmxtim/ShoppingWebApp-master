package com.shoppingwebapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class BPageViewService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void savePageView(String sessionId, String toPath, String month) {
		String sql = "INSERT INTO session_page_views (session_id, to_path, timestamp, month) VALUES (?, ?, CURRENT_TIMESTAMP, ?)";
		jdbcTemplate.update(sql, sessionId, toPath, month);
	}
}