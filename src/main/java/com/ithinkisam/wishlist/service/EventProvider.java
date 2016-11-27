package com.ithinkisam.wishlist.service;

import java.time.LocalDateTime;
import java.util.List;

import com.ithinkisam.wishlist.domain.Event;
import com.ithinkisam.wishlist.domain.User;

public interface EventProvider {

	Event getById(int id, boolean confirmedOnly);
	
	List<Event> getByAdministrator(User user);
	
	List<Event> getByMember(User user);
	
	List<Event> getEventInvitations(User user);
	
	Event add(String name, String description, LocalDateTime date, User admin);
	
	void update(Event event, User admin);
	
	void remove(Event event, User admin);
	
	void addMember(Event event, User member, User admin);
	
	void removeMember(Event event, User member, User admin);
	
	void confirmMembership(Event event, User user);
	
	void rejectMembership(Event event, User user);
	
}
