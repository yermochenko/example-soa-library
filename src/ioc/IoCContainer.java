package ioc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class IoCContainer {
	private static Map<Class<?>, Class<?>> dependencyInversionMap = new HashMap<>();
	private static Map<Class<?>, Map<Class<?>, Method>> dependencyInjectionMap = new HashMap<>();

	private Map<Class<?>, Object> cache = new HashMap<>();

	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> key) throws IoCException {
		T object = (T)cache.get(key);
		if(object == null) {
			try {
				Class<?> value = dependencyInversionMap.get(key);
				if(value != null) {
					object = (T)value.newInstance();
					cache.put(key, object);
					Map<Class<?>, Method> dependencies = dependencyInjectionMap.get(value);
					if(dependencies != null) {
						for(Map.Entry<Class<?>, Method> entry : dependencies.entrySet()) {
							Class<?> dependency = entry.getKey();
							Method injector = entry.getValue();
							injector.invoke(object, get(dependency));
						}
					}
				}
			} catch(InstantiationException | IllegalAccessException | InvocationTargetException e) {
				throw new IoCException(e);
			}
		}
		return object;
	}

	public static void registerClass(String abstraction, String implemetation) throws IoCException {
		registerClass(abstraction, implemetation, null);
	}

	public static void registerClass(String abstraction, String implemetation, Map<String, String> dependencies) throws IoCException {
		try {
			Class<?> actualAbstraction = Class.forName(abstraction);
			Class<?> actualImplemetation = Class.forName(implemetation);
			dependencyInversionMap.put(actualAbstraction, actualImplemetation);
			if(dependencies != null) {
				Map<Class<?>, Method> actualDependencies = new HashMap<>();
				dependencyInjectionMap.put(actualImplemetation, actualDependencies);
				for(Map.Entry<String, String> entry : dependencies.entrySet()) {
					Class<?> dependency = Class.forName(entry.getKey());
					String injectorName = entry.getValue();
					Method injector = actualImplemetation.getMethod(injectorName, dependency);
					actualDependencies.put(dependency, injector);
				}
			}
		} catch(ClassNotFoundException | NoSuchMethodException e) {
			throw new IoCException(e);
		}
	}
}
