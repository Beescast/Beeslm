package projects.commons.utils.config;
/**
 * Redis的key统一管理，保证多个应用场景中不冲突
 * @author qgl
 *
 */
public class GlobalKeys {
	/**
	 * 未登录串
	 */
	public final static String NOT_LOGIN_RES= "{\"code\":600,\"messages\":[],\"data\":{}}";
	
	/**
	 * Token无效
	 */
	public final static String NOT_TOKEN_RES= "{\"code\":500,\"messages\":[{\"id\":\"doAction\",\"message\":\"token无效\"}],\"data\":{}}";
	
	
	public final static String SEC_URL_KEYS = "__SEC_URL__";
	public final static String SEC_FUNC_KEYS = "__SEC_FUNC__";
	
	/**
	 * 用户中心
	 */
	public final static String PASSPORT_LOGIN_KEY= "__PASSPORT_LOGIIN__";
	public final static String PASSPORT_LOGIN_ERROR_KEY= "__PASSPORT_LOGIIN_ERROR__";
	public final static String PASSPORT_REG_MCODE_KEY= "__PASSPORT_REG_MCODE__";
	public final static String PASSPORT_PWD_MCODE_KEY= "__PASSPORT_PWD_MCODE__";

	/**
	 * 公司注册
	 */
	public final static String COMPANY_LOGIN_KEY= "__COMPANY_LOGIIN__";
	public final static String COMPANY_REG_MCODE_KEY= "__COMPANY_REG_MCODE__";
	
	/**
     * 短信限制
     */
    public final static String SMS_IP_LIMIT= "__SMS_IP_LIMIT__";
    public final static String SMS_MOBILE_LIMIT= "__SMS_MOBILE_LIMIT__";
	
	/**
	 * 公司排名
	 */
	public final static String COMPANY_CITY_RANK_KEY= "__COMPANY_CITY_RANK__";
	public final static String COMPANY_INDUSTRY_RANK_KEY= "__COMPANY_INDUSTRY_RANK__";


	

	/**
	 * 设备_用户唯一性
	 */
	public final static String DEVKEY_USRID_CHECK_KEY= "__DEVKEY_SESSIONKEY_CHECK_KEY__";
	
	
	/**
     * 联系人手机信息修改
     */
	public final static String HR_UPDATE_CONTACTPHONE_KEY= "__HR_UPDATE_CONTACTPHONE_KEY__";
	/**
     * 实名认证
     */
    public final static String PAY_USER_BIND_KEY= "__PAY_USER_BIND_KEY__";
	/**
	 * 新提现验证
	 */
	public final static String PAY_USER_NEW_CASH_KEY= "__PAY_USER_NEW_CASH_KEY__";
	/**
	 * 
	 */
	public final static String PAY_USER_CASH_KEY= "__PAY_USER_CASH_KEY__";
	/**
	 * IM用户
	 */
	public final static String PUSH_USER_IM_KEY="__PUSH_USER_IM_KEY__";
	/**
	 * 验证码
	 */
	public final static String VERIFY_CODE_KEY="__VERIFY_CODE__";
	/**
	 * 作弊IP
	 */
	public final static String CHEAT_KEY="__CHEAT__";
	
	/**
     * OAuth2联合登录
     */
    public final static String OAUTH_AUTH_CODE= "__OAUTH_AUTH_CODE__";
    
    public final static String OAUTH_ACCESS_TOKEN= "__OAUTH_ACCESS_TOKEN__";
    
    public final static String OAUTH_REFRESHACCESS_TOKEN= "__OAUTH_REFRESHACCESS_TOKEN__";
    /**
     * 二度人脉
     */
    public final static String RELATION_2_FRIEND= "__NEW_RELATION_2_FRIEND__";
    /**
     * 一度人脉
     */
    public final static String RELATION_FRIEND= "__RELATION_FRIEND__";
    /**
     * 共同好友
     */
    public final static String MUTUAL_FRIEND= "__MUTUAL_FRIEND__";
    
    /**
     * 人脉用户
     */
    public final static String RELATION_USR= "__RELATION_USR__";
    /**
     * 二度人脉的共同好友
     */
    public final static String RELATION_2_FRIEND_MUTUAL= "__NEW_RELATION_2_FRIEND_MUTUAL__";

	public final static String MD5KEY = "LVTEA";
    
}