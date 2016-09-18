package com.student.DBDaoImp;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.student.DBDao.AdminDao;
import com.student.DBDao.MongoDB;
import com.student.entity.AdminBean;
import com.student.util.StringUtil;

public class AdminDBDaoImp implements AdminDao {
	MongoDatabase db = MongoDB.newInstance();
	MongoCollection<Document> collection = db.getCollection("admin");

	@Override
	public boolean addAdmin(AdminBean adminBean) {
		Document document = new Document("name", adminBean.getName()).append("password", adminBean.getPassword())
				.append("tel", adminBean.getTelphone()).append("isSuper", adminBean.getIsSuper());
		try {
			collection.insertOne(document);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateAdmin(AdminBean adminBean) {

		UpdateResult result = collection
				.updateOne(Filters.eq("_id", adminBean.getId()),
						new Document("$set",
								new Document("name", adminBean.getName()).append("password", adminBean.getPassword())
										.append("tel", adminBean.getTelphone()).append("isSuper",
												adminBean.getIsSuper())));
		if (result.getModifiedCount() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 更具主键删除 非 超级用户
	 */

	@Override
	public boolean deleteAdmin(ObjectId id) {
		DeleteResult result = collection.deleteOne(Filters.and(Filters.eq("_id", id), Filters.eq("isSuper", 0)));
		if (result.getDeletedCount() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 查询所有的 Admin
	 */

	@SuppressWarnings("null")
	@Override
	public List<AdminBean> getAdmin() {
		List<AdminBean> list = new ArrayList<>();
		AdminBean adminBean = null;
		for (Document document : collection.find()) {
			adminBean = new AdminBean();
			adminBean.setName(document.getString("name"));
			adminBean.setPassword(document.getString("password"));
			adminBean.setTelphone(document.getString("tel"));
			adminBean.setIsSuper(document.getInteger("isSuper"));
			list.add(adminBean);
		}
		return list;
	}

	@Override
	public List<AdminBean> getListByPage(int start, int size) {
		List<AdminBean> list = new ArrayList<>();
		AdminBean adminBean = null;
		for (Document document : collection.find().skip(start).limit(size)) {
			adminBean = new AdminBean();
			adminBean.setName(document.getString("name"));
			adminBean.setPassword(document.getString("password"));
			adminBean.setTelphone(document.getString("tel"));
			adminBean.setIsSuper(document.getInteger("isSuper"));
			adminBean.setId(document.getObjectId("_id"));
			list.add(adminBean);
		}
		return list;
	}

	/**
	 * 根据具体的主键查找Admin
	 */

	@Override
	public AdminBean getAdminbyId(ObjectId id) {
		AdminBean adminBean = null;
		for (Document document : collection.find(Filters.eq("_id", id))) {
			adminBean = new AdminBean();
			adminBean.setName(document.getString("name"));
			adminBean.setId(document.getObjectId("_id"));
			adminBean.setPassword(document.getString("password"));
			adminBean.setTelphone(document.getString("tel"));
			adminBean.setIsSuper(document.getInteger("isSuper"));
		}
		return adminBean;
	}

	@Override
	public AdminBean login(AdminBean adminBean) {

		AdminBean sessionBean = null;

		for (Document document : collection.find()) {
			if (document.getString("tel").equals(adminBean.getTelphone())) {
				if (document.getString("password").equals(adminBean.getPassword())) {
					sessionBean = new AdminBean();
					sessionBean.setIsSuper(document.getInteger("isSuper"));
					sessionBean.setName(document.getString("name"));
					sessionBean.setPassword(document.getString("password"));
					sessionBean.setTelphone(document.getString("tel"));
					return sessionBean;
				}
			}
		}
		return sessionBean;
	}

	@Override
	public boolean chkuser(String tel) {
		for (Document document : collection.find()) {
			System.out.println(document.getString("username"));
			if (document.getString("tel").equals(tel)) {
				// 用户名已经存在
				return false;
			}
		}
		return true;
	}

	/**
	 * 计算总数量
	 */

	@Override
	public int getcount() {

		return StringUtil.longToInt(collection.count());
	}

}
