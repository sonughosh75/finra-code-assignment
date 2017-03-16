package org.finra.upload.dao;

import java.io.IOException;

import org.finra.upload.service.Document;

/**
 * Data access object to insert, find and load {@link Document}s
 * 
 */
public interface DocumentDao {

    /**
     * Inserts a document in the data store.
     * 
     * @param document A Document
     */
    void insert(Document document);
    
    String findDocumentMetaData(String id, String metadataName) throws IOException;
}
