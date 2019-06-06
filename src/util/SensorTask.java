/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.List;

//import org.primefaces.json.JSONArray;
//import org.primefaces.json.JSONObject;
//import constant.EndPoint;
import constant.Query;
import constant.EndPoint;
import domain.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author LuigiZ
 */
public class SensorTask {

    public Map<String, Parameter2> parametri_elaborati = new HashMap<>();

    public void runDownladData() throws Exception {
        getParametriElaborati();
        ArrayList<QueryParameter> queryParameters = new ArrayList<>();

        List<Object> locations = ManagerDB.getObjectList(Query.GET_ALL_LOCATIONS, queryParameters);
        List<Map<String, Parameter>> mappaParametri = ManagerDB.getParamMap();//System.out.println(sensors.size());

        OffsetDateTime endTime = OffsetDateTime.now(ZoneOffset.UTC);
        OffsetDateTime startTime = endTime.minusMinutes(2);
        String del = "\t";
        System.out.println(startTime + del + endTime);
        for (Location location : ((List<Location>) (List<?>) (locations))) {

            Sensor sensore = location.getSensor();
//            System.out.println("-----------------------------------------------------------"); //vecchio log
//            System.out.println("Scarico dati sensore: " + sensore.getDisplayName() + "\t TS id:" + sensore.getPrimaryPurpleairId()); //vecchio log
            //System.out.println("loc_id = " + sensorLocation);

            RequestSensorData req1 = new RequestSensorData();
            RequestSensorData req2 = new RequestSensorData();
            RequestSensorData req3 = new RequestSensorData();
            RequestSensorData req4 = new RequestSensorData();

            List<Object> misure = new ArrayList<>();
            String requestSCB = String.format(EndPoint.GET_DEVICES, sensore.getSecondaryChannelB(), sensore.getSecondaryKeyB(), startTime, endTime);
//            System.out.println("requestSCB = " + requestSCB); //vecchio log
            List<Measurement> m4 = req4.getData(requestSCB, location, mappaParametri.get(1));

            String requestSCA = String.format(EndPoint.GET_DEVICES, sensore.getSecondaryChannelA(), sensore.getSecondaryKeyA(), startTime, endTime);
//            System.out.println("requestSCA = " + requestSCA); //vecchio log
            List<Measurement> m3 = req3.getData(requestSCA, location, mappaParametri.get(1));

            String requestPCB = String.format(EndPoint.GET_DEVICES, sensore.getPrimaryChannelB(), sensore.getPrimaryKeyB(), startTime, endTime);
//            System.out.println("requestPCB = " + requestPCB); //vecchio log
            List<Measurement> m2 = req2.getData(requestPCB, location, mappaParametri.get(0));

            String requestPCA = String.format(EndPoint.GET_DEVICES, sensore.getPrimaryChannelA(), sensore.getPrimaryKeyA(), startTime, endTime);
//            System.out.println("requestPCA = " + requestPCA); //vecchio log
            List<Measurement> m1 = req1.getData(requestPCA, location, mappaParametri.get(0));

            misure.addAll(m1);
            misure.addAll(m2);
            misure.addAll(m3);
            misure.addAll(m4);

            System.out.println(sensore.getId() + del + sensore.getPrimaryPurpleairId() + del + m1.size() + del
                    + m2.size() + del + m3.size() + del + m4.size());
//            System.out.println("inizio save = " + new Date()); //vecchio log
            ManagerDB.saveObjectList(misure);
//            System.out.println("fine save = " + new Date());//vecchio log
//            System.out.println("Misure totali inserite = " + misure.size());//vecchio log
        }
        System.out.println(""); //vecchio log
//        System.out.println(""); //vecchio log
//        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"); //vecchio log

    }

//
    public void downloadPastData() throws Exception {
        getParametriElaborati();
        ArrayList<QueryParameter> queryParameters = new ArrayList<>();

        List<Object> locations = ManagerDB.getObjectList(Query.GET_ALL_LOCATIONS, queryParameters);

        List<Map<String, Parameter>> mappaParametri = ManagerDB.getParamMap();

        for (Location location : ((List<Location>) (List<?>) (locations))) {
            //for (int i = 0; i < locations.size(); i++) {
//       Location location = (Location) locations.get(i);
            //Location sensorLocation = getLocation(sensore);
            Sensor sensore = location.getSensor();
            Date dataPrimaMisura = getFirstMeasure(location);
            Date installationDate = DateManager.removeTime(sensore.getInstallation_date());

            Date startTime = DateManager.addDays(dataPrimaMisura, -1);
            Date endTime = dataPrimaMisura;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
            while (!startTime.before(installationDate)) {
                System.out.println("startdate" + DateManager.removeTime(startTime));
                System.out.println("installationDate = " + installationDate);
                System.out.println("");
                System.out.println("-----------------------------------------------------------");
                System.out.println("Scarico dati passati sensore: " + sensore.getDisplayName() + " del giorno :" + startTime.toString());
                String st = sdf.format(startTime);
                String et = sdf.format(endTime);
                //System.out.println("loc_id = " + sensorLocation);
                String requestPCA = String.format(EndPoint.GET_DEVICES, sensore.getPrimaryChannelA(), sensore.getPrimaryKeyA(), st, et);
                String requestPCB = String.format(EndPoint.GET_DEVICES, sensore.getPrimaryChannelB(), sensore.getPrimaryKeyB(), st, et);
                String requestSCA = String.format(EndPoint.GET_DEVICES, sensore.getSecondaryChannelA(), sensore.getSecondaryKeyA(), st, et);
                String requestSCB = String.format(EndPoint.GET_DEVICES, sensore.getSecondaryChannelB(), sensore.getSecondaryKeyB(), st, et);

                String requestPCA_AVG = String.format(EndPoint.GET_AVG, sensore.getPrimaryChannelA(), sensore.getPrimaryKeyA(), st, et);
                String requestSCA_AVG = String.format(EndPoint.GET_AVG, sensore.getSecondaryChannelA(), sensore.getSecondaryKeyA(), st, et);
                System.out.println("requestSCA_AVG = " + requestSCA_AVG);
                List<Object> misure = new ArrayList<>();
                RequestSensorData req1 = new RequestSensorData();
                RequestSensorData req2 = new RequestSensorData();
                RequestSensorData req3 = new RequestSensorData();
                RequestSensorData req4 = new RequestSensorData();

                RequestSensorData req5AVG = new RequestSensorData();
                RequestSensorData req6AVG = new RequestSensorData();

                List<Measurement> m1 = req1.getData(requestPCA, location, mappaParametri.get(0));
                List<Measurement> m2 = req2.getData(requestPCB, location, mappaParametri.get(0));
                List<Measurement> m3 = req3.getData(requestSCA, location, mappaParametri.get(1));
                List<Measurement> m4 = req4.getData(requestSCB, location, mappaParametri.get(1));

                List<Measurement> m1AVG = req5AVG.getData(requestPCA_AVG, location, mappaParametri.get(0));
                List<Measurement> m3AVG = req6AVG.getData(requestSCA_AVG, location, mappaParametri.get(1));

                misure.addAll(m1);
                misure.addAll(m2);
                misure.addAll(m3);
                misure.addAll(m4);
                misure.addAll(doPastAvg(m1AVG, m3AVG));

                System.out.println("inizio save = " + new Date());
                ManagerDB.saveObjectList(misure);
                System.out.println("fine save = " + new Date());
                System.out.println("Misure totali inserite = " + misure.size());
                startTime = DateManager.addDays(startTime, -1);
                endTime = DateManager.addDays(endTime, -1);
                System.out.println("");
                System.out.println("");
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            }

        }

    }

