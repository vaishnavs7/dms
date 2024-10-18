package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentListResponse {
    private String documentId;
    private String name;
    private String type;
    private LocalDateTime uploadDate;
    private String uploader;
}
