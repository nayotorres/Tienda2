package demo.torres.com.tienda.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by torres on 29/09/2016.
 */
public class ProcesaCoordenadas
{
    List<Localizacion> localizacionList = new ArrayList<Localizacion>();
    public List<Localizacion> ProcesaJson(String json)
    {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            Localizacion localizacion;
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jobjet = jsonArray.getJSONObject(i);

                localizacion = new Localizacion();
                localizacion.setAddress(jobjet.getString("address"));
                localizacion.setLatitud(jobjet.getDouble("latitude"));
                localizacion.setLongitud(jobjet.getDouble("longitude"));
                localizacion.setName(jobjet.getString("name"));
                localizacion.setPhone(jobjet.getString("phone"));
                localizacion.setEmail(jobjet.getString("email"));


                localizacionList.add(localizacion);
            }
        }
        catch (JSONException jx)
        {
            jx.getMessage();
        }

        return localizacionList;
    }
}
