package io.uptake.dto;

import java.util.ArrayList;
import java.util.List;

public class PeopleRestServiceRequest {

	List<PeopleDto> peopleDtoList;

	public List<PeopleDto> getPeopleDtoList() {
		if (this.peopleDtoList == null) {
			this.peopleDtoList = new ArrayList<PeopleDto>();
		}
		return peopleDtoList;
	}

}
