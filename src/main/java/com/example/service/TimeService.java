package com.example.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimeService {
    public String getTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.toString();
    }
}
