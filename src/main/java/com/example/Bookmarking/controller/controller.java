package com.example.Bookmarking.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Bookmarking.DTO.UserDto;
import com.example.Bookmarking.Models.UserModel;
import com.example.Bookmarking.DTO.BookmarkDTO;
import com.example.Bookmarking.Service.BookmarkService;
import com.example.Bookmarking.Service.UserService;
import com.example.Bookmarking.Models.BookMarkingModel;


@Controller
public class controller {
	@Autowired
	UserService userService;
	@Autowired
	BookmarkService bookmarkService;
	
	@GetMapping("/registration")
	public String registor(Model model) {
		model.addAttribute("message", "Register User!");
		 model.addAttribute("user", new UserDto());
		return "registor";
	}


	@PostMapping("/registration")
	public String saveUser(@ModelAttribute("UserModel") UserDto userDto, Model model) {
	    userService.save(userDto);
	    model.addAttribute("message", "Registered Successfuly!");
	    return "login";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	
	
	@GetMapping("/addBookMark")
	public String addBookMark() {
		return "addBookMark";
	}
	
	@PostMapping("/addBookMark")
	public String saveBookmark(BookmarkDTO bookdto, Principal principal, Model model){
		UserModel user = userService.findByEmail(principal.getName());

	    try {
	        bookmarkService.addBookmark(bookdto, user);
	        return "redirect:listBookMark";
	    } 
	    catch (RuntimeException e) {
	        model.addAttribute("error", e.getMessage());
	        return "addBookMark";
	    }
	}
	
	@GetMapping("/listBookMark")
	public String listBookmarks(@RequestParam(defaultValue = "0") int page, Model model) {

	    int pageSize = 5;  // 5 per page

	    Page<BookMarkingModel> bookmarkPage = bookmarkService.getAllBookmarks(page, pageSize);

	    model.addAttribute("bookmarks", bookmarkPage.getContent());
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", bookmarkPage.getTotalPages());

	    return "bookmarkList";
	}
	
	 @GetMapping("/profile")
	    public String profile(Model model, Principal principal) {

	        UserModel user = userService.findByEmail(principal.getName());

	        model.addAttribute("user", user);
	        model.addAttribute("bookmarks", bookmarkService.getBookmarksOfUser(user));

	        return "profile";   // profile.html
	    }
	// Delete bookmark
	    @GetMapping("/profile/delete/{id}")
	    public String deleteBookmark(@PathVariable Long id, Principal principal) {

	        UserModel user = userService.findByEmail(principal.getName());
	        bookmarkService.deleteBookmark(id, user);

	        return "redirect:/profile";
	    }

	    // Load edit section inside same page
	    @GetMapping("/profile/edit/{id}")
	    public String editBookmark(@PathVariable Long id, Model model, Principal principal) {

	        UserModel user = userService.findByEmail(principal.getName());
	        BookMarkingModel bm = bookmarkService.getBookmarkForEdit(id, user);

	        model.addAttribute("user", user);
	        model.addAttribute("editBookmark", bm);
	        model.addAttribute("bookmarks", bookmarkService.getBookmarksOfUser(user));

	        return "edit";
	    }

	    // Save edited bookmark
	    @PostMapping("/profile/update/{id}")
	    public String updateBookmark(@PathVariable Long id,
	                                 @ModelAttribute BookmarkDTO dto,
	                                 Principal principal) {

	        UserModel user = userService.findByEmail(principal.getName());
	        bookmarkService.updateBookmark(id, dto, user);

	        return "redirect:/profile";
	    }


}