package com.shoppingwebapp.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingwebapp.Service.BPageViewService;

@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = { "Origin", "Content-Type", "Accept" }, methods = {
		RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
public class BPageViewController {

	@Autowired
    private BPageViewService pageViewService;

	private Map<String, Integer> sessionPageCounts = new HashMap<>();

	@PostMapping("/track-page-view")
	public void trackPageView(@RequestBody Map<String, String> payload) {
//		System.out.println("BPageViewController: track-page-view()");
		String sessionId = payload.get("sessionId");
		String toPath = payload.get("category");
		String month = payload.get("month");
//		System.out.println("sessionId: " + payload.get("sessionId") + "category: " + payload.get("category"));
		getSessionPageCounts().put(sessionId, getSessionPageCounts().getOrDefault(sessionId, 0) + 1);
		pageViewService.savePageView(sessionId, toPath, month);
	}

	public Map<String, Integer> getSessionPageCounts() {
		return sessionPageCounts;
	}

	public void setSessionPageCounts(Map<String, Integer> sessionPageCounts) {
		this.sessionPageCounts = sessionPageCounts;
	}
}