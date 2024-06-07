package com.sparta.newspeed.controller;

import com.sparta.newspeed.dto.NewsFeedRequestDto;
import com.sparta.newspeed.dto.NewsFeedResponseDto;
import com.sparta.newspeed.entity.NewsFeed;
import com.sparta.newspeed.service.NewsFeedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/newsfeed")
public class NewsFeedController {
    private final NewsFeedService newsFeedService;

    public NewsFeedController(NewsFeedService newsFeedService) {
        this.newsFeedService = newsFeedService;
    }

    @PostMapping
    public NewsFeedResponseDto createNewsFeed(@RequestBody NewsFeedRequestDto newsFeedRequestDto) {
        return newsFeedService.createNewsFeed(newsFeedRequestDto);
    }

    @GetMapping     // 게시물 전체 조회 및 생성일자 기준 최신순
    public List<NewsFeedResponseDto> getNewsFeeds() {
        return newsFeedService.getNewsFeeds();
    }

    @GetMapping("/search")
    public List<NewsFeedResponseDto> getFeedId(@RequestParam String feedId) {
        return newsFeedService.getFeedId(feedId);
    }

    @PutMapping("/{id}")
    public NewsFeed updateNewsFeed(@PathVariable Long id, @RequestBody NewsFeedRequestDto newsFeedRequestDto) {
        return newsFeedService.updateNewsFeed(id, newsFeedRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNewsFeed(@PathVariable Long id, @RequestBody NewsFeedRequestDto newsFeedRequestDto) {
        return newsFeedService.deleteNewsFeed(id, newsFeedRequestDto);
    }

    @GetMapping("/find")
    public ResponseEntity<?> getAllNewsFeeds() {
        List<NewsFeedResponseDto> newsFeeds = newsFeedService.getAllNewsFeeds();
        if (newsFeeds.isEmpty()) {
            return ResponseEntity.ok("먼저 작성하여 소식을 알려보세요!");
        } else return ResponseEntity.ok(newsFeeds);
    }
}