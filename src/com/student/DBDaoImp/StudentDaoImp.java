package com.student.DBDaoImp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.student.DBDao.MongoDB;
import com.student.DBDao.StudentDao;
import com.student.entity.JqGridSearchBean;
import com.student.entity.RuleBean;
import com.student.entity.StudentBean;
import com.student.util.Contains;
import com.student.util.StringUtil;

public class StudentDaoImp implements StudentDao {
	MongoDatabase db = MongoDB.newInstance();
	MongoCollection<Document> collection = db.getCollection("student");

	/**
	 * ����û��Ƿ���� ���û������Ƿ���false �����ڷ���true
	 */
	@Override
	public boolean chkuser(StudentBean studentBean) {

		for (Document document : collection.find()) {
			if (document.getString("tel").equals(studentBean.getTel())) {
				// �û����Ѿ�����
				return false;
			}
		}
		return true;
	}

	/**
	 * ��¼����
	 */

	@Override
	public StudentBean login(StudentBean studentBean) {
		StudentBean Sessionstudent = null;
		for (Document document : collection.find()) {
			if (document.getString("tel").equals(studentBean.getTel())) {
				if (document.getString("password").equals(studentBean.getPassword())) {
					Sessionstudent = new StudentBean();
					Sessionstudent.setPassword(document.getString("password"));
					Sessionstudent.setUsername(document.getString("username"));
					Sessionstudent.setTel(document.getString("tel"));
				}
			}
		}
		return Sessionstudent;

	}

	/**
	 * ���ѧ��
	 */

	@Override
	public boolean regStudent(StudentBean studentBean) {
		if (collection.count(Filters.eq("tel", studentBean.getTel())) == 0) {
			Document document = new Document("username", studentBean.getUsername())
					.append("password", studentBean.getPassword()).append("tel", studentBean.getTel());
			try {
				collection.insertOne(document);
				return true;

			} catch (Exception e) {
				return false;
			}
		}
		return false;

	}

	/**
	 * ����ҳ������ѧ���б�
	 */

	@Override
	public List<StudentBean> getListByPage(int start, int size) {
		List<StudentBean> list = new ArrayList<>();
		StudentBean studentBean = null;
		for (Document document : collection.find().skip(start).limit(size)) {
			studentBean = new StudentBean();
			studentBean.setUsername(document.getString("username"));
			studentBean.setPassword(document.getString("password"));
			studentBean.setTel(document.getString("tel"));
			studentBean.setId(document.getObjectId("_id").toString());
			list.add(studentBean);
		}
		return list;
	}

	/**
	 * ͳ��ѧ������
	 * 
	 */

	@Override
	public int getcount() {
		return StringUtil.longToInt(collection.count());
	}

	/**
	 * ����ѧ����Ϣ
	 */

	@Override
	public boolean updateStudent(StudentBean studentBean) {
		UpdateResult result = collection.updateOne(Filters.eq("_id", new ObjectId(studentBean.getId())),
				new Document("$set", new Document("username", studentBean.getUsername())
						.append("password", studentBean.getPassword()).append("tel", studentBean.getTel())));
		if (result.getModifiedCount() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * ���ݵ绰�����ȡѧ��
	 */
	@Override
	public StudentBean getStudentByTel(String id) {
		StudentBean studentBean = null;
		ObjectId _id = new ObjectId(id);
		for (Document document : collection.find(Filters.eq("_id", _id))) {
			studentBean = new StudentBean();
			studentBean.setUsername(document.getString("username"));
			studentBean.setTel(document.getString("tel"));
			studentBean.setPassword(document.getString("password"));
			studentBean.setId(document.getObjectId("_id").toString());
		}
		return studentBean;

	}

	/**
	 * ɾ��ѧ��
	 */
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

	/**
	 * ��ȡ���е�ѧ��
	 */

	@Override
	public List<StudentBean> getAllStudent() {
		List<StudentBean> studentBeans = new ArrayList<>();
		StudentBean studentBean = null;
		for (Document document : collection.find()) {
			studentBean = new StudentBean();
			studentBean.setUsername(document.getString("username"));
			studentBean.setTel(document.getString("tel"));
			studentBean.setPassword(document.getString("password"));
			studentBean.setId(document.getObjectId("_id").toString());
			studentBeans.add(studentBean);
		}
		return studentBeans;

	}

	@Override
	public int getcountByFilter(JqGridSearchBean jqGridSearchBean) {
		int result = 0;
		for (RuleBean ruleBean : jqGridSearchBean.getRules()) {
			switch (ruleBean.getOp()) {
			case Contains.FILTER_EQ:
				result += StringUtil.longToInt(collection.count(Filters.eq(ruleBean.getField(), ruleBean.getData())));
				break;
			case Contains.FILTER_LT:
				result += StringUtil.longToInt(collection.count(Filters.lt(ruleBean.getField(), ruleBean.getData())));
				break;
			case Contains.FILTER_GT:
				result += StringUtil.longToInt(collection.count(Filters.gt(ruleBean.getField(), ruleBean.getData())));
				break;
			default:
				break;
			}
		}
		return result;
	}

	@Override
	public List<StudentBean> getListByFiter(JqGridSearchBean jqGridSearchBean) {
		int start = jqGridSearchBean.getPage() * Contains.PAGE_SIZE_1;
		List<StudentBean> list = new ArrayList<>();
		StudentBean studentBean = null;
		for (RuleBean ruleBean : jqGridSearchBean.getRules()) {
			if (ruleBean != null) {
				for (Document document : collection.find(Filters.eq(ruleBean.getField(), ruleBean.getData()))
						.skip(start).limit(Contains.PAGE_SIZE_1)) {
					studentBean = new StudentBean();
					studentBean.setUsername(document.getString("username"));
					studentBean.setPassword(document.getString("password"));
					studentBean.setTel(document.getString("tel"));
					studentBean.setId(document.getObjectId("_id").toString());
					list.add(studentBean);
				}
			}
		}
		return list;
	}

	@Override
	public Map<String, StudentBean> getMapByFiter(JqGridSearchBean jqGridSearchBean) {
		Map<String, StudentBean> map = new HashMap<>();
		int start = jqGridSearchBean.getPage() * Contains.PAGE_SIZE_1;
		StudentBean studentBean = null;
		for (RuleBean ruleBean : jqGridSearchBean.getRules()) {
			if (ruleBean != null) {
				for (Document document : collection.find(Filters.eq(ruleBean.getField(), ruleBean.getData()))
						.skip(start).limit(Contains.PAGE_SIZE_1)) {
					studentBean = new StudentBean();
					studentBean.setUsername(document.getString("username"));
					studentBean.setPassword(document.getString("password"));
					studentBean.setTel(document.getString("tel"));
					studentBean.setId(document.getObjectId("_id").toString());
					map.put(studentBean.getId(), studentBean);
				}
			}
		}

		return map;
	}
}
