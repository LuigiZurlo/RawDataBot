package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import constant.Constant;
import constant.TimeConstant;
import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

public class DateManager {

   /**
    * Aggiunge ad una data un numero di minuti fissato
    *
    * @param d
    * @param min_number: minuti da aggiungere
    * @return
    */
   public static Date addMin(Date d, int min_number) {
      GregorianCalendar d_gc = new GregorianCalendar();
      d_gc.setTime(d);
      d_gc.add(Calendar.MINUTE, min_number);
      Date updated_date = d_gc.getTime();
      // System.out.println("DATA da modificare:"+d.toString()+ "nuova DATA:
      // "+updated_date.toString()+" minuti aggiunti: "+min_number);
      return updated_date;
   }

   /**
    * Aggiunge ad una data un numero di ore fissato
    *
    * @param d
    * @param hour_number: ore da aggiungere
    * @return
    */
   public static Date addHour(Date d, int hour_number) {
      GregorianCalendar d_gc = new GregorianCalendar();
      d_gc.setTime(d);
      d_gc.add(Calendar.HOUR, hour_number);
      Date updated_date = d_gc.getTime();
      // System.out.println("DATA da modificare:"+d.toString()+ "nuova DATA:
      // "+updated_date.toString()+" minuti aggiunti: "+min_number);
      return updated_date;
   }

   /**
    * Aggiunge ad una data un numero di minuti fissato
    *
    * @param d
    * @param min_number: minuti da aggiungere
    * @return
    */
   public static Date addHourMin(Date d, int hour_number, int min_number) {
      Date updated_date = d;
      updated_date = addHour(updated_date, hour_number);
      updated_date = addMin(updated_date, min_number);
      // System.out.println("DATA da modificare:"+d.toString()+ "nuova DATA:
      // "+updated_date.toString()+" minuti aggiunti: "+min_number);
      return updated_date;
   }

   /**
    * Aggiunge ad una data un numero di minuti fissato
    *
    * @param d
    * @param min_number: minuti da aggiungere
    * @return
    */
   public static boolean compareDatesWithoutTime(Date d1, Date d2) {
      Calendar c1 = new GregorianCalendar();
      c1.setTime(d1);
      Calendar c2 = new GregorianCalendar();
      c2.setTime(d2);
      d1 = removeTime(d1);
      d2 = removeTime(d2);
      return d1.equals(d2);
   }

   /**
    * Aggiunge ad una data un numero di giorni fissato
    *
    * @param d
    * @param min_number: minuti da aggiungere
    * @return
    */
   public static Date addDays(Date d, int days_number) {
      GregorianCalendar d_gc = new GregorianCalendar();
      d_gc.setTime(d);
      d_gc.add(Calendar.DAY_OF_YEAR, days_number);
      Date updated_date = d_gc.getTime();
      // System.out.println("DATA da modificare:"+d.toString()+ "nuova DATA:
      // "+updated_date.toString()+" minuti aggiunti: "+min_number);
      return updated_date;
   }

   /**
    * Recupera le informazioni sugli aggiornamenti manuali dell'ora del
    * dispositivo. Confronta le informazioni con le date note di cambio dell'ora
    * e aggiorna gli orari degli eventi di conseguenza.
    *
    * @param r
    * @param selectedDev
    * @return
    */
   /**
    * Controlla se c'è stato un update manuale dell'ora del dispositivo in una
    * data tra la data del cambio dell'ora(previous_DST_forward) e la data
    * evento
    *
    * @param dev_updates aggiornamenti dell'ora svolti sul dispositivo di
    * interesse (tabella device_update)
    * @param previous_DST_forward
    * @param date
    * @return true se il dispositivo è già stato aggiornato manualmente dal
    * paziente (quindi data evento è giusta, a meno di disallineamento di orario
    * non dovuto a cambio dell'ora)
    */
   /**
    * restituisce la massima tra due date
    *
    * @return
    */
   public static Date maxDate(Date date1, Date date2) {

      return date1.after(date2) ? date1 : date2;

   }

   /**
    * restituisce la massima tra due date
    *
    * @return
    */
   public static Date minDate(Date date1, Date date2) {

      return date1.after(date2) ? date2 : date1;

   }

   /**
    * restituisce la massima tra due date
    *
    * @return
    */
   public static Date minDateNoTime(Date date1, Date date2) {
      Date d1 = removeTime(date1);
      Date d2 = removeTime(date2);
      return d1.after(d2) ? d2 : d1;

   }

   /**
    * Controlla se due date sono nello stesso giorno
    *
    * @param date
    * @param date_start
    * @param date_stop
    * @return
    */
   public static boolean sameDay(Date d1, Date d2) {
      Date dd1 = removeTime(d1);
      Date dd2 = removeTime(d2);
      if (dd1.getTime() == dd2.getTime()) {
         return true;
      } else {
         return false;
      }
   }

   /**
    * Controlla se due date sono nello stesso giorno
    *
    */
   public static boolean sameDay(long d1, long d2) {
      Date dd1 = removeTime(new Date(d1));
      Date dd2 = removeTime(new Date(d2));
      if (dd1.getTime() == dd2.getTime()) {
         return true;
      } else {
         return false;
      }
   }

   /**
    * Controlla se una data si colloca tra due estremi fissati
    *
    * @param date
    * @param date_start
    * @param date_stop
    * @return
    */
   public static boolean isDateBetween(Date date, Date date_start, Date date_stop) {
      return ((date.after(date_start)) && (date.before(date_stop)));
   }

