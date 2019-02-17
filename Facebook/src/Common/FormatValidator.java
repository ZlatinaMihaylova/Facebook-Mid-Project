package Common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatValidator {

   private Pattern pattern;
   private Matcher matcher;
   private static FormatValidator instance = null;
   private static final String IMAGE_PATTERN = 
                "([^\\s]+(\\.(?i)(/bmp|jpg|gif|png))$)";
  
   private FormatValidator(){
      pattern = Pattern.compile(IMAGE_PATTERN);
   }
  public synchronized static FormatValidator getInstance() {
	  if(instance == null) {
		  instance = new FormatValidator();
	  }
	  return instance;
  }
   
  
   public boolean validatePicture(final String image){
  
      matcher = pattern.matcher(image);
      return matcher.matches();
  
   }
	
	   
}
	