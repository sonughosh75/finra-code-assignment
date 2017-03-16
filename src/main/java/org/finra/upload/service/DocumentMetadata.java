package org.finra.upload.service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.finra.utils.DocumentUtils;

/**
 * @author Sourav
 */
public class DocumentMetadata implements Serializable {
	
	static final long serialVersionUID = 7283287076019483950L;

	public static final String PROP_UUID = "uuid";
	public static final String PROP_FILE_NAME = "file-name";
	public static final String PROP_DOCUMENT_UPLOAD_DATE = "created-by";
	public static final String PROP_DOCUMENT_CREATED_BY = "created-by";
	public static final String PROP_DOCUMENT_LAST_UPDATED_BY = "last-updated-by";
	public static final String PROP_DOCUMENT_LAST_UPDATED_ON = "last-updated-on";

	public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			DATE_FORMAT_PATTERN);

	protected String uuid;
	protected String fileName;
	protected Date dateCreated;
	protected Date dateLastUpdated;
	protected String lastUpdatedBy;
	protected String createdBy;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateLastUpdated() {
		return dateLastUpdated;
	}

	public void setDateLastUpdated(Date dateLastUpdated) {
		this.dateLastUpdated = dateLastUpdated;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public DocumentMetadata() {
		super();
	}

	public DocumentMetadata(String fileName, String dateCreated,
			String dateLastUpdated, String lastUpdateBy, String createdBy) {
		this(UUID.randomUUID().toString(), fileName, dateCreated,
				dateLastUpdated, lastUpdateBy, createdBy);
	}

	public DocumentMetadata(String uuid, String fileName, String dateCreated,
			String dateLastUpdated, String lastUpdateBy, String createdBy) {
		super();
		this.uuid = uuid;
		this.fileName = fileName;
		this.dateCreated = DocumentUtils.convertStringToDate(dateCreated);
		this.createdBy = createdBy;
		this.lastUpdatedBy = lastUpdateBy;
		this.dateLastUpdated = DocumentUtils.convertStringToDate(dateLastUpdated);
	}

	public DocumentMetadata(Properties properties) {
		this(properties.getProperty(PROP_UUID), properties
				.getProperty(PROP_FILE_NAME),  properties
				.getProperty(PROP_DOCUMENT_UPLOAD_DATE),   properties
						.getProperty(PROP_DOCUMENT_LAST_UPDATED_ON), properties
						.getProperty(PROP_DOCUMENT_LAST_UPDATED_BY), properties
								.getProperty(PROP_DOCUMENT_CREATED_BY));
	}

	public Properties createProperties() {
		Properties props = new Properties();
		props.setProperty(PROP_UUID, getUuid());
		props.setProperty(PROP_FILE_NAME, getFileName());
		props.setProperty(PROP_DOCUMENT_CREATED_BY, getCreatedBy());
		props.setProperty(PROP_DOCUMENT_UPLOAD_DATE,
				DATE_FORMAT.format(getDateCreated()));
		props.setProperty(PROP_DOCUMENT_LAST_UPDATED_BY, getLastUpdatedBy());
		props.setProperty(PROP_DOCUMENT_LAST_UPDATED_ON, DATE_FORMAT.format(getDateLastUpdated()));
		return props;
	}
}
