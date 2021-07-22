package com.example.adminStudy.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {

    // api 통신시간
    private LocalDateTime transactionTime;

    // api 응답코드
    private String resultCode;

    // api 부가설명
    private String description;

    private T data;

    // OK
    public static <T> Header<T> OK () {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }

    // DATA OK
    public static <T> Header<T> OK (T data) {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }

    // ERROR
    public static <T> Header<T> ERROR (String description) {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build();
    }

    // 2차 피드백 과제
    public static <T> Header<T> EMAILEXIST () {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description("이미 존재하는 이메일 입니다.")
                .build();
    }
}
