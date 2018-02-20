package com.boraji.tutorial.hibernate;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @author imssbora
 */
public class MainApp {
  public static void main(String[] args) {
    
    SessionFactory sf = buildSessionJavaConfigFactory();
    Session session = sf.getCurrentSession();
    session.beginTransaction();

    // Check database version
    String sql = "select top 1 * FROM sgp.T_VFnc001  ";

    Object result = session.createNativeQuery(sql).getSingleResult();
    System.out.println(result);

    session.getTransaction().commit();
    session.close();

    

  }
  
  
  private static SessionFactory buildSessionJavaConfigFactory() {
      try {
          Configuration configuration = new Configuration();

          //Create Properties, can be read from property files too
          Properties props = new Properties();
          props.put("hibernate.connection.driver_class", "net.sourceforge.jtds.jdbc.Driver");
          props.put("hibernate.connection.url", "jdbc:jtds:sqlserver://DBSHML/sad;appName=Organizacional");
          props.put("hibernate.connection.username", "www2");
          props.put("hibernate.connection.password", "ZKuLiGG33liSdsuRD0");
          props.put("hibernate.current_session_context_class", "thread");
          props.put("hibernate.c3p0.min_size", "5");
          props.put("hibernate.c3p0.max_size", "20");
          props.put("hibernate.c3p0.timeout", "1800");
          props.put("hibernate.c3p0.max_statements", "50");
          props.put("hibernate.cache.use_second_level_cache", "true");
          props.put("hibernate.cache.use_query_cache", "true");
          props.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
          
          

          configuration.setProperties(props);


          ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
          System.out.println("Hibernate Java Config serviceRegistry created");

          SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

          return sessionFactory;
      }
      catch (Throwable ex) {
          System.err.println("Initial SessionFactory creation failed." + ex);
          throw new ExceptionInInitializerError(ex);
      }
}
}
