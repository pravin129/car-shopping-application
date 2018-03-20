package com.pravin.model;

import javax.persistence.*;

@Entity
@Table(name="car_product_details")
public class ProductDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="product_id")
	private int product_id;
	@Column(name="product_name")
	private String product_name;
	@Column(name="product_price")
	private float product_price;
	@Column(name="product_status")
	private String product_status;
	@Column(name="product_specifications")
	private String product_specifications;
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public float getProduct_price() {
		return product_price;
	}
	public void setProduct_price(float product_price) {
		this.product_price = product_price;
	}
	public String getProduct_status() {
		return product_status;
	}
	public void setProduct_status(String product_status) {
		this.product_status = product_status;
	}
	public String getProduct_specifications() {
		return product_specifications;
	}
	public void setProduct_specifications(String product_specifications) {
		this.product_specifications = product_specifications;
	}
}
