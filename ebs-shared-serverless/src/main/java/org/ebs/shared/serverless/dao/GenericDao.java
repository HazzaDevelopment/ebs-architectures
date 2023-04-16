package org.ebs.shared.serverless.dao;

import java.sql.Connection;
import java.util.List;

public abstract class GenericDao<T> {

    protected final Connection conn;

    public GenericDao(Connection connection){
        this.conn = connection;
    }

    public abstract List<T> findAll();
    public abstract int save(T t);
    public abstract int saveAll(List<T> t);

    public T findByType(String type) {
        throw new UnsupportedOperationException("This method has not been implemented by the DAO");
    }

}
