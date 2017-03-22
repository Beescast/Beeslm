package projects.commons.database;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import projects.commons.exception.DaoException;
import projects.commons.utils.ValidateUtils;
import projects.commons.utils.date.DateUtils;
import projects.commons.utils.json.Object2Map;
/**
 * 查询工具
 * @author luinstein
 *
 */
public class Query {
    private static final Log log = LogFactory.getLog(Query.class);

   
    //****************************************************第一部分******************************************************
    /**
     * 本工具中用到的几个内部类和枚举 Filter,Order,FilterType,OrderType,ValueType
     */
    /**
     * 过滤条件
     * 
     * @author qgl
     * 
     */
    public class Filter {
        public String     name;

        public FilterType type;

        public Object     value;

        public ValueType  valueType;

        public Filter(String name, FilterType type, Object value) {
            super();
            this.name = name;
            this.type = type;
            this.value = value;
            this.valueType = ValueType.toValueType(value);
        }

        public String toString() {
            String valueStr = value.toString();
            if (valueType == ValueType.Date) {
                valueStr = String.format("%1$tY-%1$tm-%1$td", value);
            }
            if (valueType == ValueType.DateTime) {
                valueStr = String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS",
                        value);
            }
            if (valueType == ValueType.Float) {
                valueStr = String.format("%.2f", value);
            }
            return name + "," + type + "," + valueStr + "," + valueType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public FilterType getType() {
            return type;
        }

        public void setType(FilterType type) {
            this.type = type;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public ValueType getValueType() {
            return valueType;
        }

        public void setValueType(ValueType valueType) {
            this.valueType = valueType;
        }
    }

    /**
     * 排序条件
     * @author qgl
     *
     */
    public class Order {
        public String    name;

        public OrderType type;

        public Order(String name, OrderType type) {
            super();
            this.name = name;
            this.type = type;
        }

        public String toString() {
            return name + "," + type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public OrderType getType() {
            return type;
        }

        public void setType(OrderType type) {
            this.type = type;
        }
    }

    /**
     * 过滤类型
     * @author qgl
     *
     */
    public enum FilterType {
        LIKE, // 模糊查找
        EQUALS, // 等于
        GREATETHAN, // 大于
        GREATEEQUAL, // 大于等于
        LESSTHAN, // 小于
        LESSEQUAL, // 小于等于
        NOTEQUAL, // 不等于
        IN;

        public static FilterType toType(String type) {
            if ("1".equals(type)) {
                return LIKE;
            }
            if ("2".equals(type)) {
                return EQUALS;
            }
            if ("3".equals(type)) {
                return GREATETHAN;
            }
            if ("4".equals(type)) {
                return GREATEEQUAL;
            }
            if ("5".equals(type)) {
                return LESSTHAN;
            }
            if ("6".equals(type)) {
                return LESSEQUAL;
            }
            if ("7".equals(type)) {
                return NOTEQUAL;
            }
            if ("8".equals(type)) {
                return IN;
            }
            return null;
        }

        public String toString() {
            if (this == LIKE) {
                return "1";
            }
            if (this == EQUALS) {
                return "2";
            }
            if (this == GREATETHAN) {
                return "3";
            }
            if (this == GREATEEQUAL) {
                return "4";
            }
            if (this == LESSTHAN) {
                return "5";
            }
            if (this == LESSEQUAL) {
                return "6";
            }
            if (this == NOTEQUAL) {
                return "7";
            }
            if (this == IN) {
                return "8";
            }
            return "";
        }
    }

    /**
     * 排序类型
     * @author qgl
     *
     */
    public enum OrderType {

        ASC, DESC;

        public static OrderType toType(String type) {
            if ("0".equals(type)) {
                return ASC;
            }
            if ("1".equals(type)) {
                return DESC;
            }
            return null;
        }

        public String toString() {
            if (this == ASC) {
                return "0";
            }
            if (this == DESC) {
                return "1";
            }
            return "";
        }
    }

    /**
     * 值类型
     * @author qgl
     *
     */
    public enum ValueType {
        String, DateTime, Integer, Float, Boolean, Date, Long;
        public static ValueType toValueType(Object type) {
            if (type instanceof String) {
                return String;
            }
            if (type instanceof Timestamp) {
                return DateTime;
            }
            if (type instanceof Integer) {
                return Integer;
            }
            if (type instanceof Float) {
                return Float;
            }
            if (type instanceof Boolean) {
                return Boolean;
            }
            if (type instanceof Date) {
                return Date;
            }
            if (type instanceof Long) {
                return Long;
            }
            return null;
        }

