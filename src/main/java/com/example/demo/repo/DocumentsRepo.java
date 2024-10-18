package com.example.demo.repo;

import com.example.demo.model.DocumentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentsRepo extends MongoRepository<DocumentEntity, String> {
    Optional<DocumentEntity> findByDocumentId(String documentId);

    void deleteByDocumentId(String documentId);
}
