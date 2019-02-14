package Common;

import java.io.File;
import java.time.LocalDateTime;

public class Photo {

	private File file;
	private LocalDateTime time;
	
	
	public Photo(File file) throws Exception {
		this.setFile(file);
		this.time = LocalDateTime.now();
	}
	
	
	private File getFile() {
		return file;
	}
	
	private LocalDateTime getTime() {
		return time;
	}
	private void setFile(File file) throws Exception {
		if(file == null) {
			throw new Exception("Invalid directory of photo!");
		}
		this.file = file;
	}
	
	
	
	
	
}
