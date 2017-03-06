package cn.amlove.samdyn;

import java.io.IOException;
import java.util.Map;

import com.itranswarp.compiler.JavaStringCompiler;

public class Samdyn {
	
	private static JavaStringCompiler compiler = new JavaStringCompiler();
	
	private static Map<String, byte[]> getMapBytes(String fileName, String source) throws IOException {
		return compiler.compile(fileName, source);
	}
	
	private static Class<?> loadClass(String className, Map<String, byte[]> mb) throws ClassNotFoundException, IOException {
		return compiler.loadClass(className, mb);
	}

	@SuppressWarnings("unchecked")
	public static <T> T fromSource(String fileName, String source, String className, Class<T> klass) {		
		try {
			Map<String, byte[]> mb = getMapBytes(fileName, source);
			return (T) loadClass(className, mb).newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
        	return null;
        }
	}
    
	
}
