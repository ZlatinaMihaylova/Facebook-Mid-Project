package Common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageFormatValidator {

   private Pattern pattern;
   private Matcher matcher;
   private static ImageFormatValidator instance = null;
   private static final String IMAGE_PATTERN = 
                "([^\\s]+(\\.(?i)(/bmp|jpg|gif|png))$)";
  
   private ImageFormatValidator(){
      pattern = Pattern.compile(IMAGE_PATTERN);
   }
  public synchronized static ImageFormatValidator getInstance() {
	  if(instance == null) {
		  instance = new ImageFormatValidator();
	  }
	  return instance;
  }
   
  
   public boolean validate(final String image){
  
      matcher = pattern.matcher(image);
      return matcher.matches();
  
   }
	
	   
}
	