    public void runDownladDataAVG() throws Exception {
//        System.out.println("this = ");
        getParametriElaborati();
        
        ArrayList<QueryParameter> queryParameters = new ArrayList<>();

        List<Object> locations = ManagerDB.getObjectList(Query.GET_ALL_LOCATIONS, queryParameters);
        List<Map<String, Parameter>> mappaParametri = ManagerDB.getParamMap();

        OffsetDateTime endTime = OffsetDateTime.now(ZoneOffset.UTC);
        OffsetDateTime startTime = endTime.minusMinutes(2);

        for (Location location : ((List<Location>) (List<?>) (locations))) {

            Sensor sensore = location.getSensor();
            System.out.println("-----------------------------------------------------------");

            System.out.println("MISURE AVG");

            System.err.println("Scarico dati sensore: " + sensore.getDisplayName() + "\t TS id:" + sensore.getPrimaryPurpleairId());

            RequestSensorData req1 = new RequestSensorData();

            RequestSensorData req3 = new RequestSensorData();

            List<Object> misure = new ArrayList<>();

            String requestSCA = String.format(EndPoint.GET_AVG, sensore.getSecondaryChannelA(), sensore.getSecondaryKeyA(), startTime, endTime);
            System.out.println("requestSCA = " + requestSCA);
            List<Measurement> m3 = req3.getData(requestSCA, location, mappaParametri.get(1));

            String requestPCA = String.format(EndPoint.GET_AVG, sensore.getPrimaryChannelA(), sensore.getPrimaryKeyA(), startTime, endTime);
            System.out.println("requestPCA = " + requestPCA);
            List<Measurement> m1 = req1.getData(requestPCA, location, mappaParametri.get(0));

            misure.addAll(doAvgChannels(m1, m3));

            System.out.println("inizio save = " + new Date());
            ManagerDB.saveObjectList(misure);
            System.out.println("fine save = " + new Date());
            System.out.println("Misure AVG totali inserite = " + misure.size());
        }
        System.out.println("");
        System.out.println("");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

    }