        public static ValueType toType(String type) {
            if ("1".equals(type)) {
                return String;
            }
            if ("2".equals(type)) {
                return DateTime;
            }
            if ("3".equals(type)) {
                return Integer;
            }
            if ("4".equals(type)) {
                return Float;
            }
            if ("5".equals(type)) {
                return Boolean;
            }
            if ("6".equals(type)) {
                return Date;
            }
            if ("7".equals(type)) {
                return Long;
            }
            return null;
        }

        public String toString() {
            if (this == String) {
                return "1";
            }
            if (this == DateTime) {
                return "2";
            }
            if (this == Integer) {
                return "3";
            }
            if (this == Float) {
                return "4";
            }
            if (this == Boolean) {
                return "5";
            }
            if (this == Date) {
                return "6";
            }
            if (this == Long) {
                return "7";
            }
            return "1"; // 默认为String
        }

        public static Object convertValue(String value, ValueType type) {
            if (ValidateUtils.isNull(value)) {
                return null;
            }
            switch (type) {
            case String:
                return value;
            case Date:
                return DateUtils.toDate(value);
            case Boolean:
                return new Boolean(value);
            case Integer:
            	if(ValidateUtils.isInt(value)){
            		return new Integer(value);	
            	}else if(ValidateUtils.isLong(value)){
            		return new Long(value);
            	}
                return new Integer(value);
            case DateTime:
                return DateUtils.toDateTime(value);
            case Float:
                return new Float(value);
            case Long:
                return new Long(value);
            }
            return null;
        }
    }

    //****************************************************第二部分******************************************************
    /**
     * 本工具的几个属性 条件，排序，分页，子表
     */
    //列表页的分类，同一个模板使用在不同展示时候使用
    private int 			listType = 0;
    //针对模糊多条件       关键字可能表示名称或则ID时候使用
    private String			keyword;
    // --------查询信息
    private List<Filter> filters  = new ArrayList<Filter>();

    // --------排序信息
    private List<Order>  orders   = new ArrayList<Order>();

    //---------去掉重复的查询条件
    private Set<String> filterKeys = new HashSet<String>();
    private Set<String> filterNames = new HashSet<String>();

    // --------分组信息
    private String          groupBy;

    //------------分页信息
    private int             pageNo   = 0;                         // 页的序号

    private int             pageSize = 10;                        // 页的大小

    private int             totalCounts;                          // 总共记录数，用来回调

    //------------多表联合查询
    // private Set<String>     from;                                 // 定义 from 的条件

    //private String          select;                               // 定义结果的输出
    
    //private String          selectCount;                          // 如果自定义 select 的情况必须指定总数项

    //private String          where;                                //定义查询的条件

    public Query() {
        super();
    }

    /**
     * 从request中设置Query的信息：过滤信息，排序信息，分页信息
     * @param request
     */
    public Query(HttpServletRequest request){
    	fillByRequest(request);
    }

    //****************************************************第三部分******************************************************
    /**
     * 操作方法和属性方法
     * @return
     */
    /**
     * 添加listType
     */
    public void addListType(HttpServletRequest request) {
        if (request == null) {
            if (log.isWarnEnabled()) {
                log.warn("request==null. return");
            }

            return;
        }

        int tlistType = 0;

        try {
        	tlistType = Integer.parseInt(request.getParameter("listType"));
            log.debug("ListType=" + tlistType);
        } catch (Exception e) {
            log.debug("invalid listType value="
                    + request.getParameter("listType") + ".set listType=0");
        }

        this.listType = tlistType;
    }
    
    /**
     * 操作方法和属性方法
     * @return
     */
    /**
     * 添加keyword
     */
    public void addKeyword(HttpServletRequest request) {
        if (request == null) {
            if (log.isWarnEnabled()) {
                log.warn("request==null. return");
            }

            return;
        }

        this.keyword = request.getParameter("keyword");
    }

