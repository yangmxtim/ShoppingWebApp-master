package com.shoppingwebapp.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingwebapp.Model.Member;
import com.shoppingwebapp.Service.MemberManageService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = { "Origin", "Content-Type", "Accept" }, methods = {
		RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })

@RestController
public class BMemberManageController {

	MemberManageService memberManageService;

	public BMemberManageController(MemberManageService memberManageService) {
		this.memberManageService = memberManageService;
	}
	
	@GetMapping("/memberManage")
	public Iterable<Member> findBySearch(@RequestParam(value = "q", required = false) String searchText) {
		System.out.println("findBySearch()");
		if (searchText == null || searchText.isEmpty()) {
			return memberManageService.findAll();
		}
		return memberManageService.findBySearch(searchText);
	}

	@GetMapping("/memberManage/{id}")
	public ResponseEntity<Member> findById(@PathVariable(value = "id", required = true) int id) {
		System.out.println("findById()");
		Member member = memberManageService.findById(id);
		ResponseEntity<Member> re = new ResponseEntity<>(member, HttpStatus.OK);
		return re;
	}

	@GetMapping("/memberManage/permission/{isAdmin}")
	public List<Member> findByPermission(@PathVariable Boolean isAdmin) {
		System.out.println("findByPermission()");
		return memberManageService.findByPermission(isAdmin);
	}

	// 更新會員資料
	@PutMapping("/memberManage")
	public ResponseEntity<Member> updateById(@RequestBody Member bean) {
		System.out.println("updateById()");
		Integer id = bean.getId();
		Member updatedMember = memberManageService.findById(id);
//		System.out.println("Unupdated member : " + updatedMember.toString());
		updatedMember.setUsername(bean.getUsername());
		updatedMember.setEmail(bean.getEmail());
		updatedMember.setPhone(bean.getPhone());
		updatedMember.setAdmin(bean.getAdmin());

		Member result = memberManageService.update(updatedMember);
//		System.out.println("updated member : " + result);
		ResponseEntity<Member> re = new ResponseEntity<>(result, HttpStatus.OK);
		return re;
	}

	@GetMapping("/example")
    public String captureUserInfo(HttpServletRequest request,
                                  @RequestHeader(name = "User-Agent", required = false) String userAgent,
                                  @RequestHeader(name = "X-Forwarded-For", required = false) String xForwardedFor,
                                  @RequestParam Map<String, String> parameters) {

        String userIp = getClientIp(request, xForwardedFor);

        System.out.println("User-Agent: " + userAgent);
        System.out.println("User IP: " + userIp);
        System.out.println("Parameters: " + parameters.toString());

        return "User information captured.";
    }

    private String getClientIp(HttpServletRequest request, String xForwardedFor) {
        if (xForwardedFor != null && !xForwardedFor.isEmpty() && !"unknown".equalsIgnoreCase(xForwardedFor)) {
            return xForwardedFor.split(",")[0];
        }
        return request.getRemoteAddr();
    }
}
