package com.sinnet.database.model;

import java.util.Date;

public class User {
    private String guid;

    private String companyName;
    private String industry;

    private String department;
    private String position;
    private String need;

    private String username;

    private String phone;

    private String email;

    private Date addtime;

    private Date updateTime;

    private String wechatOpenid;

    private String wechatNickname;

    private Byte wechatSex;

    private String wechatProvince;

    private String wechatCity;

    private String wechatCountry;

    private String wechatHeadimgurl;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getWechatOpenid() {
        return wechatOpenid;
    }

    public void setWechatOpenid(String wechatOpenid) {
        this.wechatOpenid = wechatOpenid == null ? null : wechatOpenid.trim();
    }

    public String getWechatNickname() {
        return wechatNickname;
    }

    public void setWechatNickname(String wechatNickname) {
        this.wechatNickname = wechatNickname == null ? null : wechatNickname.trim();
    }

    public Byte getWechatSex() {
        return wechatSex;
    }

    public void setWechatSex(Byte wechatSex) {
        this.wechatSex = wechatSex;
    }

    public String getWechatProvince() {
        return wechatProvince;
    }

    public void setWechatProvince(String wechatProvince) {
        this.wechatProvince = wechatProvince == null ? null : wechatProvince.trim();
    }

    public String getWechatCity() {
        return wechatCity;
    }

    public void setWechatCity(String wechatCity) {
        this.wechatCity = wechatCity == null ? null : wechatCity.trim();
    }

    public String getWechatCountry() {
        return wechatCountry;
    }

    public void setWechatCountry(String wechatCountry) {
        this.wechatCountry = wechatCountry == null ? null : wechatCountry.trim();
    }

    public String getWechatHeadimgurl() {
        return wechatHeadimgurl;
    }

    public void setWechatHeadimgurl(String wechatHeadimgurl) {
        this.wechatHeadimgurl = wechatHeadimgurl == null ? null : wechatHeadimgurl.trim();
    }

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getNeed() {
		return need;
	}

	public void setNeed(String need) {
		this.need = need;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
    
	
    
}