package com.shoppingwebapp.Service;

import java.util.List;
import java.util.Optional;

import com.shoppingwebapp.Model.Member;

public interface MemberManageService {
	
	Member findById(int id);
	
	Iterable<Member> findBySearch(String searchText);

	List<Member> findAll();

	Member save(Member bean);
	
	Member update(Member bean); 

	void deleteById(Integer key);
	
	List<Member> findByPermission(boolean isAdmin);
}
