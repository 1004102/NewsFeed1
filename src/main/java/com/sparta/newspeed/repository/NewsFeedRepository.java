package com.sparta.newspeed.repository;

import com.sparta.newspeed.entity.NewsFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsFeedRepository extends JpaRepository<NewsFeed, Long> {
    List<NewsFeed> findAllByOrderByCreatedDateDesc();
    List<NewsFeed> findAllByFeedId(String feedId);

}