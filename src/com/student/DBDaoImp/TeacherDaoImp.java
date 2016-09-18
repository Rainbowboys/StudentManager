package com.student.DBDaoImp;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.net.Nio2Endpoint.Nio2SocketWrapper;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.student.DBDao.MongoDB;
import com.student.DBDao.TeacherDao;
import com.student.entity.StudentBean;
import com.student.entity.TeacherBean;
import com.student.util.StringUtil;

public class TeacherDaoImp implements TeacherDao {
	MongoDatabase db = MongoDB.newInstance();
	MongoCollection<Document> collection = db.getCollection("teacher");

	@Override
	public boolean chkuser(TeacherBean teacherBean) {
		for (Document document : collection.find()) {
			if (document.getString("tel").equals(teacherBean.getTelphone())) {
				// 用户名已经存在
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean login(TeacherBean teacherBean) {
		for (Document document : collection.find()) {
			if (document.getString("tel").equals(teacherBean.getTelphone())) {
				if (document.getString("password").equals(teacherBean.getPassword())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean regTeacher(TeacherBean teacherBean) {
		Document document = new Document("username", teacherBean.getName())
				.append("password", teacherBean.getPassword()).append("tel", teacherBean.getTelphone());
		try {
			collection.insertOne(document);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<TeacherBean> getListByPage(int start, int size) {
		List<TeacherBean> list = new ArrayList<>();
		TeacherBean teacherBean = null;
		for (Document document : collection.find().skip(start).limit(size)) {
			teacherBean = new TeacherBean();
			teacherBean.setName(document.getString("username"));
			teacherBean.setPassword(document.getString("password"));
			teacherBean.setTelphone(document.getString("tel"));
			teacherBean.setId(document.getObjectId("_id").toString());
			list.add(teacherBean);
		}
		return list;
	}

	@Override
	public int getcount() {
		// TODO Auto-generated method stub
		return StringUtil.longToInt(collection.count());
	}

	@Override
	public boolean updateTeacher(TeacherBean teacherBean) {
		UpdateResult result = collection
				.updateOne(Filters.eq("_id", new ObjectId(teacherBean.getId())),
						new Document("$set",
								new Document("username", teacherBean.getName())
										.append("password", teacherBean.getPassword()).append("tel",
												teacherBean.getTelphone())));
		if (result.getModifiedCount() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public TeacherBean getTeacherByTel(String id) {
		TeacherBean teacherBean = null;
		ObjectId _id = new ObjectId(id);
		for (Document document : collection.find(Filters.eq("_id", _id))) {
			teacherBean = new TeacherBean();
			teacherBean.setName(document.getString("username"));
			teacherBean.setTelphone(document.getString("tel"));
			teacherBean.setPassword(document.getString("password"));
			teacherBean.setId(document.getObjectId("_id").toString());
		}
		return teacherBean;
	}

	@Override
	public boolean delete(String[] ids) {
		int index = 0;
		for (String string : ids) {
			ObjectId _id = new ObjectId(string);
			DeleteResult result = collection.deleteOne(Filters.eq("_id", _id));
			if (result.getDeletedCount() > 0) {
				index++;
			}
		}
		if (index == ids.length) {
			return true;
		} else {
			return false;
		}
	}
}
