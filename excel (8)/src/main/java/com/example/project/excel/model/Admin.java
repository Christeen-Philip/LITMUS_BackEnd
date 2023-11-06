package com.example.project.excel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ADMINS")
public class Admin{
	
		@Id
		@Column(name="ADMINEMAIL")
		private String adminMail;
		@Column(name="PASSWORD")
		private String password;
		
		
		public Admin() {
			
		}


		public Admin(String adminMail, String password) {
			super();
			this.adminMail = adminMail;
			this.password = password;
		}


		public String getAdminMail() {
			return adminMail;
		}


		public void setAdminMail(String adminMail) {
			this.adminMail = adminMail;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		@Override
		public String toString() {
			return "Admin [adminMail=" + adminMail + ", password=" + password + "]";
		}
		
		

}
