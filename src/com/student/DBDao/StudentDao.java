package com.student.DBDao;

import java.util.List;
import java.util.Map;

import com.student.entity.JqGridSearchBean;
import com.student.entity.StudentBean;

public interface StudentDao {

	public boolean chkuser(StudentBean studentBean);

	public StudentBean login(StudentBean studentBean);

	public boolean regStudent(StudentBean studentBean);

	// ��ѯָ�������Ĺ���Ա���ڷ�ҳ��ʾ
	public List<StudentBean> getListByPage(int start, int size);

	public int getcount();

	// ��������ͳ������
	public int getcountByFilter(JqGridSearchBean jqGridSearchBean);

	// ����������ѯָ��ҳ����ѧ��
	public List<StudentBean> getListByFiter(JqGridSearchBean jqGridSearchBean);
	// ����������ѯָ��ҳ����ѧ��
	public Map<String,StudentBean> getMapByFiter(JqGridSearchBean jqGridSearchBean);

	// ���¹���Ա
	public boolean updateStudent(StudentBean studentBean);

	// �����ֻ��������ѧ��
	public StudentBean getStudentByTel(String _id);

	// ����_id ɾ��ѧ��
	public boolean delete(String[] ids);

	public List<StudentBean> getAllStudent();
}
