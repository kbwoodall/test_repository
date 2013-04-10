package com.sm.anapp;

public class AddressEntity {

	private int id;
	private String distributor;
	private String product;
	private String route;
	private String street;	
	private String idAddress;	

	public AddressEntity(int id, String distributor, String product, String route, String street, String idAddress) {
		super();
		this.id = id;
		this.distributor = distributor;
		this.product = product;
		this.route = route;
		this.street = street;
		this.idAddress = idAddress;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
	
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}

	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getIdAddress() {
		return idAddress;
	}
	
	public void setIdAddress(String idAddress) {
		this.idAddress = idAddress;
	}
	

	@Override
	public String toString() {
		return "route="+ route+", street="+street+", id="+id+", distributor="+distributor;
	}
}
