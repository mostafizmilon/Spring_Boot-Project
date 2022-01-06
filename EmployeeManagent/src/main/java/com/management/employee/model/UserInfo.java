package com.management.employee.model;

import com.management.employee.enums.Role;
import org.springframework.data.annotation.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", length = 25)
	private Integer id;

	@Email(message = "invalid email address")
	@NotBlank(message = "Email can not be empty")
	@Column(name = "email", nullable = false, unique = true, length = 50)
	private String email;

	@Column(name = "password", length = 800)
	@NotBlank(message = "Password can not be empty")
	private String password;

	@Column(name = "role", length = 50)
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(name = "enabled")
	private boolean enabled = false;

	@Column(name = "full_name", length = 50)
	@NotBlank(message = "Full name can not be empty")
	private String full_name;

	@Column(name = "address", length = 100)
	@NotBlank(message = "Address can not be empty")
	private String address;

	@Column(name = "designation", length = 50)
	private String designation;

	@Column(nullable = true, length = 64)
	private String photos;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public UserInfo() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Transient
	public String getPhotos() {
		if (photos == null || id == null) return null;

		return "/user-photos/" + id + "/" + photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}
}
