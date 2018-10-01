package dao;

import domain.Entity;

public interface Dao<T extends Entity> {
	Long create(T entity);

	T read(Long id);

	void update(T entity);

	void delete(Long id);
}
