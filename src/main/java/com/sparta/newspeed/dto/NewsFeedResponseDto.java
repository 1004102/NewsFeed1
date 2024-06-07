package com.sparta.newspeed.dto;

import com.sparta.newspeed.entity.NewsFeed;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class NewsFeedResponseDto {
    private Long id;
    private String feedId;
    private String contents;
    private LocalDate createdDate;
    private LocalDate modifiedDate;

    public NewsFeedResponseDto(NewsFeed newsFeed) {
        this.id = newsFeed.getId();
        this.feedId = newsFeed.getFeedId();
        this.contents = newsFeed.getContents();
        this.createdDate = newsFeed.getCreatedDate();
        this.modifiedDate = newsFeed.getModifiedDate();
    }
}
