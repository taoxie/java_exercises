package webresource;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class WebResourceFetcherIntegrationTest {
	
	private static final String IMAGE_RESOURCE = "http://upload.wikimedia.org/wikipedia/commons/a/a9/Example.jpg";
	private static final String FILENAME = "filename.txt";
	
	@Before
	public void deleteFilename() {
		File file = new File(FILENAME);
		file.delete();
	}
	
	@Test
	public void webResourceIsSavedInAFile() {
		
		assertTrue(WebResourceFetcher.load(IMAGE_RESOURCE, FILENAME));
		
		assertTrue(fileIsInFileSystem(FILENAME));
		assertTrue(fileIsRecent(FILENAME));
	}
	
	@Test
	public void webResourceDoesNotExist() throws IOException {
		assertTrue(!WebResourceFetcher.load("this is a fake web resource", FILENAME));
	}

	@Test
	public void WebResourceCannotBeSavedInAnInvalidFile() throws IOException {
		assertTrue(!WebResourceFetcher.load(IMAGE_RESOURCE, "invalid / file"));
	}
	
	private boolean fileIsRecent(String filename) {
		File file = new File(filename);
		// file was modified in the last 5 seconds
		return (System.currentTimeMillis() - file.lastModified() < 5000L);
	}

	private boolean fileIsInFileSystem(String filename) {
		File file = new File(filename);
		return file.exists();
	}
	
}
