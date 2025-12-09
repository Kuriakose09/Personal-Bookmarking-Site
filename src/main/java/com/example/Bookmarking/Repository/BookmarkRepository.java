package com.example.Bookmarking.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bookmarking.Models.BookMarkingModel;
import com.example.Bookmarking.Models.UserModel;

public interface BookmarkRepository extends JpaRepository<BookMarkingModel, Integer> {
	int countByUser(UserModel user);
	List<BookMarkingModel> findByUser(UserModel user);
	Optional<BookMarkingModel> findByIdAndUser(Long id, UserModel user);
}
