package com.student.DBDao;

import java.util.List;
import java.util.Map;

import com.student.entity.JqGridSearchBean;
import com.student.entity.StudentBean;

public interface StudentDao {

	public boolean chkuser(StudentBean studentBean);

	public StudentBean login(StudentBean studentBean);

	public boolean regStudent(StudentBean studentBean);

	// 查询指定数量的管理员用于分页显示
	public List<StudentBean> getListByPage(int start, int size);

	public int getcount();

	// 根据条件统计数量
	public int getcountByFilter(JqGridSearchBean jqGridSearchBean);

	// 根据条件查询指定页数的学生
	public List<StudentBean> getListByFiter(JqGridSearchBean jqGridSearchBean);
	// 根据条件查询指定页数的学生
	public Map<String,StudentBean> getMapByFiter(JqGridSearchBean jqGridSearchBean);

	// 更新管理员
	public boolean updateStudent(StudentBean studentBean);

	// 根据手机号码查找学生
	public StudentBean getStudentByTel(String _id);

	// 根据_id 删除学生
	public boolean delete(String[] ids);

	public List<StudentBean> getAllStudent();
}
