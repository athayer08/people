package io.uptake.people.rest;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@RequestMapping("/people")
public class PeopleRestService {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getPeople(@PathVariable String name) {

		return "Hi there";

	}

	@RequestMapping(method = RequestMethod.POST)
	public String createPeople(@PathVariable String name) {

		return null;

	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String updatePeople(@PathVariable String name) {

		return null;

	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public String deletePeople(@PathVariable String name) {

		return null;

	}
}
