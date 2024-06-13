package com.shoppingwebapp.Dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingwebapp.Model.Comment;
import com.shoppingwebapp.Model.Product;

public interface Commentrepository extends JpaRepository<Comment, Integer> {

}
