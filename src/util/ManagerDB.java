package util;

import domain.Location;
import domain.Measurement;
import domain.Parameter;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import json.*;


public class ManagerDB {
   
   public static List<Object> getObjectList(String query, ArrayList<QueryParameter> queryParameters) {
      return ManagerDB.getObjectList(query, queryParameters, 0);
   }

   /**
    * Riceve una query in una stringa e un array di queryParameters e
    * restituisce una Lista di risultati
    */
   public static List<Object> getObjectList(String query, ArrayList<QueryParameter> queryParameters, int maxResults) {
      Session session = HibernateUtil.getSessionFactory().openSession();
      List<Object> result = null;
      
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         Query q = session.createQuery(query);
         if (maxResults > 0) {
            q.setMaxResults(maxResults);
         }
         for (QueryParameter p : queryParameters) {
            q.setParameter(p.getNameParameter(), p.getO());
            // System.out.println("ManagerDB query "+p.toString());
         }
         result = q.list();
         // List<Event> events = ((List<Event>)(List<?>) (result));
         // for(Event e:events){
         // System.out.println("Sessioni "+e.getSession().getIdSession());
         // }
         tx.commit();
      } catch (HibernateException he) {
         if (tx != null) {
            tx.rollback();
         }
         throw he;
      } catch (Exception e) {
         e.printStackTrace();
         // System.out.println("Sospettato 1");
         if (tx != null) {
            tx.rollback();
         }
      } finally {
         session.close();
      }
      return result;
   }
   
   
//   public static int insertData(JsonDad dati, Location location, int file_id, Map<String, Parameter> mappa){
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        Map<String, Parameter> parametri = mappa;
//        Map<OffsetDateTime, Map<String,BigDecimal>> misure = measureToMap(dati);
//        int z = 0;
//        
//        
//        try {
//            
//            for (Map.Entry<OffsetDateTime, Map<String, BigDecimal>> mis : misure.entrySet()) {
//                
//                Iterator<Map.Entry<String, BigDecimal>> it = mis.getValue().entrySet().iterator();
//                
//                while(it.hasNext()){
//                    
//                    Map.Entry<String, BigDecimal> entry = it.next();   
//                    if (entry.getValue()!=null && parametri.containsKey(entry.getKey())){
//                    tx = session.beginTransaction();
//                    
//                    Measurement misura = new Measurement();
//
//                    misura.setTimestamp(mis.getKey());                       
//                    misura.setLocation(location);
//                    misura.setParameter(parametri.get(entry.getKey()));
//                    misura.setValore(entry.getValue());
//                   
//                    
//                        session.save(misura);
//                        tx.commit();
//                    } else{
//                    continue;
//                    }
//                } 
//            }
//            //System.out.println("Misure inserite per file = " + z);
//            
//      } catch (HibernateException e) {
//         if (tx != null) {
//            tx.rollback();
//         }
//         e.printStackTrace();
//      } finally {
//         session.close();
//      }
//        
//   
//        return z;
//   }
   
