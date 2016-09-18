package com.student.DBDao;

import java.util.List;

import org.bson.types.ObjectId;

import com.student.entity.AdminBean;
import com.student.entity.StudentBean;

public interface AdminDao {
	// 添加管理员
	public boolean addAdmin(AdminBean adminBean);

	// 管理员登录
	public AdminBean login(AdminBean adminBean);

	// 更新管理员
	public boolean updateAdmin(AdminBean adminBean);

	// 删除管理员
	public boolean deleteAdmin(ObjectId id);

	// 查询所有管理员
	public List<AdminBean> getAdmin();

	// 查询指定数量的管理员用于分页显示
	public List<AdminBean> getListByPage(int start, int size);

	// 根据主键管理员
	public AdminBean getAdminbyId(ObjectId id);

	// 登录前对用户名的检测
	public boolean chkuser(String tel);

	// 计算总数量
	public int getcount();

}
