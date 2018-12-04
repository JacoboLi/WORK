package cn.com.taiji;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.catalina.User;

public class Test {

	public static void main(String[] args) {

		// 1. 创建EntityManagerFactory
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Day1203-OneToOne");

		// 2. 创建EntityManager
		EntityManager entityManager = factory.createEntityManager();
		// 添加
		// add(entityManager);
		// 删除
		// delete(entityManager);
		// 修改
		// update(entityManager);
		// 查询
		select(entityManager);

		// 6. 关闭EntityManager
		entityManager.close();

		// 7. 关闭EntityManagerFactory
		factory.close();
	}

	/**
	 * OnoToOne添加信息
	 */
	public static void add(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// 4. 持久化操作
		Users users = new Users();
		users.setUsername("Jacob4");
		users.setUserage(21);

		Roles roles = new Roles();
		roles.setRolename("管理员1");

		users.setRoles(roles);
		roles.setUsers(users);

		// 添加user到数据库，相当于hibernate的save();
		entityManager.persist(users);

		// 5. 提交事务
		transaction.commit();
	}

	/**
	 * OneToOne删除信息
	 */
	public static void delete(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// 4. 持久化操作
		// 通过Id查找并删除Users表的数据
		Users users = entityManager.find(Users.class, 4);
		entityManager.remove(users);

		// 通过查找Id并删除Roles表的数据
		// Roles roles = entityManager.find(Roles.class, 6);
		// entityManager.remove(roles);

		// 5. 提交事务
		transaction.commit();
	}

	/**
	 * OneToOne修改信息
	 */
	public static void update(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		Users users = entityManager.find(Users.class, 3);
		users.setUsername("Jacob");
		users.setUserage(22);
		entityManager.merge(users);

		// 5. 提交事务
		transaction.commit();
	}

	/**
	 * OneToOne查询信息
	 */
	public static void select(EntityManager entityManager) {
		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		Users users = entityManager.find(Users.class, 3);
		System.out.println("用户信息：" + users);
		Roles roles = entityManager.find(Roles.class, 5);
		System.out.println(roles);

	}
}
