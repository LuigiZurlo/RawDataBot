/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Location;
import domain.Measurement;
import domain.Parameter;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import json.*;

/**
 *
 * @author LuigiZ
 */
public class RequestSensorData {

   public List<Measurement> getData(String request, Location location, Map<String, Parameter> mappa) throws Exception {

//      System.out.println("inizio gatData = " + new Date());
      List<Measurement> misure = null;
      int z = 0;
      ObjectMapper mapper = new ObjectMapper();
      try {
         JsonDad usrPost = mapper.readValue(new URL(request), JsonDad.class);
         misure = ManagerDB.insertOttimizzato(usrPost, location, mappa);
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
//      System.out.println("fine getData = " + new Date() +" "+ misure.size() + " misure ");

      return misure;
   }

}
