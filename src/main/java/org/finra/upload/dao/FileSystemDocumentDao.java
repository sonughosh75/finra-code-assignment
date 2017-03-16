package org.finra.upload.dao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.finra.upload.service.Document;
import org.springframework.stereotype.Service;

/**
 * Data access object to insert, find and load {@link Document}s.
 * 
 */
@Service("documentDao")
public class FileSystemDocumentDao implements DocumentDao {

	private static final Logger LOG = Logger
			.getLogger(FileSystemDocumentDao.class);

	public static final String DIRECTORY = "archive";
	public static final String META_DATA_FILE_NAME = "metadata.properties";

	@PostConstruct
	public void init() {
		createDirectory(DIRECTORY);
	}

	/**
     * 
     */
	@Override
	public void insert(Document document) {
		try {
			createDirectory(document);
			saveFileData(document);
			saveMetaData(document);
		} catch (IOException e) {
			String message = "Error while inserting document";
			LOG.error(message, e);
			throw new RuntimeException(message, e);
		}
	}

	private void saveFileData(Document document) throws IOException {
		String path = getDirectoryPath(document);
		BufferedOutputStream stream = new BufferedOutputStream(
				new FileOutputStream(new File(new File(path),
						document.getFileName())));
		stream.write(document.getFileData());
		stream.close();
	}

	public void saveMetaData(Document document) throws IOException {
		String path = getDirectoryPath(document);
		Properties props = document.createProperties();
		File f = new File(new File(path), META_DATA_FILE_NAME);
		OutputStream out = new FileOutputStream(f);
		props.store(out, "Document meta data");
	}

	private Properties readProperties(String uuid) throws IOException {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(new File(getDirectoryPath(uuid),
					META_DATA_FILE_NAME));
			prop.load(input);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}

	private String createDirectory(Document document) {
		String path = getDirectoryPath(document);
		createDirectory(path);
		return path;
	}

	private String getDirectoryPath(Document document) {
		return getDirectoryPath(document.getUuid());
	}

	private String getDirectoryPath(String uuid) {
		StringBuilder sb = new StringBuilder();
		sb.append(DIRECTORY).append(File.separator).append(uuid);
		String path = sb.toString();
		return path;
	}

	private void createDirectory(String path) {
		File file = new File(path);
		file.mkdirs();
	}

	@Override
	public String findDocumentMetaData(String uuid, String metadataName)
			throws IOException {
		String metaDataValue = null;
		Properties prop = readProperties(uuid);

		for (Map.Entry<Object, Object> entry : prop.entrySet()) {
			if (entry.getKey().equals(uuid)) {
				metaDataValue = entry.getValue().toString();
			}
		}
		return metaDataValue;
	}
}
