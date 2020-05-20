package com.niloy.salah;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class CreateCustomDialog extends Dialog implements
        android.view.View.OnClickListener {
    private  TextView arabic, bangla, bangla2;
    private String one , two, three;


    public CreateCustomDialog(Context context, String one, String two, String three) {
        super(context);

        this.one = one;
        this.two = two;
        this.three = three;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);
        arabic = findViewById(R.id.arabic);
        bangla = findViewById(R.id.bangla);
        bangla2 = findViewById(R.id.bangla2);

        arabic.setText(one);
        bangla.setText(two);
        bangla2.setText(three);
    }

    @Override
    public void onClick(View v) {

    }
}
