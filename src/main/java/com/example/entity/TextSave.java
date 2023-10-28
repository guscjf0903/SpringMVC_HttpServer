package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@Setter
@Getter
public class TextSave {
    @Id
    private String textId;
    private String text;
}
