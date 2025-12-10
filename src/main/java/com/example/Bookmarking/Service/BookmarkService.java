package com.example.Bookmarking.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.Bookmarking.DTO.BookmarkDTO;
import com.example.Bookmarking.Models.BookMarkingModel;
import com.example.Bookmarking.Models.UserModel;
import com.example.Bookmarking.Repository.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


@Service
public class BookmarkService {
	@Autowired
	BookmarkRepository BookmarkRepo;
	
	public void addBookmark(BookmarkDTO Bookmarkdto, UserModel user) {
		int count = BookmarkRepo.countByUser(user);
		
		if(count > 4) {
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
	  public List<BookMarkingModel> getBookmarksOfUser(UserModel user) {
		    return BookmarkRepo.findByUser(user);
		}

		public void deleteBookmark(Long id, UserModel user) {
		    BookMarkingModel bookmark = BookmarkRepo.findByIdAndUser(id, user)
		            .orElseThrow(() -> new RuntimeException("You can delete only your own bookmarks!"));

		    BookmarkRepo.delete(bookmark);
		}

		public BookMarkingModel getBookmarkForEdit(Long id, UserModel user) {
		    return BookmarkRepo.findByIdAndUser(id, user)
		            .orElseThrow(() -> new RuntimeException("You can edit only your own bookmarks!"));
		}

		public void updateBookmark(Long id, BookmarkDTO dto, UserModel user) {
		    BookMarkingModel bookmark = BookmarkRepo.findByIdAndUser(id, user)
		            .orElseThrow(() -> new RuntimeException("You can edit only your own bookmarks!"));

		    bookmark.setTitle(dto.getTitle());
		    bookmark.setUrl(dto.getUrl());

		    BookmarkRepo.save(bookmark);
		}
		public Page<BookMarkingModel> getAllBookmarks(int page, int size, String keyword) {

		    Pageable pageable = PageRequest.of(page, size);

		    // If search text entered → search
		    if (keyword != null && !keyword.isEmpty()) {
		        return BookmarkRepo.searchBookmarks(keyword, pageable);
		    }

		    // If search empty → show all bookmarks
		    return BookmarkRepo.findAll(pageable);
		}



}
