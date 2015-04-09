package io.uptake.rest;

import io.uptake.dao.PeopleDao;
import io.uptake.dto.PeopleRequest;
import io.uptake.ldm.People;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/people")
public class PeopleRestService {

	private static PeopleDao peopleDao;

	private static PeopleDao getPeopleDao() {
		if (peopleDao != null) {
			return peopleDao;
		} else {
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"classpath:spring-dao.xml");
			BeanFactory factory = context;
			peopleDao = (PeopleDao) factory.getBean("personDAO");
			return peopleDao;
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = { "Content-type=application/json" })
	public @ResponseBody PeopleRequest addPeople(
			@RequestBody PeopleRequest peopleRequest) {
		People people = new People();
		people.setName(peopleRequest.getName());
		PeopleRestService.getPeopleDao().save(people);
		return peopleRequest;
	}
}
