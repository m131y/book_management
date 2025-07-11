package com.my131.book_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    private Integer id;

    @NotBlank(message = "제목을 입력하세요.")
    private String title;

    @NotNull(message = "저자 ID를 지저하세요.")
    private Integer authorId;
}
