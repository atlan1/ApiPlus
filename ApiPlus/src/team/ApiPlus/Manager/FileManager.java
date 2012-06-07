package team.ApiPlus.Manager;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class FileManager {
	
	public static File getFileFromPath(String path){
		File f = new File(path);
		return f;
	}

	
	public static void copy(InputStream in, File file) throws IOException{
		OutputStream out = new FileOutputStream(file);
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		out.close();
		in.close();
	}
	
	public static boolean create(String path) throws IOException{
		return create(getFileFromPath(path));
	}
	
	public static boolean create(File f) throws IOException{
		if(!f.exists()){
			f.getParentFile().mkdirs();
			f.createNewFile();
		}else{
			return false;
		}
		return true;
	}
	
	public static boolean delete(File f){
		return f.exists()?f.delete():false;
	}
	
	public static boolean delete(String path){
		return delete(getFileFromPath(path));
	}
	
	public static void download(URL url, File file) throws IOException{
		if (!file.getParentFile().exists())
			file.getParentFile().mkdir();
		if (file.exists())
			return;
		file.createNewFile();
		final InputStream in = url.openStream();
		final OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
		final byte[] buffer = new byte[1024];
		int len;
		while ((len = in.read(buffer)) >= 0) {
			out.write(buffer, 0, len);
		}
		in.close();
		out.close();
	}
}
