package com.ithinkisam.wishlist.service.provider;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.ithinkisam.wishlist.config._Constants;
import com.ithinkisam.wishlist.domain.Event;
import com.ithinkisam.wishlist.domain.User;
import com.ithinkisam.wishlist.repository.EventRepository;
import com.ithinkisam.wishlist.service.EventProvider;
import com.ithinkisam.wishlist.service.UserProvider;

@Service("eventProvider")
@DependsOn("userProvider")
public class EventProviderImpl implements EventProvider {

	@Autowired
	@Qualifier(_Constants.REPOSITORY_TYPE + "EventRepository")
	private EventRepository eventRepository;
	
	@Autowired
	private UserProvider userProvider;
	
	@Override
	public Event getById(int id, boolean confirmedOnly) {
		Event event = eventRepository.findById(id);
		event.setAdmin(userProvider.getByUsername(event.getAdmin().getUsername()));
		event.setMembers(getMembers(id, confirmedOnly));
		return event;
	}

	@Override
	public List<Event> getByAdministrator(User user) {
		List<Event> events = eventRepository.findByAdministration(user.getUsername());
		for (Event event : events) {
			event.setAdmin(userProvider.getByUsername(event.getAdmin().getUsername()));
			event.setMembers(getMembers(event.getId(), false));
		}
		return events;
	}

	@Override
	public List<Event> getByMember(User user) {
		List<Event> events = eventRepository.findByMember(user.getUsername());
		for (Event event : events) {
			event.setAdmin(userProvider.getByUsername(event.getAdmin().getUsername()));
			event.setMembers(getMembers(event.getId(), true));
		}
		return events;
	}
	
	@Override
	public List<Event> getEventInvitations(User user) {
		List<Event> events = eventRepository.findEventInvitations(user.getUsername());
		for (Event event : events) {
			event.setAdmin(userProvider.getByUsername(event.getAdmin().getUsername()));
			event.setMembers(getMembers(event.getId(), true));
		}
		return events;
	}

	@Override
	public Event add(String name, String description, LocalDateTime date, User admin) {
		Event event = new Event(0);
		event.setName(name);
		event.setDescription(description);
		event.setDate(date);
		event.setAdmin(admin);
		return eventRepository.save(event);
	}

	@Override
	public void update(Event event, User admin) {
		Event existingEvent = eventRepository.findById(event.getId());
		if (existingEvent.getAdmin().compareTo(admin) != 0) {
			// TODO throw exception
			// non-admin cannot update event
		}
		eventRepository.update(event);
	}

	@Override
	public void remove(Event event, User admin) {
		Event existingEvent = eventRepository.findById(event.getId());
		if (existingEvent.getAdmin().compareTo(admin) != 0) {
			// TODO throw exception
			// non-admin cannot remove event
		}
		eventRepository.remove(event.getId());
	}

	@Override
	public void addMember(Event event, User member, User admin) {
		Event existingEvent = eventRepository.findById(event.getId());
		if (existingEvent.getAdmin().compareTo(admin) != 0) {
			// TODO throw exception
			// non-admin cannot remove event
		}
		eventRepository.addMember(event.getId(), member.getUsername());
	}

	@Override
	public void removeMember(Event event, User member, User admin) {
		Event existingEvent = eventRepository.findById(event.getId());
		if (existingEvent.getAdmin().compareTo(admin) != 0) {
			// TODO throw exception
			// non-admin cannot remove event
		}
		eventRepository.removeMember(event.getId(), member.getUsername());
	}

	@Override
	public void confirmMembership(Event event, User user) {
		eventRepository.confirmMembership(event.getId(), user.getUsername());
	}
	
	@Override
	public void rejectMembership(Event event, User user) {
		eventRepository.removeMember(event.getId(), user.getUsername());
	}
	
	private Set<User> getMembers(int id, boolean confirmedOnly) {
		Set<User> members = new TreeSet<User>();
		for (String username : confirmedOnly ? eventRepository.findConfirmedMembers(id) : eventRepository.findMembers(id)) {
			members.add(userProvider.getByUsername(username));
		}
		return members;
	}

}