    /**
     * 添加条件
     */
    public void addFilter(HttpServletRequest request) {
        if (request == null) {
            if (log.isWarnEnabled()) {
                log.warn("request==null. return");
            }
            return;
        }

        String[] filterNames = request.getParameterValues("filterName");

        for (int i = 0; filterNames != null && i < filterNames.length; i++) {
            log.debug("FilterNames=" + filterNames[i]);
            try {
                String[] values = filterNames[i].split(",");
                String filterName = values[0];
                String filterType = values[1]; // 比较类型
                String filterValue = values[2];
                String valueType = values[3]; // 获得类型
                addFilter(filterName, FilterType.toType(filterType),
                        filterValue, ValueType.toType(valueType));
            } catch (Exception e) {
                if (log.isWarnEnabled()) {
                    log.warn("add filter from request error." + e.getMessage());
                }
            }
        }

        filterNames = (String[]) request.getAttribute("filterName");

        for (int i = 0; (filterNames != null) && (i < filterNames.length); i++) {
            log.debug("FilterNames=" + filterNames[i]);
            try {
                String[] values = filterNames[i].split(",");
                String filterName = values[0];
                String filterType = values[1]; // 比较类型
                String filterValue = values[2];
                String valueType = values[3]; // 获得类型
                addFilter(filterName, FilterType.toType(filterType),
                        filterValue, ValueType.toType(valueType));
            } catch (Exception e) {
                log.error(e);
            }
        }
    }

    public void addFilter(String filterName, FilterType filterType, String filterValue, ValueType valueType) throws DaoException {
        if (ValidateUtils.isNull(valueType)) {
            valueType = ValueType.String; // 为了兼容以前程序
        }
        addFilter(filterName, filterType, ValueType.convertValue(filterValue, valueType));
    }

    public void addFilter(String filterName, FilterType filterType, Object filterValue) throws DaoException {
        if (ValidateUtils.isNull(filterName)
                || ValidateUtils.isNull(filterValue)) {
            if (log.isWarnEnabled()) {
                log.warn("invaild filterName ,filterValue. Name=" + filterName
                        + " value=" + filterValue);
            }
            throw new DaoException("invalid param");
        }
        // 1.只有字符串才能like
        if (!(filterValue instanceof String) && filterType == FilterType.LIKE) {
            throw new DaoException("only string type can do 'like' query");
        }
        // 2.只有list才能in
        if (!(filterValue instanceof List) && filterType == FilterType.IN) {
            throw new DaoException("only list type can do 'in' query");
        }
        log.debug("add a valid filter=" + filterName + ",filterType="
                + filterType + ",filterValue" + filterValue);

        Filter filter = new Filter(filterName, filterType, filterValue);

        if(filterKeys.contains(filter.toString())){
        	log.debug("filter is has="+filter.toString());
        }else{
        	filters.add(new Filter(filterName, filterType, filterValue));
        	filterKeys.add(filter.toString());
        	filterNames.add(filterName);
        }
    }

    public int getListType() {
		return listType;
	}
    
    public String getKeyword() {
		return keyword;
	}

    public List<Filter> getFilters() {
		return filters;
	}

    public Filter getFilter(String key) {
    	for(Filter filter : filters){
    		if(key.equals(filter.name)){
    			return filter;
    		}
    	}
		return null;
	}

    /**
     * 添加分组
     */
    /**
     * 定义分组的条件
     */
    public void addGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }
    
    /**
     * 获得分组的条件
     */
    public String getGroupBy() {
        return groupBy;
    }

    /**
     * 添加排序
     */
    public void addOrder(HttpServletRequest request) {
        if (request == null) {
            if (log.isWarnEnabled()) {
                log.warn("request==null. return");
            }

            return;
        }

        String[] orderNames = request.getParameterValues("orderName");

        for (int i = 0; (orderNames != null) && (i < orderNames.length); i++) {
            log.debug("orderNames=" + orderNames[i]);

            try {
                String[] values = orderNames[i].split(",");
                String orderName = values[0];
                String orderType = values[1]; // 比较类型
                addOrder(orderName, OrderType.toType(orderType));
            } catch (Exception e) {
                log.error(e);
            }
        }

        // 2006-03-19
        orderNames = (String[]) request.getAttribute("orderName");

        for (int i = 0; (orderNames != null) && (i < orderNames.length); i++) {
            log.debug("orderNames=" + orderNames[i]);

            try {
                String[] values = orderNames[i].split(",");
                String orderName = values[0];
                String orderType = values[1]; // 比较类型
                addOrder(orderName, OrderType.toType(orderType));
            } catch (Exception e) {
                log.error(e);
            }
        }
    }

    public void addOrder(String name, OrderType orderType) throws DaoException {
        if (ValidateUtils.isNull(name)) {
            if (log.isWarnEnabled()) {
                log.warn("invaild name and orderType. Name=" + name
                        + " OrderType=" + orderType);
            }

            throw new DaoException("invalid param");
        }

        for (Order order : orders) {
            if (name.equals(order.name)) {
                if (log.isWarnEnabled()) {
                    log.warn("order name=" + name
                            + " already existed!.can't add order.return");
                }

                return;
            }
        }
        log.debug("add a valid order=" + name + ",type=" + orderType);
        orders.add(new Order(name, orderType));
    }
    
