package org.box.fuse;

import org.box.fuse.model.Member;
import org.box.fuse.model.Book;

public interface Registration {
	String register(Member member);
	String unregister(String username);
	String addbook(Book book);
}