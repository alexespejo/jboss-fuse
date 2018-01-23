package com.javatpoint;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MemberClient {
	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		MemberDao membersdao = context.getBean("memberDaoBean", MemberDao.class);

		membersdao.createMember("isan", "Arif", "Rahman");
		System.out.println("Member created");

		membersdao.updateMember("ihsan", "arif", "rahman");
		System.out.println("Member updated");

		List<Member> members = membersdao.getAllMembers();
		for (int i = 0; i < members.size(); i++) {
			Member mem = members.get(i);
			System.out.println(mem.getFirstname() + " : " + mem.getLastname());
		}

		membersdao.deleteMember("isan");
		System.out.println("Member deleted");

	}
}
