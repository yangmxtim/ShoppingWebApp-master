package com.shoppingwebapp.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingwebapp.Dao.Commentrepository;
import com.shoppingwebapp.Model.Comment;
import com.shoppingwebapp.Model.Product_detail;
import com.shoppingwebapp.Service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
	private Commentrepository commentrepository;
	
	
	
	public CommentServiceImpl(Commentrepository commentrepository) {
		this.commentrepository = commentrepository;
	}

	@Override
	public List<Comment> findAll() {
		return commentrepository.findAll();
	}

	@Override
	public Comment findById(int id) {
		Optional<Comment> commentlist = commentrepository.findById(id); 
		Comment comment = null;
        if(commentlist.isPresent()) {
        	comment = commentlist.get();
        }else{
            throw new RuntimeException("not found");
        }
        return comment;
	}
	@Transactional
	@Override
	public Comment save(Comment comment) {
		return commentrepository.save(comment);
	}

	@Override
	public void deleteByID(Integer id) {
		commentrepository.deleteById(id);
		
	}
	
}
