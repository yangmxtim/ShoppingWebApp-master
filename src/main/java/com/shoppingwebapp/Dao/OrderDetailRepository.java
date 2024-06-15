package com.shoppingwebapp.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shoppingwebapp.Model.OrderInfo;
import com.shoppingwebapp.Model.Order_detail;



// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface OrderDetailRepository extends JpaRepository<Order_detail, Integer> {

	@Query("SELECT o FROM Order_detail o JOIN FETCH o.member")
	List<OrderInfo> findAllWithMember();
}
