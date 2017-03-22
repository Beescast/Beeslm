package org.bees.biz.service.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class UserInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5465959385861122927L;


	private Integer id;

	private String mobile;

	private String nickname;

	private String avatar;

	private String truename;
		
	private int payPass;
	
	private String[] tasks;
	
	private String idCard;

    private String frontPic;

    private String backPic;

    private String handPic;

	private String plat;

	private String liveRoom;

	private String platPic;

	private Date authTime;
	
	private Byte authStatus;
	
	private String email;

    private String contactMobile;

    private String contactAddress;

    private String agencyName;

    private String agencyMobile;

    private String accountName;

    private String bankName;

    private String subBankName;

    private String bankCard;
	
	private String description;

	private String note;

	private String balance;
	
	private String qqId;
	
	private String weixinId;

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
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * @param avatar the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * @return the truename
	 */
	public String getTruename() {
		return truename;
	}

	/**
	 * @param truename the truename to set
	 */
	public void setTruename(String truename) {
		this.truename = truename;
	}

	/**
	 * @return the payPass
	 */
	public int getPayPass() {
		return payPass;
	}

	/**
	 * @param payPass the payPass to set
	 */
	public void setPayPass(int payPass) {
		this.payPass = payPass;
	}

	/**
	 * @return the tasks
	 */
	public String[] getTasks() {
		return tasks;
	}

	/**
	 * @param strings the tasks to set
	 */
	public void setTasks(String[] strings) {
		this.tasks = strings;
	}

	/**
	 * @return the idCard
	 */
	public String getIdCard() {
		return idCard;
	}

	/**
	 * @param idCard the idCard to set
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/**
	 * @return the frontPic
	 */
	public String getFrontPic() {
		return frontPic;
	}

	/**
	 * @param frontPic the frontPic to set
	 */
	public void setFrontPic(String frontPic) {
		this.frontPic = frontPic;
	}

	/**
	 * @return the backPic
	 */
	public String getBackPic() {
		return backPic;
	}

	/**
	 * @param backPic the backPic to set
	 */
	public void setBackPic(String backPic) {
		this.backPic = backPic;
	}

	/**
	 * @return the handPic
	 */
	public String getHandPic() {
		return handPic;
	}

	/**
	 * @param handPic the handPic to set
	 */
	public void setHandPic(String handPic) {
		this.handPic = handPic;
	}

	/**
	 * @return the plat
	 */
	public String getPlat() {
		return plat;
	}

	/**
	 * @param plat the plat to set
	 */
	public void setPlat(String plat) {
		this.plat = plat;
	}

	/**
	 * @return the liveRoom
	 */
	public String getLiveRoom() {
		return liveRoom;
	}

	/**
	 * @param liveRoom the liveRoom to set
	 */
	public void setLiveRoom(String liveRoom) {
		this.liveRoom = liveRoom;
	}

	/**
	 * @return the platPic
	 */
	public String getPlatPic() {
		return platPic;
	}

	/**
	 * @param platPic the platPic to set
	 */
	public void setPlatPic(String platPic) {
		this.platPic = platPic;
	}

	/**
	 * @return the authTime
	 */
	public Date getAuthTime() {
		return authTime;
	}

	/**
	 * @param authTime the authTime to set
	 */
	public void setAuthTime(Date authTime) {
		this.authTime = authTime;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the contactMobile
	 */
	public String getContactMobile() {
		return contactMobile;
	}

	/**
	 * @param contactMobile the contactMobile to set
	 */
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	/**
	 * @return the contactAddress
	 */
	public String getContactAddress() {
		return contactAddress;
	}

	/**
	 * @param contactAddress the contactAddress to set
	 */
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	/**
	 * @return the agencyName
	 */
	public String getAgencyName() {
		return agencyName;
	}

	/**
	 * @param agencyName the agencyName to set
	 */
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	/**
	 * @return the agencyMobile
	 */
	public String getAgencyMobile() {
		return agencyMobile;
	}

	/**
	 * @param agencyMobile the agencyMobile to set
	 */
	public void setAgencyMobile(String agencyMobile) {
		this.agencyMobile = agencyMobile;
	}

	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the subBankName
	 */
	public String getSubBankName() {
		return subBankName;
	}

	/**
	 * @param subBankName the subBankName to set
	 */
	public void setSubBankName(String subBankName) {
		this.subBankName = subBankName;
	}

	/**
	 * @return the bankCard
	 */
	public String getBankCard() {
		return bankCard;
	}

	/**
	 * @param bankCard the bankCard to set
	 */
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
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
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the balance
	 */
	public String getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(String balance) {
		this.balance = balance;
	}

	public Byte getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(Byte authStatus) {
		this.authStatus = authStatus;
	}

	public String getQqId() {
		return qqId;
	}

	public void setQqId(String qqId) {
		this.qqId = qqId;
	}

	public String getWeixinId() {
		return weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserInfoDto [id=" + id + ", mobile=" + mobile + ", nickname="
				+ nickname + ", avatar=" + avatar + ", truename=" + truename
				+ ", payPass=" + payPass + ", tasks=" + Arrays.toString(tasks)
				+ ", idCard=" + idCard + ", frontPic=" + frontPic
				+ ", backPic=" + backPic + ", handPic=" + handPic + ", plat="
				+ plat + ", liveRoom=" + liveRoom + ", platPic=" + platPic
				+ ", authTime=" + authTime + ", authStatus=" + authStatus
				+ ", email=" + email + ", contactMobile=" + contactMobile
				+ ", contactAddress=" + contactAddress + ", agencyName="
				+ agencyName + ", agencyMobile=" + agencyMobile
				+ ", accountName=" + accountName + ", bankName=" + bankName
				+ ", subBankName=" + subBankName + ", bankCard=" + bankCard
				+ ", description=" + description + ", note=" + note
				+ ", balance=" + balance + ", qqId=" + qqId + ", weixinId="
				+ weixinId + "]";
	}
	
	

	
}