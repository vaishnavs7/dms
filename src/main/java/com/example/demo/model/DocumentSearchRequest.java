package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentSearchRequest {
    private String fromDate;
    private String toDate;
    private String uploader;
    private String fileName;
    private String fileType;

}
