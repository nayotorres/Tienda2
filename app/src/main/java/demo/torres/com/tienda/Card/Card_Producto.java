package demo.torres.com.tienda.Card;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

import demo.torres.com.tienda.Descripcion;
import demo.torres.com.tienda.R;
import demo.torres.com.tienda.Util.ProductImage;
import demo.torres.com.tienda.Util.Result;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardThumbnail;

/**
 * Created by torres on 27/09/2016.
 */

public class Card_Producto extends Card
{

    Result result;
    public Card_Producto(Context context, Result result_) {
        super(context, R.layout.card_info);
        result = result_;
        inicio();
    }

    private void inicio()
    {

        TituloCardHeader header;

        header = new TituloCardHeader(getContext(), R.layout.card_titulo, result.getName());
        header.setButtonExpandVisible(false);

        setExpanded(false);
        PicassoCardThumbnail cardThumbnail = new PicassoCardThumbnail(mContext);

        cardThumbnail.setExternalUsage(true);

        addCardThumbnail(cardThumbnail);
        addCardHeader(header);

        setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view)
            {
                Bundle bundle = new Bundle();
                bundle.putString("name", result.getName());
                bundle.putString("code", result.getCode());
                bundle.putString("price", result.getPrice());
                bundle.putString("currency", result.getCurrency());
                bundle.putString("modelo", result.getModel());
                bundle.putString("descripcion", result.getDescription());
                bundle.putString("brief", result.getBrief());
                Intent intent = new Intent(getContext(), Descripcion.class);
                intent.putExtras(bundle);
                getContext().startActivity(intent);

            }
        });


    }
    class PicassoCardThumbnail extends CardThumbnail {

        public PicassoCardThumbnail(Context context) {
            super(context);
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View viewImage)
        {

            List<ProductImage> productImageList = result.getProductGalleryImages();
            ProductImage productImage = productImageList.get(0);

                Picasso.with(getContext()).setIndicatorsEnabled(true);
                Picasso.with(getContext())
                        .load(productImage.getThumbnail())
                        .error(R.drawable.ic_sinimagen)
                        .into((ImageView) viewImage);
        }
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        if (view != null)
        {
            TextView txt_nombre = (TextView) view
                    .findViewById(R.id.card_txt_nombre);
            TextView txt_dias = (TextView) view
                    .findViewById(R.id.card_txt_precio);


                txt_nombre.setText(result.getDescription());
                txt_dias.setText("Precio: "+result.getPrice() +" "+ result.getCurrency());

        }
    }
}
