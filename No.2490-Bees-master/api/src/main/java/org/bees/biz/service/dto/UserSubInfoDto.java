package org.bees.biz.service.dto;

import java.io.Serializable;
import java.util.List;

public class UserSubInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5465959385861122927L;

	private Integer id;
	
	private String headImg;
	
	private String title;
	
	private String description;
	
	private List<String> imgList;

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
	 * @return the headImg
	 */
	public String getHeadImg() {
		return headImg;
	}

	/**
	 * @param headImg the headImg to set
	 */
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the imgList
	 */
	public List<String> getImgList() {
		return imgList;
	}

	/**
	 * @param imgList the imgList to set
	 */
	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserSubInfoDto [id=" + id + ", headImg=" + headImg + ", title="
				+ title + ", description=" + description + ", imgList="
				+ imgList + "]";
	}

	
}