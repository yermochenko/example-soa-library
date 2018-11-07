package pool;

import java.sql.Connection;

import ioc.Factory;
import ioc.IoCException;

public class ConnectionFactory implements Factory<Connection> {
	@Override
	public Connection get() throws IoCException {
		try {
			return ConnectionPool.getInstance().getConnection();
		} catch(PoolException e) {
			throw new IoCException(e);
		}
	}
}
