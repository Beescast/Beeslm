package projects.commons.utils.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.map.type.SimpleType;
import org.codehaus.jackson.type.JavaType;

public class JsonUtils {

	private static Logger log = Logger.getLogger(JsonUtils.class);
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * Generic message thrown while exception occurs on parsing json string
	 * 
	 * @param json
	 * @return
	 */
	private static String throwMessageWhileParseJson(String json) {
		return new StringBuilder().append("Error occurs while parsing json string ")
				.append(json).toString();
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> convertJsonToMap(String json) {
		Map<String, Object> result = new HashMap<String, Object>(0);
		if (StringUtils.isBlank(json)) {
			return result;
		}
		try {
			result = objectMapper.readValue(json, Map.class);
		} catch (JsonParseException e) {
			log.warn(throwMessageWhileParseJson(json), e);
		} catch (JsonMappingException e) {
			log.warn(throwMessageWhileParseJson(json), e);
		} catch(IOException e) {
			log.warn(throwMessageWhileParseJson(json), e);
		}
		return result;
	}

	public static Map<String, Integer> convertJsonToIntegerMap(String json) {
		Map<String, Integer> result=new HashMap<String, Integer>(0);
		if (StringUtils.isBlank(json)) {
			return result;
		}
		try {
			result = objectMapper.readValue(json, MapType.construct(Map.class, 
					SimpleType.construct(String.class), 
					SimpleType.construct(Integer.class)));
		} catch(Exception ex) {
			log.warn(ex.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static Map<String,Object> convertJsonToMapWithouQuotes(String json){
		Map<String,Object> result=new HashMap<String, Object>(0);
		if (StringUtils.isBlank(json)) {
			return result;
		}
		try{
			objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			result=objectMapper.readValue(json,Map.class);
		}catch(Exception ex){
			log.warn(ex.getMessage());
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Object> convertJsonToList(String json){
		List<Object> result = new ArrayList<Object>(0);
		if (StringUtils.isBlank(json)) {
			return result;
		}
		try{
			result=objectMapper.readValue(json, List.class);
		}catch(Exception ex){
			log.warn(ex.getMessage());
		}
		return result;
	}
	
	public static List<Integer> convertJsonToIntegerList(String json){
		List<Integer> result = new ArrayList<Integer>(0);
		if (StringUtils.isBlank(json)) {
			return result;
		}
		try{
			objectMapper.getTypeFactory().constructParametricType(List.class, Integer.class);   
			result=objectMapper.readValue(json, objectMapper.getTypeFactory().constructParametricType(List.class, Integer.class));
		}catch(Exception ex){
			log.warn(ex.getMessage());
		}
		return result;
	}

	public static List<Map<String, Object>> convertJsonToMapList(String json){
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(0);
		if (StringUtils.isBlank(json)) {
			return result;
		}
		try{
			result=objectMapper.readValue(json, CollectionType.construct(List.class, 
					MapType.construct(Map.class, 
							SimpleType.construct(String.class), 
							SimpleType.construct(Object.class))));
		}catch(Exception ex){
			log.warn(ex.getMessage());
		}
		return result;
	}
	

	public static String convertEntity2Json(Object object){
		String result = "";
		if(object==null){
			return result;
		}
		try {
			result =  objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		return result;
	}

	public static String convertEntity2Jsonp(String callback, Object object){
		String result = "";
		if(object==null){
			return result;
		}
		try {
			result =  objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		return callback+"("+result+")";
	}

	public static <T> T convertJson2Entity(String json, Class<T> classType){
		if (StringUtils.isBlank(json)) {
			return null;
		}
		try{
			return objectMapper.readValue(json,classType);
		}catch(Exception ex){
			log.warn(ex.getMessage());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T> T convertJsonToCollection(String json, Class<?> collectionClass, Class<?>... elementClasses){
		if (StringUtils.isBlank(json)) {
			return null;
		}
		try{
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
			return (T)objectMapper.readValue(json, javaType);
		}catch(Exception ex){
			log.warn(ex.getMessage());
		}
		return null;
	}
}