   /**
    * Controlla se una data si colloca tra due estremi fissati
    *
    * @param date
    * @param date_start
    * @param date_stop
    * @return
    */
   public static boolean isDateBetweenClosed(Date date, Date date_start, Date date_stop) {
      return ((!date.before(date_start)) && (!date.after(date_stop)));
   }

   /**
    * Converte una data Date in GregorianCalendar
    *
    * @param d
    * @return
    */
   public static GregorianCalendar convertDateToGC(Date d) {
      GregorianCalendar gc = new GregorianCalendar();
      long time_ = d.getTime();
      // System.out.println("long "+time_);
      // gc.setTime(d);
      gc.getTime().setTime(time_);
      return gc;
   }

   /**
    * Converte una data GregorianCalendar in Date
    *
    * @param gc: data da convertire
    * @return
    */
   public static Date convertGCtoDate(GregorianCalendar gc) {
      Date dd = new Date();
      dd.setTime(gc.getTimeInMillis());
      return dd;
   }

   /**
    * Quando l'ora cambia da legale a solare, di fatto l'ora tra le 2 e le 3
    * esiste due volte, quindi mi serve eliminare i dati della prima "istanza"
    * di quell'ora. Il metodo controlla se la data memorizzata dal sensore è tra
    * le 2 e le 3 del giorno di cambio dell'ora di quell'anno
    *
    * @param date
    * @return
    */
   public static boolean isDSTDuplicate(Date date) {
      GregorianCalendar gc = DateManager.convertDateToGC(date);
      int d_year = gc.get(Calendar.YEAR);
      Date datetime_DST_change = DateManager.getDSTforwardDate(d_year);
      Date datetime_DST_change_start = DateManager.addMin((Date) datetime_DST_change.clone(),
              -TimeConstant.MINS_IN_HOUR);
      return (DateManager.isDateBetween(date, datetime_DST_change_start, datetime_DST_change));

   }

   /**
    *
    * @param date
    * @return
    */
   public static Date getDSTforwardDate(int year) {
      // qui eventuale recupero di date DST in funzione del Locale
      HashMap<Integer, GregorianCalendar> DST_backward_dates = TimeConstant.getDSTdates_backward_IT();
      return DateManager.convertGCtoDate(DST_backward_dates.get(year));
   }

   /*
	 * public List<TimeInterval> retrieveInvalidIntervals(Device dev){ Trova
	 * dove le date degli eventi sono sbagliate di almeno X minuti (soglia
	 * fissata nella classe che definisce le costanti di tempo) }
    */
   /**
    * Converte una data in stringa
    *
    * @param d
    * @param sdf
    * @return
    */
   public static String dateToString(Date d, SimpleDateFormat sdf) {
      String s = Constant.MISSING_INFORMATION;
      s = sdf.format(d);
      return s;
   }

   /**
    * Calcola quanti secondi di differenza ci sono tra due date
    *
    * @param start_date
    * @param stop_date
    * @return
    */
   public static int computeSecondsBetween(Date start_date, Date stop_date) {
      return (int) (Math.abs(stop_date.getTime() - start_date.getTime()) / (TimeConstant.MILLISECOND_IN_SECOND));
   }

   /**
    * Calcola quanti giorni di differenza ci sono tra due date
    *
    * @param start_date
    * @param stop_date
    * @return
    */
   public static int computeDaysBetween(Date start_date, Date stop_date) {
      return (int) (Math.abs(stop_date.getTime() - start_date.getTime()) / (TimeConstant.MILLISECOND_IN_SECOND
              * TimeConstant.SECONDS_IN_MIN * TimeConstant.MINS_IN_HOUR * TimeConstant.HOURS_IN_DAY));
   }

   public static Date removeTime(Date date) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.set(Calendar.HOUR_OF_DAY, 0);
      cal.set(Calendar.MINUTE, 0);
      cal.set(Calendar.SECOND, 0);
      cal.set(Calendar.MILLISECOND, 0);
      return cal.getTime();
   }

   public static String computeDuration(long x) {
      long g = x / (60 * 60 * 1000 * 24);
      x -= g * (60 * 60 * 1000 * 24);
      long h = x / (60 * 60 * 1000);
      x -= h * (60 * 60 * 1000);
      long m = x / (60 * 1000);
      x -= m * (60 * 1000);
      long s = x / 1000;
      x -= s * 1000;
      String d = "";
      if (g > 0) {
         d = g + "g " + h + "h " + m + " min";
      } else if (h > 0) {
         d = h + "h " + m + " min";
      } else if (m > 0) {
         d = m + " min";
      }

      return d;
   }

   public static String computeDuration(Integer v) {
      int x = v;
      long g = x / (60 * 60 * 1000 * 24);
      x -= g * (60 * 60 * 1000 * 24);
      long h = x / (60 * 60 * 1000);
      x -= h * (60 * 60 * 1000);
      long m = x / (60 * 1000);
      x -= m * (60 * 1000);
      long s = x / 1000;
      x -= s * 1000;
      String d = "";
      if (g > 0) {
         d = g + "g " + h + "h " + m + " min";
      } else if (h > 0) {
         d = h + "h " + m + " min";
      } else if (m > 0) {
         d = m + " min";
      }

      return d;
   }

   public static Date truncate(Date d, int min) {
      long time = d.getTime();
      return new Date(time - time % (60000 * min));
   }
   
   
     public static OffsetDateTime truncate(OffsetDateTime d, int min) {
      long time = d.toInstant().toEpochMilli();
      long t = time - time % (60000 * min);
      
      return new Date(t).toInstant().atOffset(ZoneOffset.UTC);

   }
}
