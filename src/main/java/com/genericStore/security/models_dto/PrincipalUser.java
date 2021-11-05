package com.genericStore.security.models_dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.genericStore.security.entities.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PrincipalUser implements UserDetails {
    private String name;
    private String nick;
    private String email;
    private String identityDocument;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

 
    
    

    public PrincipalUser(String name, String nick, String email, String identityDocument, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.name = name;
		this.nick = nick;
		this.email = email;
		this.identityDocument = identityDocument;
		this.password = password;
		this.authorities = authorities;
	}

	public static PrincipalUser build(User  user){
        List<GrantedAuthority> authorities =
                user.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
                .getRolNombre().name())).collect(Collectors.toList());
        return new PrincipalUser(user.getName(), user.getNick(), user.getEmail(), user.getIdentityDocument(),user.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        return true;
    }
    
	@Override
	public String getUsername() {
		
		return nick;
	}
	
    @Override
    public String getPassword() {
        return password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdentityDocument() {
		return identityDocument;
	}

	public void setIdentityDocument(String identityDocument) {
		this.identityDocument = identityDocument;
	}



	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}




}
