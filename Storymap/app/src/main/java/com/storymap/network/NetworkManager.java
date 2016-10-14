package com.storymap.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 서버와의 데이터 송수신 기능을 수행한다.
 */
public class NetworkManager {
    private static NetworkManager instance=null;

    private NetworkManager(){
    }
    public static NetworkManager getInstance(){
        if(instance==null)
            instance=new NetworkManager();
        return instance;
    }


    /*
    전송방법 :  HTTP POST
    URI 주소로 nameValuePairs List 정보를 보낸다.
    String 정보만 보내고, 서버로부터 String 정보를 응답 받는다.

    사용 용도
    1. 서버에 데이터 요청
    2. 서버 데이터 전송

    return 정보 - JSON 또는 String
     */
    public String sendHttpPost(URI url, List<BasicNameValuePair> nameValuePairs){
        String json = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost();
            httpPost.setURI(url);
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
            HttpResponse response = httpClient.execute(httpPost);
            json = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);

        } catch (ClientProtocolException e) {
            Log.e("err", e.getLocalizedMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("err", e.getLocalizedMessage());
            e.printStackTrace();
        }
        return json;
    }

    /*
    전송방법 : HTTP POST
    URI 주소로 MultipartEntity 정보를 보낸다.
    이미지 파일과 문자 데이터를 보낼 수 있다.

    사용 용도
    1. 이미지 전송 (여러개 또는 한개)
    return 정보 - JSON 또는 String
     */
    public String sendImageHttpPost(URI uri,MultipartEntity multipart){
        String result=null;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(uri);
            post.setHeader("Connection", "Keep-Alive");
            post.setHeader("Accept-Charset", "UTF-8");
            post.setHeader("ENCTYPE", "multipart/form-data");
            post.setEntity(multipart);
            HttpResponse response = client.execute(post);
            result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
    서버의 URL 이미지에 접근하는 메소드
     */
    public Bitmap httpURLImageDownload(URL url){
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();

            return BitmapFactory.decodeStream(is);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
