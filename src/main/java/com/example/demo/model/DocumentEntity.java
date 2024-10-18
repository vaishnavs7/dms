package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "documents")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentEntity {
    @Id
    @JsonIgnore
    private String id;
    private String documentId;
    private String name;
    private String type;  // PDF, DOCX, etc.
    private byte[] content;
    private LocalDateTime uploadDate;
    private String uploader;
    
    // Constructors, Getters, Setters, etc.
}
