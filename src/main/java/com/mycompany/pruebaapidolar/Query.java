
package com.mycompany.pruebaapidolar;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;


public class Query {

    public static Double[] Request(){
        try{ 
        URL url=new URL("https://api.bluelytics.com.ar/v2/latest");
        HttpURLConnection conn=(HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
          
        int responseCode=conn.getResponseCode();
        if(responseCode!=200){
            throw new RuntimeException("Error de respuesta" + responseCode);
        }else{
            StringBuilder info=new StringBuilder();
            Scanner leer=new Scanner(url.openStream());
            
            while(leer.hasNext()){
                info.append(leer.nextLine());
            }
            leer.close();
           
            
            JSONObject datos= new JSONObject(info.toString());
            JSONObject oficial= datos.getJSONObject("blue");
            double avg = oficial.getDouble("value_avg");
            double compra = oficial.getDouble("value_buy");
            double venta = oficial.getDouble("value_sell");
            Double[] datosBlue = {avg,compra,venta};
            return datosBlue;
            }
        
       }catch(Exception e){
           e.printStackTrace();
    }
        return null;
    }
}
