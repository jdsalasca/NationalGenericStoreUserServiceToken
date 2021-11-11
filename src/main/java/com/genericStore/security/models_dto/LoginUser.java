package com.genericStore.security.models_dto;

import javax.validation.constraints.NotBlank;

public class LoginUser {
    @NotBlank
    private String nick;
    @NotBlank
    private String password;
    
    
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


}
