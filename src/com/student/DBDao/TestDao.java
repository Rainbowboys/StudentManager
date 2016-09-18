package com.student.DBDao;

import java.util.List;

import com.student.entity.StudentBean;
import com.student.entity.TestBean;

public interface TestDao {
	public boolean addTest(TestBean testBean);

	public List<TestBean> getAllTest();

	public int getcount();

	public List<TestBean> getListByPage(int start, int size);

}
