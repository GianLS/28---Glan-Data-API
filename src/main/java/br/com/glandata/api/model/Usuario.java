package br.com.glandata.api.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Id
	private String login;

	@Getter
	@Setter
	private String nome;

	@Getter
	@Setter
	private String email;

	@Getter
	@Setter
	private String senha;

	@Getter
	@Setter
	@ManyToMany
	@JoinTable(name = "usuarios_roles", 
			joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "login", foreignKey = @ForeignKey(name = "fk_id")),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "nomeRole", foreignKey = @ForeignKey(name = "fk_role")))
	private List<Role> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
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
}
