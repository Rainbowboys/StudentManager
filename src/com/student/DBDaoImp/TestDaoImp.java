package com.student.DBDaoImp;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.student.DBDao.MongoDB;
import com.student.DBDao.TestDao;
import com.student.entity.StudentBean;
import com.student.entity.TestBean;
import com.student.util.StringUtil;

public class TestDaoImp implements TestDao {
	MongoDatabase db = MongoDB.newInstance();
	MongoCollection<Document> collection = db.getCollection("test");

	@Override
	public boolean addTest(TestBean testBean) {
		Document document = new Document("id", testBean.getId()).append("name", testBean.getName())
				.append("note", testBean.getNote()).append("stock", testBean.getStock())
				.append("skip", testBean.getShip()).append("sdate", testBean.getSdate());
		try {
			collection.insertOne(document);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<TestBean> getAllTest() {

		List<TestBean> tests = new ArrayList<>();
		TestBean testBean = null;
		for (Document document : collection.find()) {
			testBean = new TestBean();
			testBean.setName(document.getString("name"));
			testBean.setSdate(document.getString("sdate"));
			testBean.setShip(document.getString("ship"));
			testBean.setStock(document.getString("stock"));
			testBean.setNote(document.getString("note"));
			testBean.setId(document.getInteger("id"));
			tests.add(testBean);
		}
		return tests;
	}

	/**
	 * 统计学生个数
	 * 
	 */

	@Override
	public int getcount() {
		return StringUtil.longToInt(collection.count());
	}

	/**
	 * 根据页数返回学生列表
	 */

	@Override
	public List<TestBean> getListByPage(int start, int size) {
		List<TestBean> list = new ArrayList<>();
		TestBean testBean = null;
		for (Document document : collection.find().skip(start).limit(size)) {
			testBean = new TestBean();
			testBean.setName(document.getString("name"));
			testBean.setSdate(document.getString("sdate"));
			testBean.setShip(document.getString("ship"));
			testBean.setStock(document.getString("stock"));
			testBean.setNote(document.getString("note"));
			testBean.setId(document.getInteger("id"));
			list.add(testBean);
		}
		return list;
	}
}
