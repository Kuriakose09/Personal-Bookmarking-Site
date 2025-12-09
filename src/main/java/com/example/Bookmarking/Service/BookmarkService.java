package com.example.Bookmarking.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.Bookmarking.DTO.BookmarkDTO;
import com.example.Bookmarking.Models.BookMarkingModel;
import com.example.Bookmarking.Models.UserModel;
import com.example.Bookmarking.Repository.*;

@Service
public class BookmarkService {
	@Autowired
	BookmarkRepository BookmarkRepo;
	
	public void addBookmark(BookmarkDTO Bookmarkdto, UserModel user) {
		int count = BookmarkRepo.countByUser(user);
		
		if(count > 5) {
			  throw new RuntimeException("You can add only 5 bookmarks!");
		}
		BookMarkingModel bookmark = new BookMarkingModel(Bookmarkdto.getTitle(),Bookmarkdto.getUrl());
		bookmark.setUser(user);
		bookmark.setCreatedAt(LocalDateTime.now());
        BookmarkRepo.save(bookmark);
	}	
	
	  public Page<BookMarkingModel> getAllBookmarks(int page, int size) {
	        return BookmarkRepo.findAll(PageRequest.of(page, size));
	    }
}
