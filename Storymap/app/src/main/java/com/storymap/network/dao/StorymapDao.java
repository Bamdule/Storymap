package com.storymap.network.dao;

import android.util.Log;

import com.storymap.network.NetworkManager;
import com.storymap.network.ServerInfo;
import com.storymap.network.dto.StorymapDto;
import com.storymap.network.util.JsonManager;
import com.storymap.network.util.TestStorymap;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by S501-04 on 2016-09-28.
 */
public class StorymapDao {
    private StorymapDao() {
    }

    private NetworkManager networkManager=NetworkManager.getInstance();
    private static StorymapDao instance = null;
    private JsonManager jm = new JsonManager();
    public static StorymapDao getInstance(){
        if(instance==null)
            instance=new StorymapDao();
        return instance;
    }

    public String insertStorymap(StorymapDto mDto, int mem_code) {
        String response_data=null;

        String urlString = ServerInfo.serverUrl+"/StorymapServlet?command=storymap_add";

        try {
            URI url = new URI(urlString);
            List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();

            nameValuePairs.add(new BasicNameValuePair("storymap", jm.instanceToJsonString(mDto)));
            nameValuePairs.add(new BasicNameValuePair("mem_code", String.valueOf(mem_code)));

            response_data= networkManager.sendHttpPost(url,nameValuePairs);

        } catch (URISyntaxException e) {
            Log.e("URISyntaxException", e.getLocalizedMessage());
            e.printStackTrace();
        }
        return response_data;
    }

    public StorymapDto selectStorymap(String sm_code){
        StorymapDto smDto = null;
        String response_data=null;

        String urlString = ServerInfo.serverUrl+"/StorymapServlet?command=storymap_detail";
        try {
            URI url = new URI(urlString);
            List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();

            nameValuePairs.add(new BasicNameValuePair("sm_code", sm_code));
            response_data= networkManager.sendHttpPost(url,nameValuePairs);

            smDto=(StorymapDto)jm.jsonStringToInstance(response_data);
            Log.i("storymap Data",response_data);

        } catch (URISyntaxException e) {
            Log.e("URISyntaxException", e.getLocalizedMessage());
            e.printStackTrace();
        }
        return smDto;
    }



    public String memberImageAdd(String mem_code,String mem_img_path){
        String result=null;
        try {
            URI uri = new URI(ServerInfo.serverUrl+"/StorymapServlet?command=storymap_image_add");
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
}
