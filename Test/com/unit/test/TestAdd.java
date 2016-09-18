package com.unit.test;

import java.util.List;

import javax.jws.Oneway;

import org.bson.types.ObjectId;
import org.junit.Test;

import com.student.DBDao.AdminDao;
import com.student.DBDao.StudentDao;
import com.student.DBDao.TeacherDao;
import com.student.DBDao.TestDao;
import com.student.DBDaoImp.AdminDBDaoImp;
import com.student.DBDaoImp.StudentDaoImp;
import com.student.DBDaoImp.TeacherDaoImp;
import com.student.DBDaoImp.TestDaoImp;
import com.student.entity.AdminBean;
import com.student.entity.StudentBean;
import com.student.entity.TeacherBean;
import com.student.entity.TestBean;
import com.student.util.RandomData;

public class TestAdd {
	AdminDao adminDao = new AdminDBDaoImp();
	StudentDao studentdao = new StudentDaoImp();
	TeacherDao teacherDao = new TeacherDaoImp();
	TestDao testDao = new TestDaoImp();

	@Test
	public void addAdmin() {
		AdminBean adminBean = new AdminBean();
		String[] names = RandomData.getRandom(100);
		String[] password = RandomData.getRandomPassword(100);
		String[] tel = RandomData.getRandomTel(100);
		for (int i = 0; i < 100; i++) {
			adminBean.setName(names[i]);
			adminBean.setTelphone(tel[i]);
			adminBean.setPassword(password[i]);
			adminBean.setIsSuper(0);
			adminDao.addAdmin(adminBean);
		}
	}

	@Test
	public void getBypage() {
		List<AdminBean> adminbeans = adminDao.getListByPage(9, 5);
		for (AdminBean adminBean : adminbeans) {
			System.out.println(adminBean.getId());
		}
	}

	@Test
	public void addStudent() {
		StudentBean studentBean = new StudentBean();
		String[] names = RandomData.getRandom(100);
		String[] password = RandomData.getRandomPassword(100);
		String[] tel = RandomData.getRandomTel(100);
		for (int i = 0; i < 100; i++) {
			studentBean.setUsername(names[i]);
			studentBean.setTel(tel[i]);
			studentBean.setPassword(password[i]);
			studentdao.regStudent(studentBean);
		}
	}

	@Test
	public void addTeacher() {
		TeacherBean teacherBean = new TeacherBean();
		String[] names = { "李英", "张云峰", "贾正华", "私情巴啦", "赵飞", "孙红燕", "金大斌", "盛源君", "孙亚男", "科科" };
		teacherBean.setPassword("123456");
		for (int i = 0; i < 10; i++) {
			teacherBean.setName(names[i]);
			teacherBean.setTelphone("1563269507" + i);
			teacherDao.regTeacher(teacherBean);
		}
	}

	@Test
	public void addTest() {
		TestBean testBean = null;
		String[] names = { "Desktop Computer", "Laptop", "贾正华", "私情巴啦", "赵飞", "孙红燕", "金大斌", "盛源君", "孙亚男", "科科", "孙亚男" };
		String[] notes = { "note1", "note2", "note3", "note4", "note5", "note6", "note7", "note8", "note9", "note10",
				"note11" };
		String[] stocks = { "No", "No", "No", "No", "No", "No", "Yes", "Yes", "Yes", "Yes", "Yes" };
		String[] sdate = { "2007-12-03", "2007-12-03", "2007-12-03", "2007-12-03", "2007-12-03", "2007-12-03",
				"2007-12-03", "2007-12-03", "2007-12-03", "2007-12-03", "2007-12-03" };
		String[] ships = { "ARAMEX" };
		for (int i = 0; i < 11; i++) {
			testBean = new TestBean();
			testBean.setName(names[i]);
			testBean.setId(i + 1);
			testBean.setNote(notes[i]);
			testBean.setSdate(sdate[i]);
			testBean.setStock(stocks[i]);
			testBean.setShip(ships[0]);
			testDao.addTest(testBean);

		}
	}

	// 更新学生
	@Test
	public void updateStudent() {
		StudentBean studentBean = new StudentBean("小张", "123456", "15059371447");
		studentBean.setId("57d905ddfc7d9f1374afc683");
		studentdao.updateStudent(studentBean);
	}

}
