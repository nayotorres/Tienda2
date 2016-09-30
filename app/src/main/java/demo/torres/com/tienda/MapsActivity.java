package demo.torres.com.tienda;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import demo.torres.com.tienda.Util.HttpRequest;
import demo.torres.com.tienda.Util.Localizacion;
import demo.torres.com.tienda.Util.ProcesaCoordenadas;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        String url ="http://demos.dflabs.io/store/v1/stores/?format=json";
        new UbiacionTask().execute(url);
    }

    private class UbiacionTask extends AsyncTask<String, Long, String> {
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

            try {

                ProcesaCoordenadas procesaCoordenadas = new ProcesaCoordenadas();
                List<Localizacion> localizacionList =procesaCoordenadas.ProcesaJson(response);

              for(Localizacion localizacion :localizacionList)
              {
                  LatLng ubicacion = new LatLng(localizacion.getLatitud(), localizacion.getLongitud());
                  MarkerOptions Store = new MarkerOptions();
                  Store.position(new LatLng(localizacion.getLatitud(), localizacion.getLongitud()));
                  Store.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_store));
                  Store.title(localizacion.getName());

                  mMap.addMarker(Store);
                  //mMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));

                  CameraPosition camPos = new CameraPosition.Builder()
                          .target(ubicacion).zoom(17).bearing(45).tilt(70).build();

                  CameraUpdate camUpd = CameraUpdateFactory
                          .newCameraPosition(camPos);
                  mMap.animateCamera(camUpd);


              }

            }

            catch (Exception ex)
            {

            }

        }
    }
}
