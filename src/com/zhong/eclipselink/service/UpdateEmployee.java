package com.zhong.eclipselink.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.zhong.eclipselink.entity.Employee;

public class UpdateEmployee {
   public static void main( String[ ] args ) 
   {
   	EntityManagerFactory emfactory = Persistence.
   			createEntityManagerFactory( "Eclipselink_JPA" );
   	EntityManager entitymanager = emfactory.
   			createEntityManager( );
   			entitymanager.getTransaction( ).begin( );
   	Employee employee=entitymanager.
   			find(Employee.class, 1201);
   	//before update
   	System.out.println( employee );
   	employee.setSalary( 46000 );
   	entitymanager.getTransaction( ).commit( );
        //after update
   	System.out.println( employee );
   	entitymanager.close();
   	emfactory.close();
   }
}
