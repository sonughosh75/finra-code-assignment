package org.finra.rest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.finra.upload.service.Document;
import org.finra.upload.service.DocumentMetadata;
import org.finra.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * REST web service for archive service {@link UploadService}.
 * 
 * @author Sourav
 */
@Controller
@RequestMapping("/api/file/")
public class UploadController {

	private static final Logger LOG = Logger.getLogger(UploadController.class);

	@Autowired
	UploadService uploadService;

	/**
	 * Uploads a document
	 * 
	 * Url: /api/file/upload?file={file}&createdBy={createdBy}&createdDate={
	 * createdDate} &lastUpdatedOn={lastUpdatedOn}&lastUpdatedBy={lastUpdatedBy}
	 * [POST]
	 * 
	 * @param file
	 *            A file posted in a multipart request
	 * @param createdBy
	 *            The name of the uploading person
	 * @param createdDate
	 *            The date of the document
	 * @param lastUpdatedBy
	 *            Person who last updated the document
	 * @param lastUpdatedOn
	 *            Date of last update of the document
	 * @return The meta data of the added document
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody DocumentMetadata handleFileUpload(
			@RequestParam(value = "file", required = true) MultipartFile file,
			@RequestParam(value = "createdBy", required = true) String createdBy,
			@RequestParam(value = "lastUpdatedBy", required = true) String lastUpdatedBy,
			@RequestParam(value = "lastUpdatedOn", required = true) String lastUpdatedDate,
			@RequestParam(value = "createdDate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") String createdDate) {
		try {
			Document document = new Document(file.getBytes(),
					file.getOriginalFilename(), createdDate, lastUpdatedDate,
					createdBy, lastUpdatedBy);
			uploadService.save(document);
			return document.getMetadata();
		} catch (RuntimeException e) {
			LOG.error("Error while uploading.", e);
			throw e;
		} catch (Exception e) {
			LOG.error("Error while uploading.", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method to search a file in the file system
	 * 
	 * Url: /api/file/search/084gfr-785/last-updated-on
	 * 
	 * @param fileId
	 *            - The id of the file
	 * @param metadataName
	 *            - The name of the metadata whose value is to be determined
	 * @return The metadata value of the file
	 * @throws IOException
	 */
	@RequestMapping(value = "/search/{fileId}/{metadataName}", method = RequestMethod.GET)
	public HttpEntity<String> getFileMetadata(@PathVariable String fileId,
			@PathVariable String metadataName) throws IOException {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.TEXT_PLAIN);
		return new ResponseEntity<String>(uploadService.findDocumentMetaData(
				fileId, metadataName), httpHeaders, HttpStatus.OK);
	}

	/**
	 * Method to download a file
	 * 
	 * Uri - /api/file/download/084gfr-785
	 * 
	 * @param fileId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/download/{fileId}", method = RequestMethod.GET)
	public HttpEntity<String> downloadContent(@PathVariable String fileId)
			throws IOException {
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<String>(
				uploadService.downloadContent(fileId), httpHeaders,
				HttpStatus.OK);
	}
}
