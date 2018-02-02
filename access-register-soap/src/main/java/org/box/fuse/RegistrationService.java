package org.box.fuse;

import javax.jws.WebService;

import org.box.fuse.model.Member;

@WebService(serviceName="registrationService")
public interface RegistrationService 
{
	String register(Member member);

	String unregister(String username);
}