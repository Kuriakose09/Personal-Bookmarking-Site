package com.example.Bookmarking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bookmarking.Models.BookMarkingModel;
import com.example.Bookmarking.Models.UserModel;

public interface BookmarkRepository extends JpaRepository<BookMarkingModel, Integer> {
	int countByUser(UserModel user);
}
