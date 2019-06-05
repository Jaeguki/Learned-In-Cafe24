package com.cafe24.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.service.CategoryService;
import com.cafe24.jblog.service.FileUploadService;
import com.cafe24.jblog.service.PostService;
import com.cafe24.jblog.vo.BlogVO;
import com.cafe24.jblog.vo.CategoryVO;
import com.cafe24.jblog.vo.PostVO;
import com.cafe24.jblog.vo.UserVO;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
@RequestMapping("/{id:(?!assets|images).*}")
public class BlogController {
	@Autowired
	BlogService blogService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	PostService postService;
	
	@Autowired
	FileUploadService fileuploadService;
	
	@RequestMapping( {"", "/{pathNo1}", "/{pathNo1}/{pathNo2}" } )
	public String index( @PathVariable String id, @PathVariable Optional<Long> pathNo1, @PathVariable Optional<Long> pathNo2, Model model) {
		BlogVO blogVO = blogService.getBlogInfo(id);
		List<CategoryVO> categoryVOList = categoryService.getCategoryListByID(id);
		List<PostVO> postVOList = null;
		PostVO postVO = null;
		Long categoryNo = 0L;
		Long postNo = 0L;
		if(pathNo1.isPresent() && !(pathNo2.isPresent())) {
			System.out.println("path1");
			categoryNo = pathNo1.get();
			// categoryVOList = categoryService.getCategoryListByID(id);
			postVOList = postService.getPostListByCategoryNo(categoryNo);
		}else if(pathNo1.isPresent() && (pathNo2.isPresent())){
			System.out.println("path2");
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
			// categoryVOList = categoryService.getCategoryListByID(id);
			postVOList = postService.getPostListByCategoryNo(categoryNo);
			postVO = postService.getPostByVO(new PostVO(postNo, categoryNo));
		}else {
			System.out.println("basic path");
			postVOList = postService.getPostListByID(id);
		}
		model.addAttribute( "pathID", id);
		model.addAttribute( "blogVO", blogVO );
		model.addAttribute( "categoryVOList", categoryVOList );
		model.addAttribute( "postVOList", postVOList );
		model.addAttribute( "postVO", postVO);
		
		System.out.println(blogVO);
		System.out.println(categoryVOList);
		System.out.println(postVOList);
		System.out.println(postVO);
		
		return "blog/blog-main";

		
	}

	@Auth
	@RequestMapping(value="admin/basic", method=RequestMethod.GET)
	public String basic(@PathVariable String id, @AuthUser UserVO authUser, Model model) {
		if( !authUser.getId().equals(id)) {
			System.out.println(authUser.getId());
			System.out.println(id);
			System.out.println("올바르지 않은 접근");
			return "error/error-occurred";
		}
		BlogVO blogVO = blogService.getBlogInfo(id);
		model.addAttribute( "blogVO", blogVO );
		return "blog/blog-admin-basic";
	}

	@RequestMapping(value="admin/basic", method=RequestMethod.POST)
	public String basic(@PathVariable String id, Model model, @RequestParam(value="title", required=true, defaultValue="") String title, @RequestParam(value="logo-file") MultipartFile logoFile, @ModelAttribute BlogVO blogVO) {
		System.out.println("admin basic post call");
		System.out.println(title);
		System.out.println(fileuploadService.restore(logoFile));
		blogVO.setLogo(fileuploadService.restore(logoFile));
		blogService.setBlogInfo(blogVO);
		blogVO = blogService.getBlogInfo(id);
		System.out.println(blogVO);
		model.addAttribute( "blogVO", blogVO );
		return "blog/blog-admin-basic";
	}
	
	@Auth
	@RequestMapping(value="admin/write", method=RequestMethod.GET)
	public String write(@PathVariable String id, @AuthUser UserVO authUser, Model model) {
		if( !authUser.getId().equals(id)) {
			System.out.println(authUser.getId());
			System.out.println(id);
			System.out.println("올바르지 않은 접근");
			return "error/error-occurred";
		}
		System.out.println("read write method call");
		List<CategoryVO> categoryVOList = categoryService.getCategoryListByID(id);
		BlogVO blogVO = blogService.getBlogInfo(id);
		model.addAttribute( "blogVO", blogVO );
		model.addAttribute( "categoryVOList", categoryVOList);
		return "blog/blog-admin-write";
	}
	
	@RequestMapping(value="admin/write", method=RequestMethod.POST)
	public String write(@PathVariable String id, Model model, @ModelAttribute PostVO postVO, @RequestParam(value="categoryNo", required=true, defaultValue="") Long categoryNo) {
		System.out.println("create post method call");
		List<CategoryVO> categoryVOList = categoryService.getCategoryListByID(id);
		//Long categoryNo = categoryService.getCategoryNoByName(categoryName);
		BlogVO blogVO = blogService.getBlogInfo(id);
		model.addAttribute( "blogVO", blogVO );
		postVO.setCategoryNo(categoryNo);
		postService.addPostByByVo(postVO);
		model.addAttribute( "postVO", postVO );
		model.addAttribute( "categoryVOList", categoryVOList);
		return "blog/blog-admin-write";
	}
	
	@Auth
	@RequestMapping(value="admin/category", method=RequestMethod.GET)
	public String category(@PathVariable String id, @AuthUser UserVO authUser, Model model) {
		if( !authUser.getId().equals(id)) {
			System.out.println(authUser.getId());
			System.out.println(id);
			System.out.println("올바르지 않은 접근");
			return "error/error-occurred";
		}
		System.out.println("read categoryList method call");
		List<CategoryVO> categoryVOList = categoryService.getCategoryListByID(id);
		BlogVO blogVO = blogService.getBlogInfo(id);
		model.addAttribute( "blogVO", blogVO );
		model.addAttribute( "categoryVOList", categoryVOList);
		return "blog/blog-admin-category";
	}
	
	@RequestMapping(value="admin/category", method=RequestMethod.POST)
	public String category(@PathVariable String id, Model model, @ModelAttribute CategoryVO categoryVO) {
		System.out.println("create category method call");
		categoryVO.setBlogID(id);
		categoryService.addCategoryByVO(categoryVO);
		return "redirect:/" +id + "/admin/category";
	}
	
	@RequestMapping(value="admin/category/delete", method=RequestMethod.POST)
	public String deleteCategory(@PathVariable String id, Model model, @ModelAttribute CategoryVO categoryVO) {
		System.out.println("delete category method call");
		System.out.println("delete post -> delete category");
		categoryVO.setBlogID(id);
		System.out.println(categoryVO);
		postService.removeAllPostByCategory(categoryVO.getNo());
		categoryService.removeCategoryByVO(categoryVO);
		return "redirect:/" + id + "/admin/category";
	}
	

}
