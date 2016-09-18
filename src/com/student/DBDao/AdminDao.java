package com.student.DBDao;

import java.util.List;

import org.bson.types.ObjectId;

import com.student.entity.AdminBean;
import com.student.entity.StudentBean;

public interface AdminDao {
	// ��ӹ���Ա
	public boolean addAdmin(AdminBean adminBean);

	// ����Ա��¼
	public AdminBean login(AdminBean adminBean);

	// ���¹���Ա
	public boolean updateAdmin(AdminBean adminBean);

	// ɾ������Ա
	public boolean deleteAdmin(ObjectId id);

	// ��ѯ���й���Ա
	public List<AdminBean> getAdmin();

	// ��ѯָ�������Ĺ���Ա���ڷ�ҳ��ʾ
	public List<AdminBean> getListByPage(int start, int size);

	// ������������Ա
	public AdminBean getAdminbyId(ObjectId id);

	// ��¼ǰ���û����ļ��
	public boolean chkuser(String tel);

	// ����������
	public int getcount();

}
