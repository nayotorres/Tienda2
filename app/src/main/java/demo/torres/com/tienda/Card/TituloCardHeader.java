package demo.torres.com.tienda.Card;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import demo.torres.com.tienda.R;
import it.gmariotti.cardslib.library.internal.CardHeader;

/**
 * Created by torres on 27/09/2016.
 */

public class TituloCardHeader  extends CardHeader {
    String titulo ="";



    public TituloCardHeader(Context context, int innerLayout, String titulo) {
        super(context, innerLayout);
        this.titulo= titulo;

    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        if (view != null) {
            TextView textView = (TextView) view
                    .findViewById(R.id.text_titulo_card);

            if (textView != null) {
                textView.setText(titulo);

            }
        }
    }
}
