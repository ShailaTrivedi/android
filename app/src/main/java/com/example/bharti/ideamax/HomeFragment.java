package com.example.bharti.ideamax;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewFlipper;



/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment{

    View view;
    Animation fade_in, fade_out;
    ViewFlipper viewFlipper,viewFlipper1;
    Button button;

    public HomeFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewFlipper = (ViewFlipper) view.findViewById(R.id.viewflipper);

        fade_in = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
        fade_out = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out);

        viewFlipper.setInAnimation(fade_in);
        viewFlipper.setOutAnimation(fade_out);
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.startFlipping();

        return view;
    }
}
