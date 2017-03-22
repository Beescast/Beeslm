package projects.commons.database;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.aspectj.lang.JoinPoint;

public class DataSourceAspect {
    
    private Map<String,Object> maps;
    
    private Random rd = new Random();
    
    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public void pointCut() {
    };

    @SuppressWarnings("unchecked")
	public void before(JoinPoint point) {
        //Object target = point.getTarget();
        String method = point.getSignature().getName();

        if (method.startsWith("update") || method.startsWith("insert")
                || method.startsWith("delete")) {
            DynamicContextHolder.setContextType((String)maps.get("write"));
        } else {
            List<String> list = (List<String>)maps.get("read");
            if (list.size() > 0) {
                DynamicContextHolder.setContextType(list.get((int)(rd.nextDouble()*list.size())));
            }
        }
        
        /*Class<?>[] classz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
        try {
            Method m = classz[0].getMethod(method, parameterTypes);
            System.out.println(m.getName());
            
            
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                DataSource data = m.getAnnotation(DataSource.class);
                System.out.println(data.value());
                HandleDataSource.putDataSource(data.value());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}