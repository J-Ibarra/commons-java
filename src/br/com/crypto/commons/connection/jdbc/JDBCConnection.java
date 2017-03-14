package br.com.crypto.commons.connection.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import br.com.crypto.commons.connection.helper.Driver;

/**
 * Generic JDCB connection interface.
 * <p>
 * @author Reginaldo Ferreira Costa Junior
 */
public interface JDBCConnection {

	/**
	 * Returns a {@code java.sql.Connection} for the specified parameters.
	 * <p>
	 * <b>Note:</b> Requires that the methods below be changed before, to specify the parameters.
	 * <p>
	 * {@code getUrl()}
	 * <p>
	 * {@code getUser()}
	 * <p>
	 * {@code getPassword()}
	 * <p>
	 * {@code getDriver()}
	 * <p>
	 *
	 * @return      the {@code java.sql.Connection} for the specified parameters
	 * @see         {@link java.sql.Connection}
	 */
	Connection getConnection();
	/**
	 * Returns a {@code java.util.List<T>} result for the query.
	 * <p>
	 * @param 		the query.
	 * @param 		Class for the java.util.List type.
	 * @param 		Paramters of the query.
	 * @return      {@code java.util.List<T>}
	 */
	<T> List<T> findList(String query, Class<T> aClass, Object... parameters);
	/**
	 * Returns a {@code Generic class object <T>}.
	 * <p>
	 * @param 		the query.
	 * @param 		Class for the java.util.List type.
	 * @param 		Paramters of the query.
	 * @return      {@code Generic class object <T>}
	 */
	<T> T findObject(String query, Class<T> aClass, Object... parameters);
	/**
	 * Returns a {@code java.util.List<Object[]>} result for the query.
	 * <p>
	 * @param 		the query.
	 * @param 		Paramters of the query.
	 * @return      {@code java.util.List<Object[]>}
	 */
	List<Object[]> findObjectArrayList(String query, Object... parameters);
	/**
	 * Returns a {@code Object array}.
	 * <p>
	 * @param 		the query.
	 * @param 		Paramters of the query.
	 * @return      {@code Object[]}
	 */
	Object[] findObjectArray(String query, Object... parameters);
	/**
	 * Returns a {@code java.sql.ResultSet} for the query.
	 * <p>
	 * @param 		the query.
	 * @return      {@code java.sql.ResultSet}
	 * @see			{@link java.sql.ResultSet}
	 */
	ResultSet findResultSet(String query);
	/**
	 * Execute the update query;
	 * <p>
	 * @param 		the query.
	 * @param 		Paramters of the query.
	 * @return      the quantity of rows affected.
	 */
	int update(String query, Object... parameters);
	/**
	 * Execute the insert query;
	 * <p>
	 * @param 		the query.
	 * @param 		Paramters of the query.
	 * @return      the inserted id.
	 */
	Long insert(String query, Object... parameters);
	/**
	 * Execute the delete query;
	 * <p>
	 * @param 		the query.
	 * @param 		Paramters of the query.
	 * @return      the quantity of rows affected.
	 */
	int delete(String query, Object... parameters);
	/**
	 * Specify the url parameter on the extended configuration class;
	 * <p>
	 * 
	 * @return      URL.
	 */
	String getUrl();
	/**
	 * Specify the user parameter on the extended configuration class;
	 * <p>
	 * 
	 * @return      user.
	 */
	String getUser();
	/**
	 * Specify the password parameter on the extended configuration class;
	 * <p>
	 * 
	 * @return      password.
	 */
	String getPassword();
	/**
	 * Specify the driver parameter on the extended configuration class;
	 * <p>
	 * 
	 * @return      driver.
	 * @see			{@link br.com.crypto.commons.connection.helper.Driver}
	 */
	Driver getDriver();
	/**
	 * Close connection.
	 * <p>
	 * 
	 * @see         {@link java.sql.Connection}
	 */
	void close();

}
