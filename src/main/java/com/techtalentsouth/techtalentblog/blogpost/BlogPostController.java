package com.techtalentsouth.techtalentblog.blogpost;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BlogPostController {
	@Autowired
	private BlogPostRepository blogPostRepository;
	
	
	@GetMapping(value="/")
	public String index(Model model) {
		List<BlogPost> posts = new ArrayList<>();
		
		for(BlogPost post:blogPostRepository.findAll()) {
			posts.add(post);
		}
		model.addAttribute("posts",posts);
		return "blogpost/index";
	}
	
	@GetMapping(value="/blogposts/new")
	 public String newBlog(Model model, BlogPost blogpost) {
		// default values would be nice
			return "blogpost/new";
		    }
	
	@PostMapping(value="/blogposts")
	public String addNewBlogPost(BlogPost blogPost, Model model) {
		blogPostRepository.save(blogPost);
		
		model.addAttribute("blogPost", blogPost);
		
		return "blogpost/result";
	}
	
	@RequestMapping(value="/blogposts/{id}")
	public String editPostWithId(@PathVariable Long id, Model model) {
		Optional<BlogPost> optionalPost=blogPostRepository.findById(id);
		if(optionalPost.isPresent()) {
			BlogPost post=optionalPost.get();
			model.addAttribute("blogPost", post);
		}
		return "blogpost/edit";
	}
	
	@PostMapping(value="/blogposts/update/{id}")
	public String updateExistionPost(@PathVariable Long id, BlogPost blogPost, Model model) {
		Optional<BlogPost> optionalPost=blogPostRepository.findById(id);
		if(optionalPost.isPresent()) {
			BlogPost post=optionalPost.get();
			post.setTitle(blogPost.getTitle());
			post.setAuthor(blogPost.getAuthor());
			post.setBlogEntry(blogPost.getBlogEntry());
			blogPostRepository.save(post);
			model.addAttribute("blogPost", post);

			}
		return "blogpost/result";
	}
	
	@RequestMapping(value= "/blogposts/delete/{id}")
		public String deletePostWithId(@PathVariable Long id) {
		blogPostRepository.deleteById(id);
		return "blogpost/delete";
	}
	

}