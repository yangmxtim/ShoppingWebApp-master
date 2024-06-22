package com.shoppingwebapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingwebapp.Model.Comment;
import com.shoppingwebapp.Model.Product;
import com.shoppingwebapp.Service.CommentService;
import com.shoppingwebapp.Service.ProductService;

@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = {"Origin", "Content-Type", "Accept"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

@RestController
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@Autowired
    private ProductService productService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	@GetMapping("/comment")
	public List<Comment> findAll(){
		return commentService.findAll();
	}
	
	@PostMapping("/comment")
	public Comment createComment(@RequestBody Comment comment,@RequestParam Integer product_id) {
	    
	    Product product = productService.findById(product_id);
	    if (product == null) {
	        throw new RuntimeException("找不到ID为 " + product_id + " 的产品");
	    }
	    
	    comment.setProduct(product);

	
	    comment.setComment_id(0);


	    Comment savedComment = commentService.save(comment);
	    return savedComment;
	}




	
	@GetMapping("/comment/{comment_id}")
	public Comment getComment(@PathVariable Integer Comment){
		Comment comment = commentService.findById(Comment);
        if(comment == null){
            throw new RuntimeException("note not found");
        }else{
            return comment;
        }
    }
	
	@PutMapping("/comment/{comment_id}")
	public Comment updatecomment(@PathVariable Integer Comment,
						 		@RequestBody Comment comment) {	
		Comment dbComment = commentService.save(comment);
        return dbComment;
	}
	
	@DeleteMapping("/comment/{comment_id}")
	public void deletecomment(@PathVariable Integer Comment) {	
		Comment comment = commentService.findById(Comment);
        if(comment == null){
            throw new RuntimeException("comment not found");
        }else{
        	commentService.deleteByID(Comment);
        }
	}
	
}
