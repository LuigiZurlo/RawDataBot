package main;

import util.SensorTask;

public class Run {

   public static void main(String[] args) throws Exception {

      try {
          switch (args[0]) {
              case "default":
                  {
                      SensorTask task = new SensorTask();
                      task.runDownladData();
                      task = null;
                      break;
                  }
//         System.out.println("Fine task con successo.");
              case "past":
                  {
                      SensorTask task = new SensorTask();
                      task.downloadPastData();
                      task = null;
                      break;
                  }
              case "avg":
                  {
//                      System.out.println("cioa");
                      SensorTask task = new SensorTask();
                      task.runDownladDataAVG();
                      task = null;
                      break;
                  }
              default:
                  break;
          }
      } catch (java.io.IOException e) {
//         System.out.println("Fine task per eccessivo numero di richieste.");
      } catch (Exception e) {
         e.printStackTrace();
//         System.out.println("Fine task per eccezione " + e.getClass().getName());
      } finally {
//         System.out.println("Fine esecuzione Bot.");
         System.exit(0);
      }

   }
}
