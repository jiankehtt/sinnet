package com.sinnet.action.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.sinnet.base.AjaxBaseAction;
import com.sinnet.database.model.User;
import com.sinnet.service.UserService;
import com.sinnet.utils.RandomGUID;


@Scope("prototype")
public class AjaxUserAction  extends AjaxBaseAction {

	private static final long serialVersionUID = -6652505635259159561L;
	
	private String industry;
	private String company;
	private String department;
	private String username;
	private String phone;
	private String email;
	
	@Autowired
	UserService userService;
	
	

	public String submit() {
		User user = new User();
		user.setUsername(username);
		user.setCompanyName(company);
		user.setDepartment(department);
		user.setEmail(email);
		user.setGuid(RandomGUID.getGUID());
		user.setPhone(phone);
		userService.saveUser(user);
		return BACK_DATA;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
