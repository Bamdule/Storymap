package com.storymap.network.dao;

import android.util.Log;
import android.widget.ImageView;

import com.storymap.network.ImageAsync;
import com.storymap.network.NetworkManager;
import com.storymap.network.ServerInfo;
import com.storymap.network.dto.MemberDto;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
    private MemberDao() {
    }

    private NetworkManager networkManager=NetworkManager.getInstance();
    private static MemberDao instance = null;
    public static MemberDao getInstance(){
            if(instance==null)
                instance=new MemberDao();
        return instance;
    }
    public String memberRegister(MemberDto mDto) {

        String result=null;
        String urlString = ServerInfo.serverUrl+"/MemberServlet?command=member_add";

        try {
            URI url = new URI(urlString);
            List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();

            nameValuePairs.add(new BasicNameValuePair("mem_email", mDto.getMem_email()));
            nameValuePairs.add(new BasicNameValuePair("mem_pwd", mDto.getMem_pwd()));
            nameValuePairs.add(new BasicNameValuePair("mem_name", mDto.getMem_name()));
            nameValuePairs.add(new BasicNameValuePair("mem_img_path", mDto.getMem_img_path()));

            result= networkManager.sendHttpPost(url,nameValuePairs);

        } catch (URISyntaxException e) {
            Log.e("URISyntaxException", e.getLocalizedMessage());
            e.printStackTrace();
        }
        return result;
    }

    public String memberImageAdd(String mem_code,String mem_img_path){
        String result=null;
        try {
            URI uri = new URI(ServerInfo.serverUrl+"/MemberServlet?command=member_image_add");
            MultipartEntity multipart = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
            File glee = new File(mem_img_path);
            FileBody bin = new FileBody(glee);

            multipart.addPart("mem_img_path", bin);
            multipart.addPart("mem_code", new StringBody(mem_code));
            networkManager.sendImageHttpPost(uri,multipart);
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void memberImageRequest(ImageView imageView,String mem_img_path){
        try {
            URL url = new URL(ServerInfo.serverUrl+ServerInfo.imageDir+ServerInfo.memberImageDir+"/"+mem_img_path);
            new ImageAsync(imageView,url).execute();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
