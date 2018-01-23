package org.box.fuse.library.model;

import java.util.List;  
import org.springframework.orm.jpa.JpaTemplate;  
import org.springframework.transaction.annotation.Transactional;  

@Transactional
public class MemberDao {
	JpaTemplate template;  
	  
    public void setTemplate(JpaTemplate template) {  
        this.template = template;  
    }
    
    public void createMember(String username,String firstname, String lastname){
    	Member member = new Member(username,firstname,lastname);
    	template.persist(member);
    }
    
    public void updateMember(String username,String firstname,String lastname){  
        Member member = template.find(Member.class,username);
        if(member != null){
        	member.setFirstname(firstname);
        	member.setLastname(lastname);
        }
        template.merge(member);  
    }
    
    public void deleteMember(String username){  
        Member member = template.find(Member.class, username);
        if(member != null)
        	template.remove(member);
    }  
    
    public List<Member> getAllMembers(){  
        List<Member> members = template.find("select * from lib_member");
        return members;  
    }  
}