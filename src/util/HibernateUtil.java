package util;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import domain.Location;
import domain.Measurement;
import domain.Measurementavg;
import domain.Parameter;
import domain.Parameter2;
import domain.Sensor;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {

			Configuration configuration = new Configuration();
//                        configuration.setProperty("hibernate.jdbc.batch_size",String.valueOf(30));
		//	Properties db = new Properties();
		//	File p = new File(Constant.PATH_DB_PROPERTIES);
			//System.out.println("DB_PROPERTIES "+p.getAbsolutePath());
	//		db.load(new InputStreamReader(new FileInputStream(p)));
//			String url="hibernate.connection.url";
//			String login="hibernate.connection.username";
//			String pwd = "hibernate.connection.password";
		//	configuration.addProperties(db);
//			configuration.setProperty(login, db.getProperty(login));
//			configuration.setProperty(pwd, db.getProperty(pwd));
                        configuration.addAnnotatedClass(Sensor.class);
                        configuration.addAnnotatedClass(Location.class);
                        configuration.addAnnotatedClass(Parameter.class);
                        configuration.addAnnotatedClass(Measurement.class);
                        configuration.addAnnotatedClass(Parameter2.class);
                        configuration.addAnnotatedClass(Measurementavg.class);
			configuration.configure("hibernate.cfg.xml");
                        
//			System.out.println(configuration.getProperty(login));
			
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			return sessionFactory;
		} catch (Throwable ex) {
			ex.printStackTrace();
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
