package org.box.fuse;

import javax.jws.WebService;

import org.box.fuse.model.Member;

@WebService(serviceName="registrationService")
public interface RegistrationService 
{
	String register(Member member);

	String unregister(String username);
}

//public class RegistrationEndpoint implements RegistrationService
//{
//	
//	@Override
//	public String register(Member member) {
//		return null;
//	}
//
//	@Override
//	public String unregister(String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//}