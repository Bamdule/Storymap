package com.storymap.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ThumbnailHelper {
	private static ThumbnailHelper instance = null;
	private ThumbnailHelper(){
	}
	public static ThumbnailHelper getInstance(){
		if(instance==null)
			instance=new ThumbnailHelper();
		
		return instance;
	}
	

	public String createThumbnail(String orginFileSavePath,String originFileName,int t_width,int t_height){
		String thumbnailName="thumbnail_"+originFileName;
    	String thumbnailSavePath=orginFileSavePath+"\\thumbnail";
    	
    	String thumbnailURL=thumbnailSavePath+"\\"+thumbnailName;
    	String originFileURL=orginFileSavePath+"\\"+originFileName;

    	System.out.println("thumbnailURL : "+thumbnailURL);
    	System.out.println("originFileURL : "+originFileURL);
        try {
            //원본이미지파일의 경로+파일명
            File originFile = new File(originFileURL);
            //생성할 썸네일파일의 경로+썸네일파일명
            File thumbnailFile = new File(thumbnailURL);
 
            BufferedImage buffer_original_image = ImageIO.read(originFile);
            BufferedImage buffer_thumbnail_image = new BufferedImage(t_width, t_height, BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D graphic = buffer_thumbnail_image.createGraphics();
            graphic.drawImage(buffer_original_image, 0, 0, t_width, t_height, null);
            ImageIO.write(buffer_thumbnail_image, "jpg", thumbnailFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return thumbnailName;
	}
}
