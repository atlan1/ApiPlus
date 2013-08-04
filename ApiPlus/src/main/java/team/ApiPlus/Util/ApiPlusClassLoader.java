package team.ApiPlus.Util;
//
//import java.io.BufferedInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//
public class ApiPlusClassLoader extends ClassLoader{
//
//	  private String dir;
//
//	  public ApiPlusClassLoader(String class_dir) {
//		  super();
//		  this.dir = class_dir;
//	  }
//
//	 @SuppressWarnings({ "unchecked", "rawtypes" })
//	 public Class findClass(String className) throws ClassNotFoundException{
//	    try {
//	      File classFile = new File(dir, className + ".class");
//	      System.out.print(className);
//	      System.out.print(classFile.getName());
//		  InputStream input = new BufferedInputStream(new FileInputStream(classFile));
//		  ByteArrayOutputStream output = new ByteArrayOutputStream();
//		  int i = 0;
//		  while((i = input.read()) >= 0) {
//		    output.write(i);
//		  }
//		  byte[] bytes = output.toByteArray();
//		  return (Class) defineClass("team.ApiPlus.Addon."+className, bytes, 0, bytes.length);
//		}catch(Exception e){
//			e.printStackTrace();
//			throw new ClassNotFoundException("Could not load " + className + ".class ");
//		}
//	  }
//	
}
