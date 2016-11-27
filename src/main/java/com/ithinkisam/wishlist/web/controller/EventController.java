package com.ithinkisam.wishlist.web.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.ithinkisam.wishlist.domain.Event;
import com.ithinkisam.wishlist.domain.User;
import com.ithinkisam.wishlist.domain.Wish;
import com.ithinkisam.wishlist.service.EventProvider;
import com.ithinkisam.wishlist.service.UserProvider;
import com.ithinkisam.wishlist.service.WishProvider;

@Controller
@RequestMapping("/events")
public class EventController {

	@Autowired private EventProvider eventProvider;
	@Autowired private UserProvider userProvider;
	@Autowired private WishProvider wishProvider;
	
	@GetMapping
	public String showEvents(Model model, WebRequest request) {
		User user = userProvider.getByUsername(request.getUserPrincipal().getName());
		return view(model, user);
	}
	
	@PostMapping
	public String addEvent(WebRequest request,
			@RequestParam("name") String name,
			@RequestParam("description") String description,
			@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		User user = userProvider.getByUsername(request.getUserPrincipal().getName());
		Event event = eventProvider.add(name, description, date.atStartOfDay(), user);
		eventProvider.addMember(event, user, user);
		eventProvider.confirmMembership(event, user);
		return "redirect:/events/" + event.getId();
	}
	
	@GetMapping("/{id}")
	public String showEventDetail(Model model, WebRequest request,
			@PathVariable("id") Integer id) {
		User user = userProvider.getByUsername(request.getUserPrincipal().getName());
		Event event = eventProvider.getById(id, true);
		model.addAttribute("event", event);
		model.addAttribute("user", user);
		
		Map<String, List<Wish>> lists = new HashMap<String, List<Wish>>();
		for (User member : event.getMembers()) {
			lists.put(member.getUsername(), wishProvider.getByUser(member));
		}
		model.addAttribute("lists", lists);
		
		if (event.getAdmin().compareTo(user) == 0) {
			return "eventAdmin";
		} else {
			return "eventDetail";
		}
	}
	
	@PostMapping("/{id}/join")
	public String joinEvent(WebRequest request,
			@PathVariable("id") Integer id) {
		User user = userProvider.getByUsername(request.getUserPrincipal().getName());
		Event event = eventProvider.getById(id, true);
		eventProvider.confirmMembership(event, user);
		return "redirect:/events/" + id;
	}

	@PostMapping("/{id}/reject")
	public String rejectEvent(WebRequest request,
			@PathVariable("id") Integer id) {
		User user = userProvider.getByUsername(request.getUserPrincipal().getName());
		Event event = eventProvider.getById(id, true);
		eventProvider.rejectMembership(event, user);
		return "redirect:/events";
	}
	
	@PostMapping("/{id}/invite")
	public String inviteUserToEvent(WebRequest request,
			@PathVariable("id") Integer id,
			@RequestParam("username") String username) {
		User admin = userProvider.getByUsername(request.getUserPrincipal().getName());
		User member = userProvider.getByUsername(username);
		if (member == null) {
			return "redirect:/events?error=userNotFound";
		}
		Event event = eventProvider.getById(id, true);
		eventProvider.addMember(event, member, admin);
		return "redirect:/events";
	}
	
	@PostMapping("/{id}/remove")
	public String removeUserFromEvent(WebRequest request,
			@PathVariable("id") Integer id,
			@RequestParam("username") String username) {
		User admin = userProvider.getByUsername(request.getUserPrincipal().getName());
		User member = userProvider.getByUsername(username);
		if (member == null) {
			return "redirect:/events?error=userNotFound";
		}
		Event event = eventProvider.getById(id, true);
		eventProvider.removeMember(event, member, admin);
		return "redirect:/events";
	}
	
	private String view(Model model, User user) {
		List<Event> memberEvents = eventProvider.getByMember(user);
		Collections.sort(memberEvents, new Comparator<Event>() {
			@Override
			public int compare(Event e1, Event e2) {
				return e1.getDate().compareTo(e2.getDate());
			}
		});
		
		model.addAttribute("eventInvites", eventProvider.getEventInvitations(user));
		model.addAttribute("adminEvents", eventProvider.getByAdministrator(user));
		model.addAttribute("memberEvents", memberEvents);
		
		return "events";
	}
	
}
