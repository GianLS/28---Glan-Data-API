package br.com.glandata.api.auditoria;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "info_revisao", schema = "auditoria")
@RevisionEntity(CustomAuditListener.class)
@ToString(callSuper = true)
public class CustomAuditEntity extends DefaultRevisionEntity {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private String usuario;
}
