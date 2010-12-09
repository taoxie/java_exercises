package webresource;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * class with static method that loads web resources and saves into a file
 * @author raul
 *
 */
public class WebResourceFetcher {

	public static boolean load(String webResource, String filename) {
		BufferedInputStream inputStream = null;
		BufferedOutputStream outputStream = null;
        try
        {
        	URL theUrl = new URL(webResource);
        	inputStream = new BufferedInputStream(theUrl.openStream());
        	
        	File theFile = new File(filename);
        	
        	outputStream = null;
            outputStream = new BufferedOutputStream(new FileOutputStream(theFile));
            byte[] buffer = new byte[32 * 1024];
            int bytesRead = 0;
            while ((bytesRead = inputStream.read(buffer)) != -1)
            {
              outputStream.write(buffer, 0, bytesRead);
            }
        }
        catch (Exception e)
        {
        	System.err.println(e.getMessage());
        	return false;
        }
        finally
        {
        	try {
	        	if (inputStream != null) {
						inputStream.close();
	        	}
	        	if (outputStream != null) {
						outputStream.close();
	        	}
        	} catch (IOException e) {
        		System.err.println(e.getMessage());
        		return false;
        	}
        }		
        
        return true;
	}

}
