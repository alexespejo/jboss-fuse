package org.box.fuse;

import org.box.fuse.model.Book;
import org.box.fuse.model.Member;

public class RegistrationImpl implements Registration{

	@Override
	public String register(Member member) {
		// TODO Auto-generated method stub
		return "registration";
	}

	@Override
	public String unregister(String username) {
		// TODO Auto-generated method stub
		return "unregister";
	}

	@Override
	public String addbook(Book book) {
		// TODO Auto-generated method stub
		return "addbook";
	}

}
