package com.sparta.newspeed.entity;

import com.sparta.newspeed.dto.NewsFeedRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "newsfeed")
@NoArgsConstructor
public class NewsFeed extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String feedId;
    private String contents;

    public NewsFeed(NewsFeedRequestDto newsFeedRequestDto) {
        this.feedId = newsFeedRequestDto.getFeedId();
        this.contents = newsFeedRequestDto.getContents();
    }

    public void update(NewsFeedRequestDto newsFeedRequestDto) {
        this.feedId = newsFeedRequestDto.getFeedId();
        this.contents = newsFeedRequestDto.getContents();
    }
}