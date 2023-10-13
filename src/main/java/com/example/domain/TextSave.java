package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class TextSave {
    private String textId;
    private String text;
    public String getTextId() {
        return textId;
    }
    public String getText() {
        return text;
    }
}
