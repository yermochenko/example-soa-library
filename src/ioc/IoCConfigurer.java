package ioc;

import java.util.HashMap;
import java.util.Map;

import web.ActionFactory;

public class IoCConfigurer {
	public static void configure() throws IoCException {
		/* registration of actions */
		IoCContainer.registerFactory("web.Action", "web.ActionFactory");

		Map<String, String> authorActionsDependencies = map(pair("service.AuthorService", "setAuthorService"));
		ActionFactory.registerAction("/author/list", "web.author.AuthorListActionImpl");
		DIContainer.registerClass("web.author.AuthorListActionImpl", authorActionsDependencies);
		ActionFactory.registerAction("/author/edit", "web.author.AuthorEditActionImpl");
		DIContainer.registerClass("web.author.AuthorEditActionImpl", authorActionsDependencies);
		ActionFactory.registerAction("/author/save", "web.author.AuthorSaveActionImpl");
		DIContainer.registerClass("web.author.AuthorSaveActionImpl", authorActionsDependencies);
		ActionFactory.registerAction("/author/delete", "web.author.AuthorDeleteActionImpl");
		DIContainer.registerClass("web.author.AuthorDeleteActionImpl", authorActionsDependencies);

		/* registration of factory for connections */
		IoCContainer.registerFactory("java.sql.Connection", "pool.ConnectionFactory");

		/* registration of DAO */
		Map<String, String> daoDependencies = map(pair("java.sql.Connection", "setConnection"));
		IoCContainer.registerClass("dao.AuthorDao", "dao.mysql.AuthorDaoMySqlImpl");
		DIContainer.registerClass("dao.mysql.AuthorDaoMySqlImpl", daoDependencies);
		IoCContainer.registerClass("dao.BookDao", "dao.mysql.BookDaoMySqlImpl");
		DIContainer.registerClass("dao.mysql.BookDaoMySqlImpl", daoDependencies);

		/* registration of services */
		IoCContainer.registerClass("service.AuthorService", "service.AuthorServiceImpl");
		DIContainer.registerClass("service.AuthorServiceImpl", map(pair("dao.AuthorDao", "setAuthorDao")));
		IoCContainer.registerClass("service.BookService", "service.BookServiceImpl");
		DIContainer.registerClass("service.BookServiceImpl", map(pair("dao.AuthorDao", "setAuthorDao"), pair("dao.BookDao", "setBookDao")));
	}

	private static Map<String, String> map(String[] ... strings) {
		Map<String, String> result = new HashMap<>();
		for(String[] pair : strings) {
			result.put(pair[0], pair[1]);
		}
		return result;
	}

	private static String[] pair(String key, String value) {
		return new String[] {key, value};
	}
}
