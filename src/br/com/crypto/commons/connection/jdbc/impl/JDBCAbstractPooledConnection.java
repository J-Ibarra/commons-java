package br.com.crypto.commons.connection.jdbc.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import br.com.crypto.commons.connection.helper.Driver;
import br.com.crypto.commons.connection.jdbc.JDBCConnection;

/**
 * Generic JDCB connection factory to be extended.
 * <p>
 * @author Reginaldo Ferreira Costa Junior
 */
public abstract class JDBCAbstractPooledConnection extends JDBCAbstract implements JDBCConnection{

	/** {@inheritDoc} */
	@Override
	public Connection getConnection() {
		
		try {
			if (conn != null && !conn.isClosed()) {
				return conn;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(this.getUrl());
			config.setUsername(this.getUser());
			config.setPassword(this.getPassword());
			config.setDriverClassName(this.getDriver().getValue());
			config.addDataSourceProperty("cachePrepStmts", "true");
			config.addDataSourceProperty("prepStmtCacheSize", "250");
			config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
			
			conn = new HikariDataSource(config).getConnection();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}


}
