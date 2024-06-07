package com.sparta.newspeed.service;

import com.sparta.newspeed.dto.NewsFeedRequestDto;
import com.sparta.newspeed.dto.NewsFeedResponseDto;
import com.sparta.newspeed.entity.NewsFeed;
import com.sparta.newspeed.repository.NewsFeedRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsFeedService {
    private final NewsFeedRepository newsFeedRepository;

    public NewsFeedService(NewsFeedRepository newsFeedRepository) {
        this.newsFeedRepository = newsFeedRepository;
    }

    public NewsFeedResponseDto createNewsFeed(NewsFeedRequestDto newsFeedRequestDto) {
        // RequestDto -> Entity
        NewsFeed newsFeed = new NewsFeed(newsFeedRequestDto);
        // DB 저장
        NewsFeed savedNewsFeed = newsFeedRepository.save(newsFeed);
        // Entity -> ResponseDto
        return new NewsFeedResponseDto(savedNewsFeed);
    }

        // 젠체 게시물 생성일자 최신순
    public List<NewsFeedResponseDto> getNewsFeeds() {
        return newsFeedRepository.findAllByOrderByCreatedDateDesc().stream()
                .map(NewsFeedResponseDto::new)
                .toList();
    }

        // 게시물 조회
    public List<NewsFeedResponseDto> getFeedId(String feedId) {
        return newsFeedRepository.findAllByFeedId(feedId).stream()
                .map(NewsFeedResponseDto::new)
                .toList();
    }

        // 게시물 수정
    @Transactional
    public NewsFeed updateNewsFeed(Long id, NewsFeedRequestDto newsFeedRequestDto) {
        NewsFeed newsFeed = findNewsFeedById(id);
        newsFeed.update(newsFeedRequestDto);
        return newsFeed;
    }

        // 게시물 삭제
    public ResponseEntity<String> deleteNewsFeed(Long id, NewsFeedRequestDto newsFeedRequestDto) {
        NewsFeed newsFeed = findNewsFeedById(id);
        newsFeedRepository.delete(newsFeed);
        return ResponseEntity.ok("게시물이 삭제되었습니다.");
    }

    public NewsFeed findNewsFeedById(Long id) {
        return newsFeedRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("선택한 게시물이 존재하지 않습니다.")
        );
    }

        // 뉴스피드 조회 기능 : 뉴스피드가 없을 경우 메세지 표시
    public List<NewsFeedResponseDto> getAllNewsFeeds() {
        List<NewsFeed> newsFeeds = newsFeedRepository.findAllByOrderByCreatedDateDesc();
        return newsFeeds.stream()
                .map(NewsFeedResponseDto::new)
                .collect(Collectors.toList());
    }
}
