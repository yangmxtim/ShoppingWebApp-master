package com.shoppingwebapp.Dao;

import com.shoppingwebapp.Model.Member;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface MemberRepository extends CrudRepository<Member, Integer> {
    Iterable<Member> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE Member m SET m.img = :img WHERE m.id = :id")
    int updateImgById(@Param("id") Integer id, @Param("img") byte[] base64Code);


    Member findMemberById(Integer id);
}
