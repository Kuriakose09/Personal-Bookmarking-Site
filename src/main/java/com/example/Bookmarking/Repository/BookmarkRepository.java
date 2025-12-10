package com.example.Bookmarking.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Bookmarking.Models.BookMarkingModel;
import com.example.Bookmarking.Models.UserModel;

public interface BookmarkRepository extends JpaRepository<BookMarkingModel, Integer> {
	int countByUser(UserModel user);
	List<BookMarkingModel> findByUser(UserModel user);
	Optional<BookMarkingModel> findByIdAndUser(Long id, UserModel user);
	@Query("SELECT b FROM BookMarkingModel b " +
		       "WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "OR LOWER(b.url) LIKE LOWER(CONCAT('%', :keyword, '%'))")
		Page<BookMarkingModel> searchBookmarks(String keyword, Pageable pageable);

}
