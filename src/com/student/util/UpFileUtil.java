package com.student.util;

import java.io.File;

import org.apache.commons.fileupload.FileItem;

/**
 * ������ϴ��ļ�
 * 
 * @author Rainbow 2016/09/18
 *
 */
public class UpFileUtil {

	public static boolean processFileUpload(FileItem fileItem) {
		/* ��ȡ�ļ��� */
		String Filename = fileItem.getName();
		/* ��ȡ��׺�� */
		//String Suffix = Filename.substring(Filename.lastIndexOf(".") + 1, Filename.length());
		/* �ϴ�·�� */
		String path_up = Contains.PATH_UP;
		if (Filename.length() == 0 || Filename.equals("")) {
			return false;
		}
		/* ����������·�� */
		File file = new File(path_up);
		if (!file.exists()) {
			file.mkdirs();
		}
		//��Ҫ������ļ�
		File Upfile = new File(path_up + "/" + Filename);
		try {
			fileItem.write(Upfile);
			return true;
		} catch (Exception e) {

			return false;
		}
	}
}
