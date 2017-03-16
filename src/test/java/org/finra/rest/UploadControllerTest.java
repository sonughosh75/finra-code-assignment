package org.finra.rest;

import static org.junit.Assert.fail;

import org.finra.rest.config.AppConfig;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@IntegrationTest
public class UploadControllerTest {

	@BeforeClass
	public static void prepareClassForTest() {

	}

	@AfterClass
	public static void teardownAfterClass() {

	}

	@Ignore
	@Test
	public void testHandleFileUpload() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetFileMetadata() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testDownloadContent() {
		fail("Not yet implemented");
	}

}
