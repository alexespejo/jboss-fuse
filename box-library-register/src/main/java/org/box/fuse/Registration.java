package org.box.fuse;

import org.box.fuse.model.Member;

public interface Registration {
	String register(Member member);
	String unregister(String username);
}