	public List<Order> getOrders() {
		return orders;
	}

	 /**
     * 设置当前页数
     * @param pageNo
     */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	/**
     * 获得当前页数
     * @return
     */
	public int getPageNo() {
		return pageNo;
	}

	/**
     * 设置每页大小
     * 
     * @return
     */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
     * 获得每页大小
     * 
     * @return
     */
	public int getPageSize() {
		return pageSize;
	}

	/**
     * 设置总的大小数
     * 
     * @return
     */
    public void setTotalCounts(int totalCounts) {
        this.totalCounts = totalCounts;

        // 当设置了总数的时候,需要判断当前的分页是否有效
        if ((pageNo * pageSize) > totalCounts) {
            pageNo = 0;
        }
    }

	public int getTotalCounts() {
		return totalCounts;
	}

	/**
     * 添加分页
     */
    public void addPage(HttpServletRequest request) {
        addPage(request, true);
    }

    public void addPage(HttpServletRequest request, boolean logic) {
        //逻辑页号从1开始 物理页号从0开始
        if (request == null) {
            if (log.isWarnEnabled()) {
                log.warn("request==null. return");
            }

            return;
        }

        int tPageNo = 0;
        int tPageSize = 20;

        try {
            tPageNo = Integer.parseInt(request.getParameter("pageNo"));
            if (logic) {
                tPageNo = tPageNo - 1; // //逻辑页号修正到物理页号
            }
            log.debug("PageNo=" + tPageNo);
        } catch (Exception e) {
            log.debug("invalid pageNo value="
                    + request.getParameter("pageNo") + ".set pageNo=0");
        }

        try {
            tPageSize = Integer.parseInt(request.getParameter("pageSize")); // 页的大小
            log.debug("pageSize=" + tPageSize);
        } catch (Exception e) {
            log.debug("invalid pageSize value="
                    + request.getParameter("pageSize") + " .set pageSize="
                    + tPageSize);
        }

        if ((request.getAttribute("pageNo") != null)
                && (request.getAttribute("pageSize") != null)) {
            try {
                tPageNo = ((Integer) request.getAttribute("pageNo"))
                        .intValue();
                if (logic) {
                    tPageNo = tPageNo - 1; // //逻辑页号修正到物理页号
                }
                log.debug("pageNo=" + tPageNo);
            } catch (Exception e) {
                log.debug("invalid indexPage value.set PageNo=0");
            }

            try {
                tPageSize = ((Integer) request.getAttribute("pageSize"))
                        .intValue(); // 页的大小
                log.debug("pageSize=" + tPageSize);
            } catch (Exception e) {
                log.debug("invalid pageSize value.set pageSize=" + tPageSize);
            }
        }

        // 2006-12-08
        // 检查queryparam是否包含了totalCounts,可以避免每次都执行一次,在大数据集的时候可以加快速度
        try {
            int tTotalCounts = Integer.parseInt(request
                    .getParameter("totalCounts"));
            log.debug("totalCounts=" + tTotalCounts);

            if (totalCounts > 0) {
                totalCounts = tTotalCounts;
            }
        } catch (Exception e) {
            log.debug("invalid totalCounts. ");
        }

        try {
            int tTotalCounts = ((Integer) request.getAttribute("totalCounts"))
                    .intValue();
            log.debug("totalCounts=" + tTotalCounts);

            if (totalCounts > 0) {
                totalCounts = tTotalCounts;
            }
        } catch (Exception e) {
            log.debug("invalid totalCounts. ");
        }

        setPage(tPageNo, tPageSize);
    }
    
    /**
     * 设置分页信息
     * 
     * @param indexPage
     * @param pageSize
     * @throws SqlException
     */
    public void setPage(int indexPage, int pageSize) throws DaoException {
        if ((indexPage < 0) || (pageSize < 0)) {
            if (log.isWarnEnabled()) {
                log.warn("invaild indexpage and pageSize. IndexPage="
                        + indexPage + " pageSize=" + pageSize
                        + ". Set default value");
            }

            throw new DaoException("invalid param");
        }

        this.pageNo = indexPage;
        this.pageSize = pageSize;
    }

