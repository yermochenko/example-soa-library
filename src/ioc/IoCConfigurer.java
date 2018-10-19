package ioc;

public class IoCConfigurer {
	public static void configure() throws IoCException {
		IoCContainer.registerClass("dao.AuthorDao", "dao.fake.AuthorDaoFakeImpl");
		IoCContainer.registerClass("dao.BookDao", "dao.fake.BookDaoFakeImpl");
	}
}
