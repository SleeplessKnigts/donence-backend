package com.donence.repository;

import com.donence.model.News;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Integer>{
    
}
