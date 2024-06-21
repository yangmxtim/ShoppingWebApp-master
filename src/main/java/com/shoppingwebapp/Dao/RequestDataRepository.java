package com.shoppingwebapp.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoppingwebapp.Model.RequestData;

@Repository
public interface RequestDataRepository extends JpaRepository<RequestData, Integer> {

	@Query(value = "SELECT * FROM\r\n"
			+ "(SELECT 'pc' system, COUNT(*) FROM `request_data` \r\n"
			+ "WHERE platform like '%windows%' OR platform like  '%macOS%' OR platform like  '%linux%') as x,\r\n"
			+ "(SELECT 'mobile' system, COUNT(*) FROM `request_data` \r\n"
			+ "WHERE platform like '%android%' OR platform like  '%iOS%') as y,\r\n"
			+ "(SELECT 'other' system, COUNT(*) FROM `request_data` \r\n"
			+ "WHERE platform like '%OS%' OR platform like  '%Unknown%') as z", nativeQuery = true)
    String[] findChart8Data();
	
	@Query("SELECT preferLanguage, count(platform) FROM RequestData r "
			+ "group BY preferLanguage")
    List<String[]> findChart7Data();
	
}