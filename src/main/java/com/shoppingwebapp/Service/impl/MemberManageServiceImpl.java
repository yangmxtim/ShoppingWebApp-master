 package com.shoppingwebapp.Service.impl;

 import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingwebapp.Dao.MemberManageRepository;
import com.shoppingwebapp.Dao.MemberRepository;
import com.shoppingwebapp.Model.Member;
import com.shoppingwebapp.Service.MemberManageService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

 @Service
 public class MemberManageServiceImpl implements MemberManageService {

 	@PersistenceContext
 	private EntityManager entityManager;

 	@Autowired
 	MemberManageRepository memberManageRepository;

// 	@Override
// 	public List<Member> findBySearch(String searchText) {
// 		System.out.println("findBySearch()");
// 		return memberManageRepository.findBySearch(searchText);
// 	}

 	@Override
 	public List<Member> findAll() {
 		return (List<Member>) memberManageRepository.findAll();
 	}

 	@Override
 	public Member save(Member bean) {
 		return memberManageRepository.save(bean);
 	}

 	@Override
 	public Member update(Member bean) {
 		return memberManageRepository.save(bean);
 	}

 	@Override
 	public void deleteById(Integer id) {
 		memberManageRepository.deleteById(id);
 	}

 	@Override
 	public List<Member> findByPermission(boolean isAdmin) {
 		return memberManageRepository.findByPermission(isAdmin);
 	}

 	@Override
 	public Member findById(int id) {
 		Optional<Member> result = memberManageRepository.findById(id);
 		Member member = null;
         if(result.isPresent()) {
             member = result.get();
         }else{
             throw new RuntimeException("not found");
         }
         return member;
 	}

	@Override
	public Iterable<Member> findBySearch(String searchText) {
		return memberManageRepository.findAll();
	}


 }
