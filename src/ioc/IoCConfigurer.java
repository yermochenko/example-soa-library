package ioc;

import java.util.HashMap;
import java.util.Map;

public class IoCConfigurer {
	public static void configure() throws IoCException {
		IoCContainer.registerClass("dao.AuthorDao", "dao.fake.AuthorDaoFakeImpl");
		IoCContainer.registerClass("dao.BookDao", "dao.fake.BookDaoFakeImpl");

		Map<String, String> authorServiceDependencies = new HashMap<>();
		authorServiceDependencies.put("dao.AuthorDao", "setAuthorDao");
		IoCContainer.registerClass("service.AuthorService", "service.AuthorServiceImpl", authorServiceDependencies);

		Map<String, String> bookServiceDependencies = new HashMap<>();
		bookServiceDependencies.put("dao.AuthorDao", "setAuthorDao");
		bookServiceDependencies.put("dao.BookDao", "setBookDao");
		IoCContainer.registerClass("service.BookService", "service.BookServiceImpl", bookServiceDependencies);
	}
}
