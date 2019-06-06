package constant;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class TimeConstant {
	//Daylight saving time (DST)
    public static final int TIME_UPDATE_DST_FORWARD_CODE = 1; // da solare a legale
    public static final int TIME_UPDATE_DST_BACKWARD_CODE = 2; // inverso
    public static final int TIME_UPDATE_ALIGNMENT_CODE = 3; //se lettore aggiornato perché aveva ora sbagliata

    // TOLLERANZE
    public static final int TIME_UPDATE_DST_TOL = 30;//in minuti: tolleranza sullo scarto tra le date 
                                                   // previous_date e updated_date di un aggiornamento successivo al cambio dell'ora
    public static final int ZOOM_SHIFT_HOUR = 2;
    public static final int TIME_ALIGNMENT_TOL = 30;// minuti di tolleranza (sul disallineamento ora vera - ora lettore) per considerare il dato valido
    // CONVERSIONI
    public static final int MILLISECOND_IN_SECOND = 1000;
    public static final int SECONDS_IN_MIN = 60;
    public static final int MINS_IN_HOUR = 60;
    public static final int HOURS_IN_DAY = 24;  
    public static final int MONTHS_IN_YEAR = 12;  
    public static final int DAYS_IN_MONTH = 30;  
    public static final int DAYS_IN_WEEK = 7; 
    public static final int DEFAULT_PATTER_SEARCH_PERIOD = 180; 

    
    /*
	 * date italiane di cambio dell'ora (da solare a legale: sposta 1 h avanti) 
	 * tra il 2010 ed il 2028
	 */
 public static HashMap<Integer, GregorianCalendar> getDSTdates_forward_IT(){
	 HashMap<Integer, GregorianCalendar> dst_dates = new HashMap<>();
	 dst_dates.put(2010, new GregorianCalendar(2010,Calendar.MARCH,28,02,00));// 2010/03/28 h 2
	 dst_dates.put(2011, new GregorianCalendar(2011,Calendar.MARCH,27,02,00));// 2011/03/27 h 2
	 dst_dates.put(2012, new GregorianCalendar(2012,Calendar.MARCH,25,02,00));// 2012/03/25 h 2
	 dst_dates.put(2013, new GregorianCalendar(2013,Calendar.MARCH,31,02,00));// 2013/03/31 h 2
	 dst_dates.put(2014, new GregorianCalendar(2014,Calendar.MARCH,30,02,00));// 2014/03/30 h 2
	 dst_dates.put(2015, new GregorianCalendar(2015,Calendar.MARCH,29,02,00));// 2015/03/29 h 2
	 dst_dates.put(2016, new GregorianCalendar(2016,Calendar.MARCH,27,02,00));// 2016/03/27 h 2
	 dst_dates.put(2017, new GregorianCalendar(2017,Calendar.MARCH,26,02,00));// 2017/03/26 h 2
	 dst_dates.put(2018, new GregorianCalendar(2018,Calendar.MARCH,25,02,00));// 2018/03/25 h 2
	 dst_dates.put(2019, new GregorianCalendar(2019,Calendar.MARCH,31,02,00));// 2019/03/31 h 2
	 dst_dates.put(2020, new GregorianCalendar(2020,Calendar.MARCH,29,02,00));// 2020/03/29 h 2
	 dst_dates.put(2021, new GregorianCalendar(2021,Calendar.MARCH,28,02,00));// 2021/03/28 h 2
	 dst_dates.put(2022, new GregorianCalendar(2022,Calendar.MARCH,27,02,00));// 2022/03/27 h 2
	 dst_dates.put(2023, new GregorianCalendar(2023,Calendar.MARCH,26,02,00));// 2023/03/26 h 2
	 dst_dates.put(2024, new GregorianCalendar(2024,Calendar.MARCH,31,02,00));// 2024/03/31 h 2
	 dst_dates.put(2025, new GregorianCalendar(2025,Calendar.MARCH,30,02,00));// 2025/03/30 h 2
	 dst_dates.put(2026, new GregorianCalendar(2026,Calendar.MARCH,29,02,00));// 2026/03/29 h 2
	 dst_dates.put(2027, new GregorianCalendar(2027,Calendar.MARCH,28,02,00));// 2027/03/28 h 2
	 dst_dates.put(2028, new GregorianCalendar(2028,Calendar.MARCH,26,02,00));// 2028/03/26 h 2
	 return dst_dates;
 }
 
 
	/*
	 * date italiane di cambio dell'ora (da legale a solare: sposta 1 h indietro) 
	 * tra il 2010 ed il 2028
	 */
public static HashMap<Integer, GregorianCalendar> getDSTdates_backward_IT(){
	 HashMap<Integer, GregorianCalendar> dst_dates = new HashMap<>();
	 dst_dates.put(2010, new GregorianCalendar(2010,Calendar.OCTOBER,31,03,00));
	 dst_dates.put(2011, new GregorianCalendar(2011,Calendar.OCTOBER,30,03,00));
	 dst_dates.put(2012, new GregorianCalendar(2012,Calendar.OCTOBER,28,03,00));
	 dst_dates.put(2013, new GregorianCalendar(2013,Calendar.OCTOBER,27,03,00));
	 dst_dates.put(2014, new GregorianCalendar(2014,Calendar.OCTOBER,26,03,00));
	 dst_dates.put(2015, new GregorianCalendar(2015,Calendar.OCTOBER,25,03,00));
	 dst_dates.put(2016, new GregorianCalendar(2016,Calendar.OCTOBER,30,03,00));
	 dst_dates.put(2017, new GregorianCalendar(2017,Calendar.OCTOBER,29,03,00));
	 dst_dates.put(2018, new GregorianCalendar(2018,Calendar.OCTOBER,28,03,00));
	 dst_dates.put(2019, new GregorianCalendar(2019,Calendar.OCTOBER,27,03,00));
	 dst_dates.put(2020, new GregorianCalendar(2020,Calendar.OCTOBER,25,03,00));
	 dst_dates.put(2021, new GregorianCalendar(2021,Calendar.OCTOBER,31,03,00));
	 dst_dates.put(2022, new GregorianCalendar(2022,Calendar.OCTOBER,30,03,00));
	 dst_dates.put(2023, new GregorianCalendar(2023,Calendar.OCTOBER,29,03,00));
	 dst_dates.put(2024, new GregorianCalendar(2024,Calendar.OCTOBER,27,03,00));
	 dst_dates.put(2025, new GregorianCalendar(2025,Calendar.OCTOBER,26,03,00));
	 dst_dates.put(2026, new GregorianCalendar(2026,Calendar.OCTOBER,25,03,00));
	 dst_dates.put(2027, new GregorianCalendar(2027,Calendar.OCTOBER,31,03,00));
	 dst_dates.put(2028, new GregorianCalendar(2028,Calendar.OCTOBER,29,03,00));
	 return dst_dates;
}
	
}
