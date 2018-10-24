package com.digitalhouse.viewpagerapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.digitalhouse.viewpagerapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorFragment extends Fragment {

    private static final String TITULO = "TITULO";
    private static final String COR = "COR";

    public ColorFragment() {
        // Required empty public constructor
    }

    public static ColorFragment getInstance(String titulo, int cor){
        ColorFragment fragment = new ColorFragment();

        Bundle bundle = new Bundle();

        bundle.putString(TITULO, titulo);
        bundle.putInt(COR, cor);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_color, container, false);

        Bundle bundle = getArguments();

        String titulo = bundle.getString(TITULO);
        int cor = bundle.getInt(COR);

        TextView textViewTitulo = view.findViewById(R.id.fragment_titulo_id);
        textViewTitulo.setText(titulo);

        FrameLayout frameLayoutBackground = view.findViewById(R.id.fragment_background_id);
        frameLayoutBackground.setBackgroundColor(getResources().getColor(cor));

        return view;
    }

}
