package Common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class PictureUploader {

	
	
	
	private static PictureUploader instance = null;
	
	
	private PictureUploader() {
		
	}
	
	public static synchronized PictureUploader getInstanceOfPictureDownloader() {
		if(instance == null) {
			instance = new PictureUploader();
		}
		return instance;
	}
	
	public File upload(String path, String email) throws Exception {
		if(path == null || !ImageFormatValidator.getInstance().validate(path)) {
			throw new Exception("Invalid photo path!");
		}
		File uploadingPhoto = new File(path);
		File dir = new File("src\\resources\\"+email);
		dir.mkdir();
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
	
	
}
