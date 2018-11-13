package ioc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class IoCContainer implements AutoCloseable {
	private static Map<Class<?>, Class<?>> dependencyInversionMap = new HashMap<>();
	private static Map<Class<?>, Map<Class<?>, Method>> dependencyInjectionMap = new HashMap<>();
	private static Map<Class<?>, Factory<?>> factories = new HashMap<>();

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
				} else {
					Factory<?> factory = factories.get(key);
					if(factory != null) {
						object = (T)factory.get();
						cache.put(key, object);
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

	public static void registerFactory(String abstraction, String factory) throws IoCException {
		try {
			Class<?> actualAbstraction = Class.forName(abstraction);
			Class<?> actualFactory = Class.forName(factory);
			factories.put(actualAbstraction, (Factory<?>)actualFactory.newInstance());
		} catch(ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new IoCException(e);
		}
	}

	@Override
	public void close() {
		for(Object object : cache.values()) {
			if(object instanceof AutoCloseable) {
				try { ((AutoCloseable)object).close(); } catch(Exception e) {}
			}
		}
	}
}