//   private static Map<OffsetDateTime, Map<String,BigDecimal>> measureToMap(JsonDad dati){
//       Map<String, BigDecimal> misura = new HashMap<>();
//       Map <OffsetDateTime, Map<String, BigDecimal>> measures =  new HashMap<>();
//       for(int i = 0; i < dati.getFeeds().size() ; i++){
//           misura.put(dati.getChannel().getField1(), dati.getFeeds().get(i).getField1());
//           misura.put(dati.getChannel().getField2(), dati.getFeeds().get(i).getField2());
//           misura.put(dati.getChannel().getField3(), dati.getFeeds().get(i).getField3());
//           misura.put(dati.getChannel().getField4(), dati.getFeeds().get(i).getField4());
//           misura.put(dati.getChannel().getField5(), dati.getFeeds().get(i).getField5());
//           misura.put(dati.getChannel().getField6(), dati.getFeeds().get(i).getField6());
//           misura.put(dati.getChannel().getField7(), dati.getFeeds().get(i).getField7());
//           misura.put(dati.getChannel().getField8(), dati.getFeeds().get(i).getField8());
//           
//           measures.put(dati.getFeeds().get(i).getCreatedAt(), misura);
//       }
//   
////       System.out.println("dati.getFeeds().size() = " + dati.getFeeds().size());
//       return measures;
//   }
   
   public static List<Measurement> insertOttimizzato(JsonDad dati, Location location,  Map<String, Parameter> mappa){
//       Session session = HibernateUtil.getSessionFactory().openSession();
//       Transaction tx = null;
       Map<String, Parameter> parametri = mappa;
       List<Measurement> misure = new ArrayList<>();
//        Map<Date, Map<String,BigDecimal>> misure = measureToMap(dati);
//        int z = 0;
//        try {
        for(int i = 0; i < dati.getFeeds().size() ; i++){
            for(int j = 1; j < 9 ; j++){
//                        System.out.println("j = " + j);
                        if (dati.getFeeds().get(i).getField(j)!= null && parametri.containsKey(dati.getChannel().getField(j))){
//                            tx = session.beginTransaction();
                            Measurement misura = new Measurement();
                            misura.setTimestamp(dati.getFeeds().get(i).getCreatedAt());
                            misura.setLocation(location);
                            misura.setParameter(parametri.get(dati.getChannel().getField(j)));

                            misura.setValore(dati.getFeeds().get(i).getField(j));
                            misure.add(misura);
//                            session.save(misura);
//                                if ( z % 30 == 0 ){
//                                session.flush();
//                                session.clear();
//                                }
//                            tx.commit();
                        } else {
                            continue;
                        }
                    
            }
        }
//        } catch (HibernateException e) {
//                        if (tx != null) {
//                            tx.rollback();
//                        }
//                        e.printStackTrace();
//                    } finally {
//                        session.close();
//                    }
        return misure;
//       System.out.println("dati.getFeeds().size() = " + dati.getFeeds().size());

   }
   
    public static void saveObjectList(List<Object> list) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        int i = 0;

        for (Object o : list) {
            try {
                
                session.save(o);
                
                i++;
                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
                
            } catch (HibernateException he) {
                if (tx != null) {
                    tx.rollback();
                }
                throw he;
            }

        }
        tx.commit();
        session.close();
    }
  
    
   
//   private static Map<String, Parameter> getParam (int file_id){
//        Map<String, Parameter> parametri = new HashMap<>();
//        ArrayList<QueryParameter> queryParameters = new ArrayList<>();
//        List<Object> parameters  = new ArrayList<>();   
//        if (file_id <3){
//            parameters = ManagerDB.getObjectList(constant.Query.GET_FIRST_PARAMETER, queryParameters);
//        } else {
//            parameters = ManagerDB.getObjectList(constant.Query.GET_LAST_PARAMETER, queryParameters);
//        }
//        for (Object p : parameters) {
//           Parameter par = (Parameter) p;
//           parametri.put(par.getCode(), par);
//       }
//        
//        return parametri;
//   }
//   
   public static List<Map<String,Parameter>> getParamMap(){
        ArrayList<QueryParameter> queryParameters = new ArrayList<>();
        Map<String, Parameter> par1 = new HashMap<>();
        Map<String, Parameter> par2 = new HashMap<>();
        
        List<Map<String,Parameter>> paramListMap = new ArrayList<>();
        
        List<Object> parList1 = ManagerDB.getObjectList(constant.Query.GET_FIRST_PARAMETER, queryParameters);
        
        List<Object> parList2 = ManagerDB.getObjectList(constant.Query.GET_LAST_PARAMETER, queryParameters);
        
        for (Object p : parList1){
            Parameter par = (Parameter) p;
            par1.put(par.getCode(), par);
        }
        
        for (Object p : parList2){
            Parameter par = (Parameter) p;
            par2.put(par.getCode(), par);
        }
        paramListMap.add(par1);
        paramListMap.add(par2);
        
        return paramListMap;
   }
   
   
   
}
