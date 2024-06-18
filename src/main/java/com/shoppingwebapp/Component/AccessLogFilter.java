//package com.shoppingwebapp.Component;
//
//import java.io.IOException;
//import java.util.logging.Logger;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.shoppingwebapp.Dao.RequestDataRepository;
//import com.shoppingwebapp.Model.RequestData;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Component
//public class AccessLogFilter extends OncePerRequestFilter {
//
//	private static final Logger logger = Logger.getLogger(AccessLogFilter.class.getName());
//
//	@Autowired
//	private RequestDataRepository requestRe;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//
//		try {
//			String path = request.getRequestURI();
//			String preferLanguage = request.getHeader("Accept-Language");
//			String platform = request.getHeader("Sec-Ch-Ua-Platform");
//			String ipAddress = request.getRemoteAddr();
//
//			if (preferLanguage != null) {
//				String[] arr = preferLanguage.split(";");
//				preferLanguage = arr[0];
//			}
//
//			// 記錄訪問訊息
//			logger.info("Path: " + path + ", preferLanguage: " + preferLanguage + ", platform: " + platform
//					+ ", IP Address: " + ipAddress);
//
//			if (requestRe != null && path.equals("/products")) {
//				RequestData requestData = new RequestData();
//				requestData.setIp(ipAddress);
//				requestData.setPath(path);
//				requestData.setPlatform(platform);
//				requestData.setPreferLanguage(preferLanguage);
//				requestRe.save(requestData);
//			}
//		} catch (Exception e) {
//			logger.severe("Exception in AccessLogFilter: " + e.getMessage());
//		}
//	}
//}