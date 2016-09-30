package demo.torres.com.tienda.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by torres on 29/09/2016.
 */
public class ProcesaInformacion
{
    List<Result> resultList = new ArrayList<Result>();

    public List<Result> ProcesaJson(String json)
    {

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            Result result;
            for (int i = 0; i < jsonArray.length(); i++) {


                JSONObject jobjet = jsonArray.getJSONObject(i);
                if(jobjet.getString("code").equals("TEST"))
                    break;
                result = new Result();
                result.setId(jobjet.getString("id"));
                result.setCode(jobjet.getString("code"));
                result.setDescription(jobjet.getString("description"));
                result.setName(jobjet.getString("name"));
                result.setPrice(jobjet.getString("price"));
                result.setModel(jobjet.getString("model"));
                result.setCurrency(jobjet.getString("currency"));
                result.setBrief(jobjet.getString("brief"));

                result.setProductGalleryImages(ObtnenImagen(jobjet.getJSONArray("product_gallery_images")));

                resultList.add(result);
            }
        }
        catch (JSONException jx)
        {
            jx.getMessage();
        }

        return resultList;
    }

    private  List<ProductImage> ObtnenImagen(JSONArray jsonArray) throws JSONException {
        List<ProductImage> productImageList = new ArrayList<ProductImage>();
        ProductImage productImage;
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            productImage = new ProductImage();
            productImage.setImg(jsonObject.getString("image"));
            productImage.setThumbnail(jsonObject.getString("thumbnail"));
            productImageList.add(productImage);
        }

        return productImageList;

    }
}