    /**
     * 获得页的大小
     * 
     * @return
     */
    public int getTotalPage() {
        // 如果是不分页,则总页数为1
        if (getPageSize() <= 0) {
            return 1;
        }

        int temp = getTotalCounts() / getPageSize();

        if ((getTotalCounts() % getPageSize()) > 0) {
            // 如果是有余数，则页数加一
            temp++;
        }

        return temp;
    }
    
    //****************************************************第四部分******************************************************
    /**
     * 从request中设置QueryParam的信息：过滤信息，排序信息，分页信息
     * 
     * @param request
     */
    public void fillByRequest(HttpServletRequest request) {
    	addListType(request);
    	addKeyword(request);
        addFilter(request);
        addOrder(request);
        addPage(request);
    }
    /**
     * 几个属性的状态判断方法
     */
    
    /**
     * 是否需要分页(pageSize>0)
     * 
     * @return
     */
    public boolean isNeedPage() {
        return (pageSize > 0);
    }

    /**
     * 是否需要过滤
     * 
     * @return
     */
    public boolean isNeedFilter() {
        return (filters.size() > 0);
    }

    /**
     * 是否存在指定条件
     * @param filterName
     * @return
     */
    public boolean isNeedFilter(String filterName){
    	return filterNames.contains(filterName);
    }

    /**
     * 是否需要分组
     * 
     * @return
     */
    public boolean isNeedGroupBy() {
        return groupBy!=null&&!"".equals(groupBy);
    }

    /**
     * 是否需要排序
     * 
     * @return
     */
    public boolean isNeedOrder() {
        return (orders.size() > 0);
    }

    //****************************************************第五部分******************************************************
    /**
     * 生成example
     */
    public <E> E createExample(Query query, Class<E> exampleClass) throws DaoException{
		try {
			E example = exampleClass.newInstance();
			Method createMethod = exampleClass.getDeclaredMethod("createCriteria");
			Object criteria = createMethod.invoke(example);

			//条件
			if (isNeedFilter()) {
				List<Filter> filters = getFilters();
		        for (int i = 0; filters != null && i < filters.size(); i++) {
		            Filter filter = filters.get(i);
		            String filterName = filter.name.substring(0,1).toUpperCase()+filter.name.substring(1);
		            FilterType filterType = filter.type;
		            String filterTypStr = "";
		            Object filterValue = filter.value;
		            switch (filterType) {
		            case LIKE:
		                filterTypStr = "Like";
		                break;
		            case EQUALS:
		                filterTypStr = "EqualTo";
		                break;
		            case GREATETHAN:
		                filterTypStr = "GreaterThan";
		                break;
		            case GREATEEQUAL:
		                filterTypStr = "GreaterThanOrEqualTo";
		                break;
		            case LESSTHAN:
		                filterTypStr = "LessThan";
		                break;
		            case LESSEQUAL:
		                filterTypStr = "LessThanOrEqualTo";
		                break;
		            case NOTEQUAL:
		                filterTypStr = "NotEqualTo";
		                break;
		            case IN:
		                filterTypStr = "In";
		                break;
		            }

		            try{
			            String methodName = "and" + filterName + filterTypStr ;
		                Method filterMethod = criteria.getClass().getDeclaredMethod(methodName, filterValue.getClass());
		                filterMethod.invoke(criteria, filterValue);
		            }catch(java.lang.NoSuchMethodException e){
	            	
		            }
		        }
			}

			//分组
			if(isNeedGroupBy()){
				try{
					Method groupByMethod = exampleClass.getDeclaredMethod("setGroupByClause", groupBy.getClass());
					groupByMethod.invoke(example, Object2Map.keyName(groupBy));
				}catch(java.lang.NoSuchMethodException e){
	            	
	            }
			}

			//排序
	        if (isNeedOrder()) {
	            String orderStr = "";
	            for (int i = 0; orders != null && i < orders.size(); i++) {
	                Order order = orders.get(i);
	                String name = order.name;
	                OrderType type = order.type;
	                if (type == OrderType.ASC) {
	                    orderStr += name + " asc";
	                }
	                if (type == OrderType.DESC) {
	                    orderStr += name + " desc";
	                }
	                if (i != orders.size() - 1) {
	                    orderStr += " , ";
	                }
	            }

	            if(orderStr!=null && !"".equals(orderStr)){
	            	try{
	            		Method orderByMethod = exampleClass.getDeclaredMethod("setOrderByClause", orderStr.getClass());
	            		orderByMethod.invoke(example, Object2Map.keyName(orderStr));
	            	}catch(java.lang.NoSuchMethodException e){
		            	
		            }
	            }
	        }
	        
	        return example;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}