package org.finra.upload.service;

import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

/**
 * A document from an archive managed by {@link UploadService}.
 * @author Sourav
 * 
 */
public class Document extends DocumentMetadata implements Serializable {

	private static final long serialVersionUID = 2004955454853853315L;

	private byte[] fileData;

	public Document(byte[] fileData, String fileName, Date documentDate,
			String personName) {
		this.fileData = fileData;
	}

	public Document(byte[] fileData, String fileName, String dateCreated,
			String dateLastUpdated, String lastUpdateBy, String createdBy) {
		super(fileName, dateCreated
				, dateLastUpdated, lastUpdateBy, createdBy);
		this.fileData = fileData;
	}

	public Document(Properties properties) {
		super(properties);
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public DocumentMetadata getMetadata() {
		return new DocumentMetadata(getUuid(), getFileName(),
				getDateCreated().toString(), getDateLastUpdated().toString(), getLastUpdatedBy(), getCreatedBy());
	}

}
