package com.storymap.network;

/**
 * WebServer의 정보 (IP, PORT NUMER 등..)를 저장하는 클래스
 */
public class ServerInfo {

    public static String imageDir="/images";
    public static String memberImageDir="/member_images";
    public static String addr="192.168.0.6";
    public static String port="8888";
    public static String serverUrl="http://"+addr+":"+port+"/Storymap";

    private ServerInfo(){

    }

}
