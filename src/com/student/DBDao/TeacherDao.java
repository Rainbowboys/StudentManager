package com.student.DBDao;

import java.util.List;

import org.bson.types.ObjectId;

import com.student.entity.StudentBean;
import com.student.entity.TeacherBean;

public interface TeacherDao {
	public boolean chkuser(TeacherBean teacherBean);

	public boolean login(TeacherBean teacherBean);

	public boolean regTeacher(TeacherBean teacherBean);

	// 查询指定数量的老师用于分页显示
	public List<TeacherBean> getListByPage(int start, int size);

	public int getcount();

	// 更新
	public boolean updateTeacher(TeacherBean teacherBean);

	// 根据手机号码查找老师
	public TeacherBean getTeacherByTel(String _id);

	// 根据_id 删除老师
	public boolean delete(String[] ids);

}
