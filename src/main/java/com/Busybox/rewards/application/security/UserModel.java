package com.Busybox.rewards.application.security;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.management.relation.Role;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Table(name="User_Model")
public class UserModel implements UserDetails{

	 private static final long serialVersionUID = 1L;
	
	@Id
	
	private String id;
	
	private String name;
	
	private String email;

//	@Column(name = "role", columnDefinition = "TINYINT")
//	private Role role;
	
	private String roleName;

	
	private String password;

	private String storeId;

	private String description;
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//	    String authority = "ROLE_" + roleName; // Prefix with "ROLE_" as is common convention
		
	    return List.of(new SimpleGrantedAuthority(roleName));
	}
	


	@Override
	public String getUsername() {
		return this.email;
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
	public String getPassword() {
		return password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
//@Override
//public Collection<? extends GrantedAuthority> getAuthorities() {
//  // Assuming your role field holds the role as a String
//  return List.of(new SimpleGrantedAuthority(Role_Name));
//}
//
//@Override
//public Collection<? extends GrantedAuthority> getAuthorities() {
//  // Assuming your role field holds the role as a String
//  return Collections.singleton(new SimpleGrantedAuthority(role));
//}


//@Override
//public Collection<? extends GrantedAuthority> getAuthorities() {
//	// TODO Auto-generated method stub
//	return null;
//}
