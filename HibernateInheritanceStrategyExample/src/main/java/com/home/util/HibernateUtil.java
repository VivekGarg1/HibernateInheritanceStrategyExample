package com.home.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

	private static StandardServiceRegistry standardServiceRegistry;
	private static SessionFactory sessionFactory;
	
	static {
		try {
			if(sessionFactory == null) {
				//Creation for default hibernate.cfg.xml configuration
				standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
				MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
				Metadata metadata=metadataSources.getMetadataBuilder().build();
				sessionFactory=metadata.getSessionFactoryBuilder().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(standardServiceRegistry != null){
				StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
			}
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static Date getDoj(String doj) throws ParseException {
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
		return format.parse(doj);
	}
}
