package com.sm.rjguide;

import java.util.ArrayList;
import java.util.List;

public class PersonArray {

	private List list;

	public PersonArray() {
		super();
		list = new ArrayList();
	}

	public void addToList(PersonEntity personEntity) {
		list.add(personEntity);
	}

	public List getList() {
		return list;
	}

	@Override
	public String toString() {
		return "PersonArray " + list.toString();
	}
}
