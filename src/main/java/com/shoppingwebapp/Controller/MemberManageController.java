package com.shoppingwebapp.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingwebapp.Model.Member;
import com.shoppingwebapp.Service.MemberManageService;

@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = { "Origin", "Content-Type", "Accept" }, methods = {
		RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })

@RestController
public class MemberManageController {
	
	MemberManageService memberManageService;

	public MemberManageController(MemberManageService memberManageService) {
		this.memberManageService = memberManageService;
	}

	@GetMapping("/memberManage")
	public List<Member> findBySearch(@RequestParam(value="q",required = false) String searchText) {
		System.out.println("findBySearch()");
		if(searchText == null || searchText.isEmpty()) {
			return memberManageService.findAll();
		}
		return memberManageService.findBySearch(searchText);
	}
	
	@GetMapping("/memberManage/{id}")
	public ResponseEntity<Member> findById(@PathVariable(value="id",required = true) int id) {
		System.out.println("findById()");
		Member member = memberManageService.findById(id);
		ResponseEntity<Member> re = new ResponseEntity<>(member, HttpStatus.OK);
		return re;
	}
	
	@GetMapping("/memberManage/permission/{isAdmin}")
	public List<Member> findByPermission(@PathVariable Boolean isAdmin){
		System.out.println("findByPermission()");
		return memberManageService.findByPermission(isAdmin);
	}
	
	// 更新會員資料
	@PutMapping("/memberManage/{id}")
	public ResponseEntity<Member> updateById(@RequestBody Member bean) {
		System.out.println("updateById()");
		Member updatedBean = memberManageService.update(bean);
		ResponseEntity<Member> re = new ResponseEntity<>(updatedBean, HttpStatus.OK);
		return re;
	}
	
}
