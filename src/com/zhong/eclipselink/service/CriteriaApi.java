package com.zhong.eclipselink.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.zhong.eclipselink.entity.Employee;
/**
 * 准则查询api
 * @author admin
 * 
 *  准则查询结构：
 *	EntityManager em = ...;
	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<Entity class> cq = cb.createQuery(Entity.class);
	Root<Entity> from = cq.from(Entity.class);
	cq.select(Entity);
	TypedQuery<Entity> q = em.createQuery(cq);
	List<Entity> allitems = q.getResultList();
	
	查询演示了基本的步骤来创建一个标准。
		EntityManager实例被用来创建一个CriteriaBuilder对象。
		CriteriaQuery实例是用来创建一个查询对象。这个查询对象的属性将与该查询的细节进行修改。
		CriteriaQuery.form方法被调用来设置查询根。
		CriteriaQuery.select被调用来设置结果列表类型。
		TypedQuery<T>实例是用来准备一个查询执行和指定的查询结果的类型。
		在TypedQuery<T>对象getResultList方法来执行查询。该查询返回实体的集合，结果存储在一个列表中。
 */
public class CriteriaApi {

	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager entitymanager = emfactory.createEntityManager();
		CriteriaBuilder criteriaBuilder = entitymanager.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
		Root<Employee> from = criteriaQuery.from(Employee.class);

		// select all records
		System.out.println("Select all records");
		CriteriaQuery<Object> select = criteriaQuery.select(from);
		TypedQuery<Object> typedQuery = entitymanager.createQuery(select);
		List<Object> resultlist = typedQuery.getResultList();

		for (Object o : resultlist) {
			Employee e = (Employee) o;
			System.out.println("EID : " + e.getEid() + " Ename : " + e.getEname());
		}

		// Ordering the records
		System.out.println("Select all records by follow ordering");
		CriteriaQuery<Object> select1 = criteriaQuery.select(from);
		select1.orderBy(criteriaBuilder.asc(from.get("ename")));
		TypedQuery<Object> typedQuery1 = entitymanager.createQuery(select);
		List<Object> resultlist1 = typedQuery1.getResultList();

		for (Object o : resultlist1) {
			Employee e = (Employee) o;
			System.out.println("EID : " + e.getEid() + " Ename : " + e.getEname());
		}

		entitymanager.close();
		emfactory.close();
	}
}
