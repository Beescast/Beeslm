package projects.commons.service.cache;

import java.io.Serializable;
import java.util.Date;

/**
 * Session中的手机验证码信息
 * @author luinstein
 *
 */
public class MobileCode implements Serializable{

	/**
     */
    private static final long serialVersionUID = 6843728857573413920L;
    private String mobile;//手机号码
	private String code;//验证码信息
	private Date expires;//过期时间
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getExpires() {
		return expires;
	}
	public void setExpires(Date expires) {
		this.expires = expires;
	}
}