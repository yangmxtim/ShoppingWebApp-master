package com.shoppingwebapp.Service;

import java.util.List;

import com.shoppingwebapp.Model.Comment;
import com.shoppingwebapp.Model.Product_detail;

public interface CommentService {

	List<Comment> findAll();

	Comment findById(int id);

	Comment save(Comment comment);
    
    void deleteByID(Integer id);
}
