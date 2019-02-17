package Common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FileHelper {

	
	
	
	private static FileHelper instance = null;
	
	
	private FileHelper() {
		
	}
	
	public static synchronized FileHelper getInstance() {
		if(instance == null) {
			instance = new FileHelper();
		}
		return instance;
	}
	
	public File uploadPicture(String path, String email) throws Exception {
		if(path == null || !FormatValidator.getInstance().validatePicture(path)) {
			throw new Exception("Invalid photo path!");
		}
		File uploadingPhoto = new File(path);
		File dir = new File("resources\\pictures\\"+email);
		dir.mkdirs();
		File uploadedPhoto = new File(dir, uploadingPhoto.hashCode()+".jpg");
		uploadedPhoto.createNewFile();
		try (InputStream is = new BufferedInputStream(new FileInputStream(uploadingPhoto));
			OutputStream os = new BufferedOutputStream(new FileOutputStream(uploadedPhoto))) {
				int b = is.read();
				while (b != -1) {
					os.write(b);
					b = is.read();
				}
		}
		return uploadedPhoto;
	}
	
	public void createJson(Object object, String fileName) throws IOException {
		File dir = new File("resources\\json\\");
		dir.mkdir();
		try(Writer writer = new FileWriter(new File(dir, fileName+".json"))){
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(object, writer);
		}
	}
	
	
}
