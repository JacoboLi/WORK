package cn.com.taiji;

import java.awt.Robot;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.catalina.User;

/**
 * ManyToMany 出现的问题： 1.更新有时更新不成功，或者出现空指针异常 2.查询出现Transaction already active
 * 
 * @author Jacobo
 *
 */
public class Test {

	public static void main(String[] args) {

		// 1. 创建EntityManagerFactory
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Day1203-ManyToMany");

		// 2. 创建EntityManager
		EntityManager entityManager = factory.createEntityManager();

		// 3.开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// 4.持久性操作

		// 添加
		// addStudent(entityManager);
		// addCourse(entityManager);
		// 删除
		// delStudent(entityManager);
		// delCourse(entityManager);
		// 修改
		updateUser(entityManager);
		// 查询
		// select(entityManager);

		// 5. 提交事务
		transaction.commit();

		// 6. 关闭EntityManager
		entityManager.close();

		// 7. 关闭EntityManagerFactory
		factory.close();
	}

	/**
	 * ManyToMany添加信息
	 */
	// 添加student表信息
	public static void addStudent(EntityManager entityManager) {
		List<Course> courseList = entityManager.createQuery("SELECT a FROM Course a").getResultList();
		// 4.持久化操作
		Student student = new Student();
		student.setsName("Jacob1");
		student.setCourseList(courseList);
		// 添加user到数据库，相当于hibernate的save();
		entityManager.persist(student);
	}

	// 添加course表信息
	public static void addCourse(EntityManager entityManager) {
		// 4.持久化操作
		Course course = new Course();
		course.setcName("Java");

		// 添加user到数据库，相当于hibernate的save();
		entityManager.persist(course);
	}

	// 通过id删除student表信息
	public static void delStudent(EntityManager entityManager) {

		Student student = entityManager.find(Student.class, 1);
		entityManager.remove(student);
	}

	// 通过ID删除course表信息
	public static void delCourse(EntityManager entityManager) {

		Course course = entityManager.find(Course.class, 1);
		entityManager.remove(course);

	}

	public static void updateUser(EntityManager entityManager) {

		// 4.持久化操作
		Student student = entityManager.find(Student.class, 1);
		student.setsName("ggg");

		entityManager.merge(student);

	}

	public static Student select(EntityManager entityManager) {

		return entityManager.createQuery("SELECT s FROM Student s WHERE id=:id", Student.class).setParameter("id", 2)
				.getSingleResult();
	}
}
