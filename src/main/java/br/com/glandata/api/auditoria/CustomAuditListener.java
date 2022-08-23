package br.com.glandata.api.auditoria;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CustomAuditListener implements RevisionListener {

	@Override
	public void newRevision(Object entity) {
		CustomAuditEntity revision = (CustomAuditEntity) entity;

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		revision.setUsuario(auth.getName());

	}

}
