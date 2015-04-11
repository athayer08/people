package io.uptake.rest;

import java.util.List;

import io.uptake.dao.PeopleDao;
import io.uptake.dto.PeopleDto;
import io.uptake.dto.PeopleRestServiceRequest;
import io.uptake.dto.PeopleRestServiceResponse;
import io.uptake.ldm.People;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
public class PeopleRestService {

	@Autowired
	private PeopleDao peopleDao;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody PeopleRestServiceResponse addPeople(
			@RequestBody PeopleRestServiceRequest peopleRequest) {
		PeopleRestServiceResponse peopleRestServiceResponse = new PeopleRestServiceResponse();
		for (PeopleDto peopleDto : peopleRequest.getPeopleDtoList()) {
			People people = new People();
			people.setName(peopleDto.getName());
			this.peopleDao.create(people);
			peopleDto.setId(people.getId());
			peopleDto.setStatus("Success");
			peopleRestServiceResponse.getPeopleDtoList().add(peopleDto);
		}
		return peopleRestServiceResponse;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody PeopleRestServiceResponse updatePeople(
			@RequestBody PeopleRestServiceRequest peopleRequest) {
		PeopleRestServiceResponse peopleRestServiceResponse = new PeopleRestServiceResponse();
		for (PeopleDto peopleDto : peopleRequest.getPeopleDtoList()) {
			People people = this.peopleDao.readById(peopleDto.getId());
			if (people != null) {
				if (!peopleDto.getName().equals(people.getName())) {
					people.setName(peopleDto.getName());
					this.peopleDao.update(people);
				}
				peopleDto.setStatus("Success");
			} else {
				peopleDto.setStatus("Person Not Found");
			}
			peopleRestServiceResponse.getPeopleDtoList().add(peopleDto);
		}
		return peopleRestServiceResponse;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody PeopleRestServiceResponse getPeopleById(
			@PathVariable int id) {
		PeopleRestServiceResponse peopleRestServiceResponse = new PeopleRestServiceResponse();
		People people = this.peopleDao.readById(id);
		PeopleDto peopleDto = new PeopleDto();
		peopleDto.setId(id);
		if (people == null) {
			peopleDto.setStatus("Person Not Found");
		} else {
			peopleDto.setName(people.getName());
			peopleDto.setStatus("Success");
		}
		peopleRestServiceResponse.getPeopleDtoList().add(peopleDto);
		return peopleRestServiceResponse;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody PeopleRestServiceResponse getAllPeople() {
		PeopleRestServiceResponse peopleRestServiceResponse = new PeopleRestServiceResponse();
		List<People> peopleList = this.peopleDao.readAll();
		for (People people : peopleList) {
			PeopleDto peopleDto = new PeopleDto();
			peopleDto.setId(people.getId());
			peopleDto.setName(people.getName());
			peopleDto.setStatus("Success");
			peopleRestServiceResponse.getPeopleDtoList().add(peopleDto);
		}
		return peopleRestServiceResponse;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody PeopleRestServiceResponse deletePeople(
			@PathVariable int id) {
		PeopleRestServiceResponse peopleRestServiceResponse = new PeopleRestServiceResponse();
		People people = this.peopleDao.readById(id);
		PeopleDto peopleDto = new PeopleDto();
		peopleDto.setName(people.getName());
		peopleDto.setId(id);
		if (people == null) {
			peopleDto.setStatus("Person Not Found");
		} else {
			this.peopleDao.delete(id);
			peopleDto.setStatus("Success");
			peopleRestServiceResponse.getPeopleDtoList().add(peopleDto);
		}
		return peopleRestServiceResponse;
	}
}
