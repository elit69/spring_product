package com.lit.app.config.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInit extends AbstractSecurityWebApplicationInitializer{

	@Override
	protected boolean enableHttpSessionEventPublisher() {
		return true;
	}
	
	
}
