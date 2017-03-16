package org.finra.upload.service;

import java.io.IOException;


/**
 * A service to save, find and get documents from an archive. 
 * 
 */
public interface UploadService {
    
    /**
     * Saves a document
     * 
     * @param document A document
     * @return DocumentMetadata The meta data of the saved document
     */
    DocumentMetadata save(Document document);
    
    /**
     * Returns the document file with the given id.
     * Returns null if no document was found.
     * 
     * @param id The id of a document
     * @return A document file
     */

	String findDocumentMetaData(String id, String metadataName) throws IOException;

	String downloadContent(String fileId);
}
