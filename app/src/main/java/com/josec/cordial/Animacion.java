package com.josec.cordial;

import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Animacion {

    private ImageView logo,google;
    private TextView marca,version;

    public Animacion(ImageView logo, TextView marca, ImageView google, TextView version){
        this.logo = logo;
        this.marca = marca;
        this.google = google;
        this.version = version;
    }

    public Pair[] animacion() {

        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View, String>(this.logo, "logo");
        pairs[1] = new Pair<View, String>(this.marca, "marca");
        pairs[2] = new Pair<View, String>(this.google, "google");
        pairs[3] = new Pair<View, String>(this.version, "version");

        return pairs;
    }

}
