package com.josec.cordial;

import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Animacion {

    private ImageView imgTranLogo, imgTranAlumno, imgTranProfesora;
    private TextView txtTranMarca, txtTranVersion, txtTranAlumno, txtTranProfesora;

    public Animacion(ImageView imgTranLogo, ImageView imgTranAlumno, ImageView imgTranProfesora, TextView txtTranMarca, TextView txtTranVersion, TextView txtTranAlumno, TextView txtTranProfesora) {
        this.imgTranLogo = imgTranLogo;
        this.imgTranAlumno = imgTranAlumno;
        this.imgTranProfesora = imgTranProfesora;
        this.txtTranMarca = txtTranMarca;
        this.txtTranVersion = txtTranVersion;
        this.txtTranAlumno = txtTranAlumno;
        this.txtTranProfesora = txtTranProfesora;
    }

    public Pair[] animacion() {

        Pair[] pairs = new Pair[7];
        pairs[0] = new Pair<View, String>(this.imgTranLogo, "imgTranLogo");
        pairs[1] = new Pair<View, String>(this.imgTranAlumno, "imgTranAlumno");
        pairs[2] = new Pair<View, String>(this.imgTranProfesora, "imgTranProfesora");
        pairs[3] = new Pair<View, String>(this.txtTranMarca, "txtTranMarca");
        pairs[4] = new Pair<View, String>(this.txtTranVersion, "txtTranVersion");
        pairs[5] = new Pair<View, String>(this.txtTranAlumno, "txtTranAlumno");
        pairs[6] = new Pair<View, String>(this.txtTranProfesora, "txtTranProfesora");

        return pairs;
    }

}
