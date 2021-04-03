package com.donence.service.abstracts;

import java.util.List;

import com.donence.model.News;

public interface NewsService {
    public boolean addNews(News news);
    public List<News> getAllNews();
    public News getNewsById(Integer id);
}
