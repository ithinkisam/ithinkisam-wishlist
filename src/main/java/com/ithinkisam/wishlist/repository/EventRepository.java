package com.ithinkisam.wishlist.repository;

import java.util.List;

import com.ithinkisam.wishlist.domain.Event;

public interface EventRepository {

	Event findById(int id);

	List<Event> findByAdministration(String username);

	List<Event> findByMember(String username);

	List<String> findMembers(int eventId);
	
	List<Event> findEventInvitations(String username);

	Event save(Event event);

	void update(Event event);

	void remove(int id);

	void addMember(int id, String username);

	void removeMember(int id, String username);

	void confirmMembership(int id, String username);

}
