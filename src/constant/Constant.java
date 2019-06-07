package constant;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Constant {
   // file containing properties setting general parameters of the application

   public static final String PATH_PROXY_PROPERTIES = "net/proxy.properties";

   public static final String PATH_DB_PROPERTIES = "tmp/dbBot.properties";

   // parametri query
   public static final String PURPLE_AIR = "PURPLEAIR";
   // OTHER CONSTANTS
   public final static String MISSING_INFORMATION = "-";
   public final static int PERIOD_AVG = 10;
   public final static int PERIOD_PAST_REQUEST = 1;
   public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

   public static SimpleDateFormat getTimeFormat() {
      sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
      return sdf;
   }
}
