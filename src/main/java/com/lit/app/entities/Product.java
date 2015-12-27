package com.lit.app.entities;

import java.util.Date;

public class Product {
	
	private int id;
	private String name;
	private float price;
	private int stock;
	private Date cdate;
	private Date udate;
	private boolean enabled;
	private User cby;
	private User uby;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public Date getUdate() {
		return udate;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public User getCby() {
		return cby;
	}
	public void setCby(User cby) {
		this.cby = cby;
	}
	public User getUby() {
		return uby;
	}
	public void setUby(User uby) {
		this.uby = uby;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", stock=" + stock + ", cdate=" + cdate
				+ ", udate=" + udate + ", enabled=" + enabled + ", cby=" + cby + ", uby=" + uby + "]";
	}
}
