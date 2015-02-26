package org.home.gae.shopping.model;

import java.io.Serializable;
import org.home.gae.common.Status;



public class ArticleDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String user;
	private String name;
	private int amount;
	private Status status;
	
	public ArticleDTO(Long id, String user, String name, int amount, Status status) {
		this.id = id;
		this.user = user;
		this.name = name;
		this.amount = amount;
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	

}
