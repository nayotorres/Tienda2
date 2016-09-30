package demo.torres.com.tienda;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import demo.torres.com.tienda.Card.Card_Producto;
import demo.torres.com.tienda.Util.HttpRequest;
import demo.torres.com.tienda.Util.ProcesaInformacion;
import demo.torres.com.tienda.Util.Result;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;



public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        incia();
    }


    private void incia()
    {

        String url ="http://demos.dflabs.io/store/v1/products/?format=json";
        new HttpTask().execute(url);
    }

    private class HttpTask extends AsyncTask<String, Long, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return HttpRequest.get(urls[0]).accept("application/json")
                        .body();
            } catch (HttpRequest.HttpRequestException exception) {
                Log.i("Error", exception.getMessage());
                return null;
            }
        }
        @Override
        protected void onPostExecute(String response) {
            Log.i("http", response);
            List<Card> cards = new ArrayList<Card>();
            try {

                List<Result> resultList;
                ProcesaInformacion procesaInformacion = new ProcesaInformacion();
                resultList =  procesaInformacion.ProcesaJson(response);

                    Card_Producto cardProducto;

                    for(Result result : resultList)
                    {
                        cardProducto = new Card_Producto(Principal.this,result);
                        cards.add(cardProducto);
                    }

            }

            catch (Exception ex)
            {

            }
            CardArrayAdapter mCardArrayAdapter= new CardArrayAdapter(Principal.this,cards);
            CardListView mListView = (CardListView) findViewById(R.id.list_card_productos);

            if (mListView!=null)
            {
                mListView.setAdapter(mCardArrayAdapter);
            }
        }
    }


    private void getProductos(String json)  {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

           for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jobjet = jsonArray.getJSONObject(i);

               jobjet.getString("name");
               jobjet.getString("price");
               jobjet.getString("currency");
            }
        }
        catch (JSONException jx)
        {
            jx.getMessage();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle menu item selection
        switch (item.getItemId()) {
            case R.id.id_mapa:
                //Opciones_Capas();
                Intent intent = new Intent(this, MapsActivity.class);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
