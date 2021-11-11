package com.genericStore.security.models_dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NewUser {
    @NotBlank
    private String name;
    @NotBlank
    private String nick;
    @NotBlank
    private String identityDocument;
    @Email
    private String email;
    @NotBlank
    private String password;
    private Set<String> roles = new HashSet<>();
    
    
    
	public NewUser(@NotBlank String nick, @NotBlank String password) {
		super();
		this.nick = nick;
		this.password = password;
	}
	public NewUser(@NotBlank String name, @NotBlank String nick, @NotBlank String identityDocument, @Email String email,
			@NotBlank String password, Set<String> roles) {
		super();
		this.name = name;
		this.nick = nick;
		this.identityDocument = identityDocument;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	
	
	public NewUser(@NotBlank String name, @NotBlank String nick, @NotBlank String identityDocument, @Email String email,
			@NotBlank String password) {
		super();
		this.name = name;
		this.nick = nick;
		this.identityDocument = identityDocument;
		this.email = email;
		this.password = password;
	}
	
	
	public NewUser() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getIdentityDocument() {
		return identityDocument;
	}
	public void setIdentityDocument(String identityDocument) {
		this.identityDocument = identityDocument;
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
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
	
	

    
    
}
