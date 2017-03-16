package org.finra.upload.service;

import java.io.IOException;
import java.io.Serializable;

import org.finra.upload.dao.DocumentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A service to save, find and get documents from an archive.
 */
@Service("uploadService")
public class UploadServiceImpl implements UploadService, Serializable {

	private static final long serialVersionUID = 8119784722798361327L;

	@Autowired
	private DocumentDao documentDao;

	/**
	 * Saves a document
	 * 
	 */
	@Override
	public DocumentMetadata save(Document document) {
		documentDao.insert(document);
		return document.getMetadata();
	}

	public String findDocumentMetaData(String id, String metadataName)
			throws IOException {
		return documentDao.findDocumentMetaData(id, metadataName);
	}

	@Override
	public String downloadContent(String fileId) {
		return null;
	}

}
