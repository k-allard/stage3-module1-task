package com.mjc.school.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NewsCreateDTORequest {
    String title;
    String content;
    Long authorId;
}