    private Date getFirstMeasure(Location l) {
        ArrayList<QueryParameter> c = new ArrayList<>();
        //System.out.println("queryParameters = " + sensore.getId());
        QueryParameter loc = new QueryParameter("location_id", l.getId());

        c.add(loc);
        List<Object> location = ManagerDB.getObjectList(Query.GET_FIRST_MEASURE, c);
        Date data = (Date) location.get(0);
        System.out.println("data = " + data);
//      Date dataFinale = DateManager.removeTime(data);
        return data;
    }

    private void getParametriElaborati() {
        ArrayList<QueryParameter> queryParameters = new ArrayList<>();
        List<Object> parList1 = ManagerDB.getObjectList(constant.Query.GET_PARAMETER_ELAB, queryParameters);
        for (Object object : parList1) {
            Parameter2 p2 = (Parameter2) object;
            switch (p2.getCode()) {
                case "pm1":
                    parametri_elaborati.put("PM1.0 (ATM)", p2);
                    break;
                case "pm25":
                    parametri_elaborati.put("PM2.5 (ATM)", p2);
                    break;
                case "pm10":
                    parametri_elaborati.put("PM10.0 (ATM)", p2);
                    break;
                case "temperature":
                    parametri_elaborati.put("Temperature", p2);
                    break;
                case "humidity":
                    parametri_elaborati.put("Humidity", p2);
                    break;
                case "pressure":
                    parametri_elaborati.put("Pressure", p2);
                    break;

                default:
                    throw new AssertionError();
            }

        }
    }

    private List<Measurementavg> doPastAvg(List<Measurement> m1, List<Measurement> m3) {
        List<Measurementavg> mis = new ArrayList<>();
        List<Measurement> m1Temp = new ArrayList<>();
        List<Measurement> m3Temp = new ArrayList<>();
        if (m1.size() > 0) {
            Measurement temp = m1.get(0);
            //System.out.println("temp = " + temp.getTimestamp());

            int k = 0;
            for (int i = 0; i < m1.size(); i++) {
                if (temp.equals(m1.get(i))) {
                    m1Temp.add(m1.get(i));
//                System.out.println("i = " + m1Temp.size());
                } else {
                    for (int j = k; j < m3.size(); j++) {

                        if (temp.equals(m3.get(j))) {
                            m3Temp.add(m3.get(j));
//                        System.out.println("mmm = " + m3.get(j).getTimestamp());
                            k++;
                        } else {

                            mis.addAll(doAvgChannels(m1Temp, m3Temp));
                            if (m3.get(j).getTimestamp().after(m1.get(i).getTimestamp())) {
                                temp = m1.get(i);
                                //System.out.println("uso data m1 = " );
                            } else if ((m3.get(j).getTimestamp().before(m1.get(i).getTimestamp())) || m3.get(j).equals(m1.get(i))) {
                                temp = m3.get(j);
                            }
                            //System.out.println("new temp = " + temp.getTimestamp());
                            m1Temp.clear();
                            m3Temp.clear();
                            i--;
                            break;
                        }

                    }
                }

            }
        }
        // System.out.println("m3Temp = " + mis.size());
        return mis;
    }

