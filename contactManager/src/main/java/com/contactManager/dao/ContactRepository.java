package com.contactManager.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.contactManager.entities.Contact;
import com.contactManager.entities.User;

import jakarta.transaction.Transactional;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

	//current page- page
	//contact per page -5 
	@Query("from Contact as c where c.user.id = :id")
	public Page<Contact> findContactsByUser(@Param("id") int userid, Pageable pageabale);
	
	@Query("Select c from Contact c where c.user.id = :userid and c.cid = :cid")
	public Contact findByUserIDContactId(@Param("userid") int userid,@Param("cid")int cid);
	
	@Transactional
	@Modifying
	@Query("Delete from Contact c where c.cid = :cid")
	public void deleteContactById(@Param("cid")int cid);
	
	
	public List<Contact> findByNameContainingAndUser(String keyword, User user);
}
