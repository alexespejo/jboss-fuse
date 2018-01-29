package org.box.fuse.dao;

import java.util.List;

import org.box.fuse.model.Member;

public interface MemberDao {
	public void add(Member member);

	public List<Member> getAllMembers();

	public void delete(String username);

	public Member update(Member member);

	public Member getMember(String username);
}
