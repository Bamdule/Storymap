package com.storymap.network.dto;

/**
 * Created by S501-04 on 2016-09-20.
 */
public class MemberDto {
	private String mem_code;
	private String mem_email;
	private String mem_pwd;
	private String mem_name;
	private String mem_img_path;

    public MemberDto(){

    }
    public MemberDto( String mem_email, String mem_pwd, String mem_name, String mem_img_path) {
        this.mem_email = mem_email;
        this.mem_pwd = mem_pwd;
        this.mem_name = mem_name;
        this.mem_img_path = mem_img_path;
    }

    public String getmem_code() {
        return mem_code;
    }

    public String getMem_email() {
        return mem_email;
    }

    public String getMem_pwd() {
        return mem_pwd;
    }

    public String getMem_name() {
        return mem_name;
    }

    public String getMem_img_path() {
        return mem_img_path;
    }

    public void setmem_code(String mem_code) {
        this.mem_code = mem_code;
    }
    public void setMem_email(String mem_email) {
        this.mem_email = mem_email;
    }

    public void setMem_pwd(String mem_pwd) {
        this.mem_pwd = mem_pwd;
    }

    public void setMem_name(String mem_name) {
        this.mem_name = mem_name;
    }

    public void setMem_img_path(String mem_img_path) {
        this.mem_img_path = mem_img_path;
    }


    @Override
    public String toString() {
        return "MemberDto{" +
                "mem_code='" + mem_code + '\'' +
                ", mem_email='" + mem_email + '\'' +
                ", mem_pwd='" + mem_pwd + '\'' +
                ", mem_name='" + mem_name + '\'' +
                ", mem_img_path='" + mem_img_path + '\'' +
                '}';
    }

}
