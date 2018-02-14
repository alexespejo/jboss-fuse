package org.box.fuse;

//import javax.jws.*;

import org.box.fuse.model.Book;
import org.box.fuse.model.Member;

//@WebService(targetNamespace="http://localhost:40000/box/library/register") 
public interface Registration {
	String register(Member member);

	String unregister(String username);
	
	String addbook(Book book);
}