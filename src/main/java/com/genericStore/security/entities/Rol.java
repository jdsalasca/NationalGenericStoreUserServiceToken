package com.genericStore.security.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.genericStore.security.enums.RolName;

@Entity
@Table(name = "rol")
public class Rol {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RolName rolNombre;
	public Rol(int id, @NotNull RolName rolNombre) {
		super();
		this.id = id;
		this.rolNombre = rolNombre;
	}
	
	
	public Rol(@NotNull RolName rolNombre) {
		super();
		this.rolNombre = rolNombre;
	}


	public Rol(int id) {
		super();
		this.id = id;
	}
	public Rol() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public RolName getRolNombre() {
		return rolNombre;
	}
	public void setRolNombre(RolName rolNombre) {
		this.rolNombre = rolNombre;
	}
    
    
	
	

}
