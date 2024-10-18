package com.example.demo.service;

import com.example.demo.exceptions.DocumentNotFoundException;
import com.example.demo.exceptions.GeneralException;
import com.example.demo.model.DocumentEntity;
import com.example.demo.model.DocumentListResponse;
import com.example.demo.model.DocumentSearchRequest;
import com.example.demo.model.DocumentUploadResponse;
import com.example.demo.repo.DocumentsRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DocumentService {
    private final DocumentsRepo documentsRepo;
    private final ModelMapper modelMapper;

    public DocumentService(DocumentsRepo documentsRepo, ModelMapper modelMapper) {
        this.documentsRepo = documentsRepo;
        this.modelMapper = modelMapper;
    }

    public DocumentUploadResponse documentUpload(MultipartFile file, String uploader) {
        DocumentEntity document = new DocumentEntity();
        document.setDocumentId(RandomStringUtils.randomAlphanumeric(15).toUpperCase());
        document.setUploader(uploader);
        document.setUploadDate(LocalDateTime.now());
        document.setName(file.getOriginalFilename());
        document.setType(file.getContentType());
        try {
            document.setContent(file.getBytes());
        } catch (IOException e) {
            throw new GeneralException("An unexpected error occurred while processing your request.Please try again");
        }
        documentsRepo.save(document);
        return new DocumentUploadResponse(document.getDocumentId(),uploader);
    }

    public ResponseEntity<String> documentDelete(String documentId) {
        Optional<DocumentEntity> documentEntityOptional = documentsRepo.findByDocumentId(documentId);
        if (!documentEntityOptional.isPresent()) {
            throw new DocumentNotFoundException("The document id you have given is invalid. Please check and retry");
        }
        documentsRepo.delete(documentEntityOptional.get());
        return ResponseEntity.ok("Deleted");
    }

    public ResponseEntity<List<DocumentListResponse>> getAllFiles() {
        List<DocumentEntity> documentEntities = documentsRepo.findAll();
        return ResponseEntity.ok(documentEntities.stream()
                .map(document -> modelMapper.map(document, DocumentListResponse.class))
                .collect(Collectors.toList()));
    }

    public DocumentEntity downloadFile(String documentId) {
        Optional<DocumentEntity> optionalDocument = documentsRepo.findByDocumentId(documentId);
        return optionalDocument.orElseThrow(() -> new DocumentNotFoundException("The document id you have given is invalid. Please check and retry"));
    }

    public ResponseEntity<List<DocumentEntity>> documentSearch(DocumentSearchRequest request) {
        return new ResponseEntity<>(null);
    }
}
