package com.sm.anapp;

import java.util.ArrayList;
import java.util.List;

public class AddressArray {

	private List list;

	public AddressArray() {
		super();
		list = new ArrayList();
	}

	public void addToList(AddressEntity addressEntity) {
		list.add(addressEntity);
	}

	public List getList() {
		return list;
	}

	@Override
	public String toString() {
		return "AddressArray " + list.toString();
	}
}
