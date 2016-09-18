package com.student.DBDao;

import java.util.List;

import org.bson.types.ObjectId;

import com.student.entity.StudentBean;
import com.student.entity.TeacherBean;

public interface TeacherDao {
	public boolean chkuser(TeacherBean teacherBean);

	public boolean login(TeacherBean teacherBean);

	public boolean regTeacher(TeacherBean teacherBean);

	// ��ѯָ����������ʦ���ڷ�ҳ��ʾ
	public List<TeacherBean> getListByPage(int start, int size);

	public int getcount();

	// ����
	public boolean updateTeacher(TeacherBean teacherBean);

	// �����ֻ����������ʦ
	public TeacherBean getTeacherByTel(String _id);

	// ����_id ɾ����ʦ
	public boolean delete(String[] ids);

}
