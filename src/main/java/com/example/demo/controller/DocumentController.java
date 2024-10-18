package com.example.demo.controller;

import com.example.demo.model.DocumentEntity;
import com.example.demo.model.DocumentListResponse;
import com.example.demo.model.DocumentSearchRequest;
import com.example.demo.model.DocumentUploadResponse;
import com.example.demo.service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/upload")
    @Operation(summary = "Upload a document")
    public DocumentUploadResponse uploadDocument(@Parameter(description = "The file to upload", required = true) @RequestParam("file") MultipartFile file,
                                                 @Parameter(description = "Uploader's name", required = true) @RequestParam String uploader) {
        return documentService.documentUpload(file, uploader);
    }

    @PostMapping("/list")
    public ResponseEntity<List<DocumentListResponse>> getAllFiles() {
        return documentService.getAllFiles();
    }

    @PostMapping("/filter")
    public ResponseEntity<List<DocumentEntity>> fileSearch(@RequestBody DocumentSearchRequest request) {
        return documentService.documentSearch(request);
    }

    @PostMapping("/delete/{document-id}")
    public ResponseEntity<String> deleteFile(@PathVariable("document-id") String documentId) {
        return documentService.documentDelete(documentId);
    }

    @GetMapping("/download/{document-id}")
    public DocumentEntity downloadFile(@PathVariable("document-id") String documentId) {
        return documentService.downloadFile(documentId);
    }
}
