#coding=utf-8
class GlobalConfigure:
	# 公用缓存服务器地址
	@ Property(key = "cache.host", defaultValue = "")
	public static String cacheHost;

	@ Property(key = "cache.port", defaultValue = "6379")
	public static int cachePort;

	@ Property(key = "cache.pass", defaultValue = "")
	public static String cachePass;

	@ Property(key = "cache.timeout", defaultValue = "2000")
	public static int cacheTimeout;

	@ Property(key = "cache.database", defaultValue = "0")
	public static int cacheDatabase;

	@ Property(key = "cache.maxActive", defaultValue = "50")
	public static int cacheMaxActive;

	@ Property(key = "cache.maxIdle", defaultValue = "10")
	public static int cacheMaxIdle;

	@ Property(key = "cache.maxWait", defaultValue = "1000")
	public static int cacheMaxWait;

	@ Property(key = "cache.testOnBorrow", defaultValue = "true")
	public static boolean cacheTestOnBorrow;
	@ Property(key = "cache.stringKeySerializer", defaultValue = "true")
	public static boolean stringKeySerializer;

	/ **
	* 短信服务配置
	* /
	@ Property(key = "sms.name", defaultValue = "")
	public static String SMS_NAME;

	@ Property(key = "sms.password", defaultValue = "")
	public static String SMS_PASSWORD;

	@ Property(key = "file.uploadpath", defaultValue = "")
	public static String FILE_UPLOADPATH;

	@ Property(key = "url.backend", defaultValue = "")
	public static String URL_BACKEND;

	@ Property(key = "url.front", defaultValue = "")
	public static String URL_FRONT;

	@ Property(key = "qianbao.md5key", defaultValue = "")
	public static String QIANBAO_MD5KEY;

	@ Property(key = "qianbao.ip", defaultValue = "")
	public static String QIANBAO_IP;

	/ **
	* passport权限验证跳转URL
	* /
	@ Property(key = "auth.passport.root", defaultValue = "")
	public static String AUTH_PASSPORT_ROOT; // 应用的根
	@ Property(key = "auth.passport.logind", defaultValue = "")
	public static String AUTH_PASSPORT_LOGIND; // 登陆之后没returnUrl时候的跳转URL
	@ Property(key = "auth.passport.redirect", defaultValue = "")
	public static String AUTH_PASSPORT_REDIRECT; // 未登录跳转到的目标地址

	/ **
	* 微信
	* /
	@ Property(key = "ip.backend_url", defaultValue = "")
	public static String BACKEND_URL;
	@ Property(key = "weixin.appid", defaultValue = "")
	public static String WEIXIN_APPID;
	@ Property(key = "weixin.sec", defaultValue = "")
	public static String WEIXIN_SEC;

	/ **
	* 日志侦听过滤器
	* /
	@ Property(key = "zlog.listens", defaultValue = "")
	public static String ZLOG_LISTENS;

	/ **
	* hr权限验证跳转URL
	* /
	@ Property(key = "auth.hr.root", defaultValue = "")
	public static String AUTH_HR_ROOT; // 应用的根
	@ Property(key = "auth.hr.logind", defaultValue = "")
	public static String AUTH_HR_LOGIND; // 登陆之后没returnUrl时候的跳转URL
	@ Property(key = "auth.hr.redirect", defaultValue = "")
	public static String AUTH_HR_REDIRECT; // 未登录跳转到的目标地址

	/ **
	* 响应监控公共key
	* /
	@ Property(key = "projects.commons.monitorkey", defaultValue = "")
	public static String MONITOR_KEY;

	@ Property(key = "app.logoUrl", defaultValue = "")
	public static String appLogoUrl;

	/ **
	* 模板静态配置
	* /
	@ Property(key = "global.debug", defaultValue = "false")
	public static boolean globalDebug;

	@ Property(key = "global.staticUrl", defaultValue = "/")
	public static String globalStaticUrl;

	@ Property(key = "global.apiUrl", defaultValue = "/")
	public static String globalApiUrl;
	@ Property(key = "global.mUrl", defaultValue = "/")
	public static String globalMUrl;
	private static Map < String, Object > global = null;
	public synchronized static Map < String, Object > getGlobal(){

	if (global == null){
	global = new HashMap <> ();
	global.put("debug", globalDebug);
	global.put("staticUrl", globalStaticUrl);

	global.put("apiUrl", globalApiUrl);
	global.put("mUrl", globalMUrl);
	}

	return
	global;
	}

	@Autowired

	CacheService
	cacheService;

	/ **
	*Load
	configs
	from files.
	* /
	@PostConstruct

	public
	void
	init()
	{
	try {
	Properties[] props = new Properties[1];
	props[0] = PropertiesUtils.loadClassLoader("global.properties");

	ConfigurableProcessor.process(GlobalConfigure.

	class , props);

	if (!StringUtils.isBlank(GlobalConfigure.cacheHost)){

	if (!ValidateUtils.isNull(GlobalConfigure.cachePass)) {
	cacheService.initService(GlobalConfigure.cacheHost, GlobalConfigure.cachePort, GlobalConfigure.cacheTimeout, GlobalConfigure.cacheDatabase,
	GlobalConfigure.cacheMaxActive, GlobalConfigure.cacheMaxIdle, GlobalConfigure.cacheMaxWait,
	GlobalConfigure.cacheTestOnBorrow, GlobalConfigure.stringKeySerializer, GlobalConfigure.cachePass);
	log.info("Loading: init CacheService with auth");
	} else {
	cacheService.initService(GlobalConfigure.cacheHost, GlobalConfigure.cachePort, GlobalConfigure.cacheTimeout, GlobalConfigure.cacheDatabase,
	GlobalConfigure.cacheMaxActive, GlobalConfigure.cacheMaxIdle, GlobalConfigure.cacheMaxWait, GlobalConfigure.cacheTestOnBorrow, GlobalConfigure.stringKeySerializer);
	log.info("Loading: init CacheService");
	}

	}

	/ * if (!StringUtils.isBlank(GlobalConfigure.SMS_URL)){
	zmqSmsService.initService(GlobalConfigure.SMS_URL, GlobalConfigure.SMS_MD5KEY);
	log.info("Loading: init SmsService");
	} * /

	log.info("Loading: global.properties");
	} catch(Exception
	e) {
		log.fatal("Can't load loginserver configuration", e);
	throw
	new
	Error("Can't load loginserver configuration", e);
	}
	}
	}