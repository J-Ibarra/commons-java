package br.com.crypto.commons.connection.jdbc.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.crypto.commons.connection.jdbc.JDBCConnection;

/**
 * Generic JDCB connection factory to be extended.
 * <p>
 * @author Reginaldo Ferreira Costa Junior
 */
public abstract class JDBCAbstractConnection extends JDBCAbstract implements JDBCConnection {

	/** {@inheritDoc} */
	@Override
	public Connection getConnection() {

		try {
			if (conn != null && !conn.isClosed() && conn.getMetaData().getURL().equals(this.url)) {
				return conn;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {

			Class.forName(this.driver.getValue());
			conn = DriverManager.getConnection(this.url, this.user, this.password);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

}
