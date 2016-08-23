package com.betr.server.domain;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.bson.Document;
import org.springframework.security.core.userdetails.UserDetails;

import com.betr.server.authorization.UserAuthority;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	private int id;
	private String email;
	private String name;
	private String password;
	private String phoneNumber;
	private boolean enabled = true;
	private long expires;
	private UserRole role;

	private Set<UserAuthority> authorities = new HashSet<>();
	
	public User() {}
	
	public User(int id, String email, String name, String password, String phoneNumber, UserRole role) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.role = role;
		this.phoneNumber = phoneNumber;
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	
	public static User fromDocument(Document document) {
		return new User(document.getInteger("id"), document.getString("email"), document.getString("name"), document.getString("password"), document.getString("phoneNumber"), UserRole.valueOf(document.getString("userRole")));
	}
	
	public Document toDocument() {
		Document document = new Document();
		
		document.append("email", getEmail());
		document.append("name", getName());
		document.append("phoneNumber", getPhoneNumber());
		document.append("password", getPassword());
		document.append("userRole", getRole().name());
		
		return document;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setExpires(long expires) {
		this.expires = expires;
	}
	
	public long getExpires() {
		return expires;
	}
	
	@JsonIgnore
	public Set<UserAuthority> getAuthorities() {
		return authorities;
	}

	// Use Roles as external API
	public Set<UserRole> getRoles() {
		Set<UserRole> roles = EnumSet.noneOf(UserRole.class);
		//FIXME
		roles.add(UserRole.USER);
		if (authorities != null) {
			for (UserAuthority authority : authorities) {
				roles.add(UserRole.valueOf(authority));
			}
		}
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		for (UserRole role : roles) {
			grantRole(role);
		}
	}

	public void grantRole(UserRole role) {
		if (authorities == null) {
			authorities = new HashSet<UserAuthority>();
		}
		authorities.add(role.asAuthorityFor(this));
	}

	public void revokeRole(UserRole role) {
		if (authorities != null) {
			authorities.remove(role.asAuthorityFor(this));
		}
	}

	public boolean hasRole(UserRole role) {
		return authorities.contains(role.asAuthorityFor(this));
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