    private List<Measurementavg> doAvgChannels(List<Measurement> m1, List<Measurement> m3) {
//        System.out.println("inzio avg " + m1.size() + " " + m3.size());
        List<Measurementavg> mis = new ArrayList<>();
        Map<String, Measurement> map1 = new HashMap<>();
        Map<String, Measurement> map3 = new HashMap<>();
        if (m1.isEmpty() && !m3.isEmpty()) {
            for (Measurement measurement : m3) {
                mis.add(addMisuraElaborata(measurement, measurement.getValore(), parametri_elaborati.get(measurement.getParameter().getCode())));

            }
            System.out.println("!!!!!!!Misure PRIMO canale mancanti");
        } else if (!m1.isEmpty() && m3.isEmpty()) {
            for (Measurement measurement : m1) {
                mis.add(addMisuraElaborata(measurement, measurement.getValore(), parametri_elaborati.get(measurement.getParameter().getCode())));

            }
            System.out.println("!!!!!!!Misure SECONDO canale mancanti");
        } else if (m1.isEmpty() && m3.isEmpty()) {
            System.out.println("!!!!!MISURE ENTRAMBE MANCANTI");
        } else {

            for (Measurement measurement : m1) {
                map1.put(measurement.getParameter().getCode(), measurement);
//                System.out.println("m1 = " + measurement.getTimestamp());
            }

            for (Measurement measurement : m3) {
                map3.put(measurement.getParameter().getCode(), measurement);
//                System.out.println("m3 = " + measurement.getTimestamp());
            }

            for (String entry : parametri_elaborati.keySet()) {
                if (map1.get(entry) != null && map3.get(entry) != null) {
                    mis.add(getMean(map1.get(entry), map3.get(entry)));
                } else if (entry.equals("Pressure") && map3.get(entry) != null) {
                    mis.add(convertPressure(map3.get(entry)));
                } else if (entry.equals("Temperature") && map1.get(entry) != null) {
                    mis.add(convertTemperature(map1.get(entry)));
                } else if (entry.equals("Humidity") && map1.get(entry) != null) {
                    mis.add(addMisuraElaborata(map1.get(entry), map1.get(entry).getValore(), parametri_elaborati.get(entry)));
                }
            }
        }
//        System.out.println("Misure avg inserite = " + mis.size());
        return mis;
    }

    private Measurementavg convertPressure(Measurement m) {
        BigDecimal pascal = m.getValore().multiply(new BigDecimal(100));
        return addMisuraElaborata(m, pascal, parametri_elaborati.get(m.getParameter().getCode()));

    }

    private Measurementavg convertTemperature(Measurement m) {
        BigDecimal sub = m.getValore().subtract(new BigDecimal(32));
        BigDecimal mul = sub.multiply(new BigDecimal(5));
        BigDecimal celsius = mul.divide(new BigDecimal(9), 6, RoundingMode.HALF_UP);

        return addMisuraElaborata(m, celsius, parametri_elaborati.get(m.getParameter().getCode()));
    }

    private Measurementavg addMisuraElaborata(Measurement m, BigDecimal valore, Parameter2 parameter) {
        Measurementavg misura = new Measurementavg();
        misura.setLocation(m.getLocation());
        misura.setValore(valore);
        misura.setTimestamp(m.getTimestamp());
        misura.setParameter(parameter);
        return misura;
    }

    private Measurementavg getMean(Measurement m1, Measurement m2) {

        BigDecimal pmSum = m1.getValore().add(m2.getValore());
        BigDecimal mean = pmSum.divide(new BigDecimal(2));

        return addMisuraElaborata(m1, mean, parametri_elaborati.get(m1.getParameter().getCode()));
    }

}
