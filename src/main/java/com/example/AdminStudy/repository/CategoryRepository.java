package com.example.AdminStudy.repository;

import com.example.AdminStudy.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // select * from category where type = 'COMPUTER'
    Optional<Category> findByType (String type);
}
