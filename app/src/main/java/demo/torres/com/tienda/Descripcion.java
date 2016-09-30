package demo.torres.com.tienda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Descripcion extends AppCompatActivity {

    TextView nombre,precio,modelo,descricion,brief,codigo;
    String nombre_,precio_,modelo_,descricion_,brief_,codigo_,currency;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);

        configuraControles();
        Bundle extras = getIntent().getExtras();

        nombre_=extras.getString("name");
        precio_=extras.getString("price");
        modelo_=extras.getString("modelo");
        descricion_=extras.getString("descripcion");
        brief_=extras.getString("brief");
        codigo_=extras.getString("code");
        currency=extras.getString("currency");

        inicia();
    }

    private  void inicia()
    {
        nombre.setText(nombre_);
        precio.setText("Precio: "+ precio_ + " "+currency);
        modelo.setText("Modelo: "+modelo_);
        descricion.setText("Descripcion: "+ descricion_);
        brief.setText(brief_);
        codigo.setText("Codigo: "+codigo_);
    }

    private void configuraControles()
    {
        nombre =(TextView)findViewById(R.id.txtname);
        precio =(TextView)findViewById(R.id.txtprecio);
        modelo =(TextView)findViewById(R.id.txtmodelo);
        descricion =(TextView)findViewById(R.id.txtdescripcion);
        brief =(TextView)findViewById(R.id.txtbrief);
        codigo =(TextView)findViewById(R.id.txtcode);
    }
}
