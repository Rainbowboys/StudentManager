package com.student.util;

import java.io.File;

import org.apache.commons.fileupload.FileItem;

/**
 * 处理表单上传文件
 * 
 * @author Rainbow 2016/09/18
 *
 */
public class UpFileUtil {

	public static boolean processFileUpload(FileItem fileItem) {
		/* 获取文件名 */
		String Filename = fileItem.getName();
		/* 获取后缀名 */
		//String Suffix = Filename.substring(Filename.lastIndexOf(".") + 1, Filename.length());
		/* 上传路径 */
		String path_up = Contains.PATH_UP;
		if (Filename.length() == 0 || Filename.equals("")) {
			return false;
		}
		/* 服务器保存路径 */
		File file = new File(path_up);
		if (!file.exists()) {
			file.mkdirs();
		}
		//需要保存的文件
		File Upfile = new File(path_up + "/" + Filename);
		try {
			fileItem.write(Upfile);
			return true;
		} catch (Exception e) {

			return false;
		}
	}
}
