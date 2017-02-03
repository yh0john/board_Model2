package jdbc;

import java.io.IOException;
import java.io.StringReader;
import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInitListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sec) {
		
		//web.xml에 저장되어 있는 poolConfig parameter 가져오기
		String poolConfig = sec.getServletContext().getInitParameter("poolConfig");
		
		//초기화 파라미터 설정값을 Properties 객체에 프로퍼티로 등록한다
		Properties prop = new Properties();
		
		try{
			prop.load(new StringReader(poolConfig));
		}catch(IOException e){
			throw new RuntimeException(e);
		}
		loadJDBCDriver(prop);
		initConnectionPool(prop);
	}
	

	
	private void loadJDBCDriver(Properties prop){
		
		//JDBC driverClass를 불러온다.
		String driverClass = prop.getProperty("jdbcDriver");
		
		try{
			Class.forName(driverClass);
		}catch(ClassNotFoundException ex){
			throw new RuntimeException("fail to load JDBC Driver",ex);
		}
	}
	
	private void initConnectionPool(Properties prop){
		//커넥션 풀 초기화를 위한  파라미터 가져오기
		String jdbcUrl = prop.getProperty("jdbcUrl");
		String username= prop.getProperty("dbUser");
		String pw = prop.getProperty("dbPass");
		
		try{
			//커넥션 풀이 새로운 커넥션을 생성 할때 필요한 ConnectionFactory 생성
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcUrl,username,pw);
			
			//커넥션 풀에 커넥션을 저장 하기 위한 PoolableConnectonFacctory 생성 - 커넥션 풀 관리 기능 제공
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory,null);
			
			//커넥션 유효성 검사
			String validationQuery = prop.getProperty("validationQuery");
			if(validationQuery !=null && !validationQuery.isEmpty()){
				poolableConnFactory.setValidationQuery(validationQuery);
			}
			
			
			//커넥션 풀 설정 정보 생성 - GenericObjctPoolConfig 클래스 사용
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L*60L*5L);
			
			//최소 유휴 커넥션 설정
			poolConfig.setTestWhileIdle(true);
			int minIdle = getInitParameter(prop,"minIdle",5);
			poolConfig.setMinIdle(minIdle);
			
			//최대 크기 설정
			int maxTotal = getInitParameter(prop,"maxTotal",50);
			poolConfig.setMaxTotal(maxTotal);
			
			//커넥션 풀 생성  - GenericObjectPool 클래스 사용. 커넥션팩토리와,설정정보를 파라미터로 넘김
			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnFactory,poolConfig);
			poolableConnFactory.setPool(connectionPool);
			
			//커넥션 풀을 제공하는 JDBC 드라이버 등록
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			
			//커넥션풀 드라이버에 미리 생성한 커넥션 풀을 등록
			String poolName = prop.getProperty("poolName");
			driver.registerPool(poolName, connectionPool);
			
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
	}
	
	//컨텍스트 초기화 리스너 파라미터가 null일 경우 처리하는 method
	private int getInitParameter(Properties prop,String propName,int defaultValue){
		String value = prop.getProperty(propName);
		if(value == null) return defaultValue;
		return Integer.parseInt(value);
	}
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}
	


}
