package ioc;

import java.util.HashMap;
import java.util.Map;

public class IoCConfigurer {
	public static void configure() throws IoCException {
		IoCContainer.registerFactory("java.sql.Connection", "pool.ConnectionFactory");

		Map<String, String> daoDependencies = new HashMap<>();
		daoDependencies.put("java.sql.Connection", "setConnection");
		IoCContainer.registerClass("dao.AuthorDao", "dao.mysql.AuthorDaoMySqlImpl", daoDependencies);
		IoCContainer.registerClass("dao.BookDao", "dao.mysql.BookDaoMySqlImpl", daoDependencies);

		Map<String, String> authorServiceDependencies = new HashMap<>();
		authorServiceDependencies.put("dao.AuthorDao", "setAuthorDao");
		IoCContainer.registerClass("service.AuthorService", "service.AuthorServiceImpl", authorServiceDependencies);

		Map<String, String> bookServiceDependencies = new HashMap<>();
		bookServiceDependencies.put("dao.AuthorDao", "setAuthorDao");
		bookServiceDependencies.put("dao.BookDao", "setBookDao");
		IoCContainer.registerClass("service.BookService", "service.BookServiceImpl", bookServiceDependencies);
	}
}
