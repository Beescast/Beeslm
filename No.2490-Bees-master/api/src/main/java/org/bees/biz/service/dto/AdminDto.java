package org.bees.biz.service.dto;

import java.io.Serializable;

public class AdminDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5465959385861122927L;

	private Integer id;
	
	private String title;
	
	private String name;
	
	private String ops;
	
	private String url;
	
	private String imgUrl;
	
	private String content;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the ops
	 */
	public String getOps() {
		return ops;
	}

	/**
	 * @param ops the ops to set
	 */
	public void setOps(String ops) {
		this.ops = ops;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the imgUrl
	 */
	public String getImgUrl() {
		return imgUrl;
	}

	/**
	 * @param imgUrl the imgUrl to set
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AdminDto [id=" + id + ", title=" + title + ", name=" + name
				+ ", ops=" + ops + ", url=" + url + ", imgUrl=" + imgUrl
				+ ", content=" + content + "]";
	}
	
	
}