package com.genericStore.security.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name = "usuario")
public class User {
	
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	@Column(name = "nombre")
	private String name;
	@NotNull
	@Column(name = "cedula")
	private String identityDocument;
	@NotNull
	@Column(name = "nick")
	private String nick;
	@NotNull
	@Column(name = "password")
	private String password;
	@NotNull
	@Column(name = "email")
	private String email;
	
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

	public User() {
		super();
	}



	public User(long id, @NotNull String name, @NotNull String identityDocument, @NotNull String nick,
			@NotNull String password, @NotNull String email, @NotNull Set<Rol> roles) {
		super();
		this.id = id;
		this.name = name;
		this.identityDocument = identityDocument;
		this.nick = nick;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}



	public User(@NotNull String name, @NotNull String identityDocument, @NotNull String nick, @NotNull String password,
			@NotNull String email, @NotNull Set<Rol> roles) {
		super();
		this.name = name;
		this.identityDocument = identityDocument;
		this.nick = nick;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}
	



	public User(@NotNull String name, @NotNull String identityDocument, @NotNull String nick, @NotNull String password,
			@NotNull String email) {
		super();
		this.name = name;
		this.identityDocument = identityDocument;
		this.nick = nick;
		this.password = password;
		this.email = email;
	}



	public User(@NotNull String nick, @NotNull String password) {
		super();
		this.nick = nick;
		this.password = password;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getIdentityDocument() {
		return identityDocument;
	}



	public void setIdentityDocument(String identityDocument) {
		this.identityDocument = identityDocument;
	}



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



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Set<Rol> getRoles() {
		return roles;
	}



	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}




}
