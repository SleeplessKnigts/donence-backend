package com.donence.service.concretes;

import com.donence.model.News;
import com.donence.repository.NewsRepository;
import com.donence.service.abstracts.NewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsRepository newsRepository;
    
    public boolean addNews(News news) {
        News returnVal = newsRepository.save(news);
        return returnVal == null ? false : true;
    }
}
