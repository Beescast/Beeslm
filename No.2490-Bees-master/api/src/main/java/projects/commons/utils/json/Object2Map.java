package projects.commons.utils.json;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;


@SuppressWarnings({ "rawtypes", "unchecked" })
public class Object2Map {
	
	public static Map<String, Object> put(final Object obj, final Map<String, Object> map) {
		
		for(final Field field : obj.getClass().getDeclaredFields()) {
			if(Modifier.isStatic(field.getModifiers()) || Modifier.isNative(field.getModifiers()) 
					|| Modifier.isTransient(field.getModifiers())) {
				continue;
			}
			field.setAccessible(true);
			try {
				map.put(keyName(field.getName()), field.get(obj));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		for(final Field field : obj.getClass().getSuperclass().getDeclaredFields()) {
			if(Modifier.isStatic(field.getModifiers()) || Modifier.isNative(field.getModifiers()) 
					|| Modifier.isTransient(field.getModifiers())) {
				continue;
			}
			field.setAccessible(true);
			try {
				map.put(keyName(field.getName()), field.get(obj));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return map;
	}
	/**
	 * 
	 * 
	 * 为空的属性则不显示
	 * 
	 * @param obj
	 * @param map
	 * @return
	 */
	
	public static Map<String, Object> putSelect(final Object obj) {
		 Map map  = new HashMap<String, Object>();
		for(final Field field : obj.getClass().getDeclaredFields()) {
			if(Modifier.isStatic(field.getModifiers()) || Modifier.isNative(field.getModifiers()) 
					|| Modifier.isTransient(field.getModifiers())) {
				continue;
			}
			field.setAccessible(true);
			try {
				if(field.get(obj)!=null){
				map.put(keyName(field.getName()), field.get(obj));
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	
	
	/**
	 * List中的对象变为Map返回List
	 * @param list
	 * @return
	 */
	public static List<Map<String, Object>> list2MapList(final List<?> list) {
		if(!CollectionUtils.isEmpty(list)){
			final ArrayList<Map<String, Object>> ret = new ArrayList<>(list.size());
			for(final Object obj : list) {
				ret.add(put(obj));
			}
			return ret;
		}
		return new ArrayList<>(0);
	}
	
	/**
	 * List中对象的某一属性为键值，构造Map，对象存入值中 返回map
	 * @param list  List<Object>
	 * @param fieldName
	 * @param clazz
	 * @return
	 */
	public static <T, C> Map<C, T> list2Map(final List<T> list, final String fieldName, Class<C> clazz ) {
		if(!CollectionUtils.isEmpty(list)){
			final Map<C, T> ret = new HashMap<>(list.size());
			for(final T obj : list) {
				
				for(final Field field : obj.getClass().getDeclaredFields()) {
					if(Modifier.isStatic(field.getModifiers()) || Modifier.isNative(field.getModifiers()) 
							|| Modifier.isTransient(field.getModifiers())) {
						continue;
					}
					field.setAccessible(true);
					if(fieldName.equals(field.getName())){
						try {
							ret.put((C)field.get(obj), obj);
						} catch (IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
						}
						break;
					}
				}
				for(final Field field : obj.getClass().getSuperclass().getDeclaredFields()) {
					if(Modifier.isStatic(field.getModifiers()) || Modifier.isNative(field.getModifiers()) 
							|| Modifier.isTransient(field.getModifiers())) {
						continue;
					}
					field.setAccessible(true);
					if(fieldName.equals(field.getName())){
						try {
							ret.put((C)field.get(obj), obj);
						} catch (IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
						}
						break;
					}
				}
			}
			return ret;
		}
		return new HashMap<>(0);
	}
	
	/**
	 * List中取某一属性，构造新List，包括了所选择的属性
	 * @param list
	 * @param fieldName
	 * @param clazz
	 * @return
	 */
	public static <T,C> List<C> list2list(final List<T> list, final String fieldName, Class<C> clazz ) {
		if(!CollectionUtils.isEmpty(list)){
			final List<C> ret = new ArrayList<>(list.size());
			for(final T obj : list) {
				
				for(final Field field : obj.getClass().getDeclaredFields()) {
					if(Modifier.isStatic(field.getModifiers()) || Modifier.isNative(field.getModifiers()) 
							|| Modifier.isTransient(field.getModifiers())) {
						continue;
					}
					field.setAccessible(true);
					if(fieldName.equals(field.getName())){
						try {
							if(!ret.contains((C)field.get(obj))){
								ret.add((C)field.get(obj));
							}
						} catch (IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
						}
						break;
					}
				}
				for(final Field field : obj.getClass().getSuperclass().getDeclaredFields()) {
					if(Modifier.isStatic(field.getModifiers()) || Modifier.isNative(field.getModifiers()) 
							|| Modifier.isTransient(field.getModifiers())) {
						continue;
					}
					field.setAccessible(true);
					if(fieldName.equals(field.getName())){
						try {
							if(!ret.contains((C)field.get(obj))){
								ret.add((C)field.get(obj));
							}
						} catch (IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
						}
						break;
					}
				}
			}
			return ret;
		}
		return new ArrayList<>(0);
	}
	
	public static Map<String, Object> int2str(final Map<String, Object> map) {
		for(final Map.Entry<String, Object> entry : map.entrySet()) {
			if(entry.getValue() instanceof Integer) {
				entry.setValue(entry.getValue().toString());
			}
		}
		return map;
	}
	
	public static String keyName(final String key) {
		final StringBuilder sb = new StringBuilder();
		for(int i = 0; i < key.length(); i++) {
			final char c = key.charAt(i);
			if(Character.isUpperCase(c)) {
				sb.append('_').append((char)(c+'a'-'A'));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	public static Map<String, Object> put(final Object obj) {
		if (obj == null) {
			return new HashMap<String, Object>();
		}
		return put(obj, new HashMap<String, Object>());
	}
	
	/**
	 * 替换map中的key名字
	 * @param target
	 * @param regex 替换的目标
	 * @param replacement 替换的值
	 * @return
	 */
	public static List<Map<String, Object>> replaceMapKey(List<Map<String, Object>> target, String regex, String replacement){
		if(!CollectionUtils.isEmpty(target)){
			for(Map<String, Object> m : target){
				if(m.containsKey(regex) && !m.containsKey(replacement)){
					m.put(replacement, m.get(regex));
					m.remove(regex);
				}
			}
		}
		return target;
	}
	/**
	 *
	 * 把list<T>  => Map<C,List<T>>
	 * C是T的一个属性 
	 * @param list
	 * @param fieldName
	 * @param clazz
	 * @return
	 */
	public static <T, C> Map<C,List<T>> list2MapList(final List<T> list, final String fieldName, Class<C> clazz ) {
		Map<C,List<T>> returnMap =  new HashMap<C,List<T>>();
		for(T t:list){
			for(final Field field : t.getClass().getDeclaredFields()) {
				if(Modifier.isStatic(field.getModifiers()) || Modifier.isNative(field.getModifiers()) 
						|| Modifier.isTransient(field.getModifiers())) {
					continue;
				}
				field.setAccessible(true);
				if(fieldName.equals(field.getName())){
					try {
						C key  = (C)field.get(t);
						if(returnMap.get(key)==null){
							List<T> listn = new ArrayList<T>();
							listn.add(t);
							returnMap.put((C)field.get(t), listn);
						}else{
							List<T> listp = returnMap.get(key);
							listp.add(t);
							returnMap.put((C)field.get(t), listp);
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
		
		return returnMap;
	}
	
	public static void main(String[] args) {
//		final BigDecimal bigDecimal = new BigDecimal("1234567890");
//		System.out.println(put(bigDecimal));
		
		
	}
	

	
}