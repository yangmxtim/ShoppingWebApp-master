package com.shoppingwebapp.Dao;

import com.shoppingwebapp.Model.Member;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MemberRepository extends CrudRepository<Member, Integer> {
    Iterable<Member> findByUsername(String username);

    @Transactional
    @Query("SELECT m FROM Member m WHERE CAST(m.id AS string) LIKE %:searchText% " +
           "OR m.email LIKE %:searchText% " +
           "OR m.username LIKE %:searchText%")
    List<Member> findBySearch(@Param("searchText") String searchText);
    
    @Transactional
    @Query("SELECT m FROM Member m WHERE m.isAdmin = :isAdmin")
    List<Member> findByPermission(@Param("isAdmin") Boolean isAdmin);

}
