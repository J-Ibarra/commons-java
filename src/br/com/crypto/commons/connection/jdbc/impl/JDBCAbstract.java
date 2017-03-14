package br.com.crypto.commons.connection.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import br.com.crypto.commons.connection.helper.Driver;
import br.com.crypto.commons.connection.jdbc.JDBCConnection;

/**
 * Generic JDCB connection factory to be extended.
 * <p>
 * @author Reginaldo Ferreira Costa Junior
 */
abstract class JDBCAbstract implements JDBCConnection {

	protected static Connection conn;
	String url = null;
	Driver driver = null;
	String user = null;
	String password = null;

	public JDBCAbstract() {
		this.url = this.getUrl();
		this.driver = this.getDriver();
		this.user = this.getUser();
		this.password = this.getPassword();
	}

	/** {@inheritDoc} */
	@Override
	public List<Object[]> findObjectArrayList(String query, Object... parameters) {

		ResultSetHandler<List<Object[]>> handler = new ResultSetHandler<List<Object[]>>() {
			public List<Object[]> handle(ResultSet rs) throws SQLException {

				List<Object[]> objectArrayList = new ArrayList<>();

				int cols = rs.getMetaData().getColumnCount();

				while (rs.next()) {

					Object[] objectArray = new Object[cols];

					for (int i = 0; i < cols; i++) {
						objectArray[i] = rs.getObject(i + 1);
					}

					objectArrayList.add(objectArray);

				}

				return objectArrayList;

			}

		};

		QueryRunner run = new QueryRunner();

		List<Object[]> list = null;
		try {
			if(parameters!= null){
				list = run.query(this.getConnection(), query, handler, parameters);
			}else{
				list = run.query(this.getConnection(), query, handler);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

	/** {@inheritDoc} */
	@Override
	public Object[] findObjectArray(String query, Object... parameters) {

		ResultSetHandler<Object[]> handler = new ResultSetHandler<Object[]>() {
			public Object[] handle(ResultSet rs) throws SQLException {

				ResultSetMetaData meta = rs.getMetaData();
				int cols = meta.getColumnCount();
				Object[] result = new Object[cols];

				for (int i = 0; i < cols; i++) {
					result[i] = rs.getObject(i + 1);
				}

				return result;

			}

		};

		QueryRunner run = new QueryRunner();

		Object[] objectArray = null;
		try {
			if(parameters!= null){
				objectArray = run.query(this.getConnection(), query, handler, parameters);
			}else{
				objectArray = run.query(this.getConnection(), query, handler);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return objectArray;

	}

	/** {@inheritDoc} */
	@Override
	public <T> T findObject(String query, Class<T> aClass, Object... parameters) {

		QueryRunner run = new QueryRunner();

		ResultSetHandler<T> handler = new BeanHandler<T>(aClass);

		T object = null;
		try {
			if(parameters!= null){
				object = run.query(this.getConnection(), query, handler, parameters);
			}else{
				object = run.query(this.getConnection(), query, handler);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return object;

	}

	/** {@inheritDoc} */
	@Override
	public <T> List<T> findList(String query, Class<T> aClass, Object... parameters) {

		QueryRunner run = new QueryRunner();

		ResultSetHandler<List<T>> handler = new BeanListHandler<T>(aClass);

		List<T> list = null;
		try {
			if(parameters!= null){
				list = run.query(this.getConnection(), query, handler, parameters);
			}else{
				list = run.query(this.getConnection(), query, handler);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

	/** {@inheritDoc} */
	@Override
	public ResultSet findResultSet(String query) {

		try {
			PreparedStatement prepareStatement = this.getConnection().prepareStatement(query);
			return prepareStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}
	
	/** {@inheritDoc} */
	@Override
	public Long insert(String query, Object... parameters) {
		
		QueryRunner queryRunner = new QueryRunner();
		
		Long id = null;
		try {
			if(parameters!= null){
				id = queryRunner.insert(this.getConnection(), query, new ScalarHandler<Long>(), parameters);
			}else{
				id = queryRunner.insert(this.getConnection(), query, new ScalarHandler<Long>());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
		
	}
	
	/** {@inheritDoc} */
	@Override
	public int delete(String query, Object... parameters) {
		return update(query, parameters);
	}
	
	/** {@inheritDoc} */
	@Override
	public int update(String query, Object... parameters) {
		
		QueryRunner queryRunner = new QueryRunner();
		
		int inserts = 0;
		try {
			if(parameters!= null){
				inserts = queryRunner.update(this.getConnection(), query, parameters);
			}else{
				inserts = queryRunner.update(this.getConnection(), query);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return inserts;
		
	}
	
	/** {@inheritDoc} */
	@Override
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
