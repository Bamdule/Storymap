package com.storymap.util;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UploadManager {

	private UploadManager() {
	}
	
	public static MultipartRequest getMultiPartRequest(HttpServletRequest request,String savePath)
	{
		int uploadFileSizeLimit = 20 * 1024 * 1024;
		String encType="UTF-8";
		ServletContext context =request.getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		MultipartRequest multi=null;
		try {
				multi = new MultipartRequest(
					      request
						, uploadFilePath
						, uploadFileSizeLimit,encType
						, new DefaultFileRenamePolicy());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return multi;
	}

}


/*MultipartRequest m=UploadManager.getMultiPartRequest(request);
Enumeration files=m.getFileNames();
while(files.hasMoreElements()){
	String file = (String)files.nextElement();
	String file_name= m.getFilesystemName(file);
	String org_file_name=m.getOriginalFileName(file);
	
	System.out.println(file);
	System.out.println(file_name);
	System.out.println(org_file_name);
	System.out.println();
	System.out.println();
	System.out.println();
}
System.out.println("imageName : "+m.getFilesystemName("image"));

*/
