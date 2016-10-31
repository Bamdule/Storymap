package com.storymap.common.dto;


public class MemberDto {
	private int mem_code;
	private String mem_email;
	private String mem_pwd;
	private String mem_name;
	private String mem_img_path;
	private String thumbnail_id;	
	private String loginFlag;
	private String blog_image_id;

   
	
	public String getBlog_image_id() {
		return blog_image_id;
	}

	public void setBlog_image_id(String blog_image_id) {
		this.blog_image_id = blog_image_id;
	}

	public String getThumbnail_id() {
		return thumbnail_id;
	}

	public void setThumbnail_id(String thumbnail_id) {
		this.thumbnail_id = thumbnail_id;
	}

	public String getLoginFlag() {
		return loginFlag;
	}
	
	public int getMem_code() {
		return mem_code;
	}

	public void setMem_code(int mem_code) {
		this.mem_code = mem_code;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}
	public MemberDto(){

    }
    public MemberDto( String mem_email, String mem_pwd, String mem_name, String mem_img_path) {
        this.mem_email = mem_email;
        this.mem_pwd = mem_pwd;
        this.mem_name = mem_name;
        this.mem_img_path = mem_img_path;
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
		return "MemberDto [mem_code=" + mem_code + ", mem_email=" + mem_email + ", mem_pwd=" + mem_pwd + ", mem_name="
				+ mem_name + ", mem_img_path=" + mem_img_path + ", thumbnail_id=" + thumbnail_id + ", loginFlag="
				+ loginFlag + ", blog_image_id=" + blog_image_id + "]";
	}

}
