package ioc;

public interface Factory<T> {
	T get() throws IoCException;
}
