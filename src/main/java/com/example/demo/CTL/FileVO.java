package com.example.demo.CTL;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FileVO {

    private String id;
    private String pw;
    private String name;


    @Builder
    public FileVO(String id, String pw, String name) {
        this.id = id;
        this.pw = pw;
        this.name = name;
    }
}
