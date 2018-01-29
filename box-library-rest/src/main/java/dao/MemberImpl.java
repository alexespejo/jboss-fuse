package org.box.fuse.dao;

import org.box.fuse.model.Member;
import java.util.List;
import javax.persistence.EntityManager;
import org.box.fuse.AppConfig;

public class MemberImpl implements MemberDao {
	private static EntityManager em;

	public MemberImpl() {
		em = AppConfig.init();
	}

	@Override
	public void add(Member member) {
		em.getTransaction().begin();
		em.persist(member);
		em.getTransaction().commit();
	}

	@Override
	public List<Member> getAllMembers() {
		em.getTransaction().begin();
		List<Member> member = em.createQuery("select * from member").getResultList();
		return member;
	}

	@Override
	public void delete(String username) {
		em.getTransaction().begin();
		Member member = em.find(Member.class, username);
		em.remove(member);
		em.getTransaction().commit();
	}

	@Override
	public Member update(Member member) {
		em.getTransaction().begin();
		member = em.merge(member);
		em.getTransaction().commit();
		return member;
	}

	@Override
	public Member getMember(String username) {
		em.getTransaction().begin();
		Member member = em.find(Member.class, username);
		em.getTransaction().commit();
		return member;
	}

}
