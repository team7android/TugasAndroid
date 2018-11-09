package com.andlab.tugasandroid.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.andlab.tugasandroid.LoginActivity;
import com.andlab.tugasandroid.R;

import static com.andlab.tugasandroid.LoginActivity.TAG_ALAMAT;
import static com.andlab.tugasandroid.LoginActivity.TAG_ID_USER;
import static com.andlab.tugasandroid.LoginActivity.TAG_NAMA_USER;
import static com.andlab.tugasandroid.LoginActivity.TAG_NO_HP;
import static com.andlab.tugasandroid.LoginActivity.TAG_USERNAME;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    Button btnLogout;
    SharedPreferences sharedpreferences;

    String id_user, nama_user, username, no_hp, alamat;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);

        id_user = getActivity().getIntent().getExtras().getString(id_user,TAG_ID_USER);
        username = getActivity().getIntent().getStringExtra(TAG_USERNAME);
        nama_user = getActivity().getIntent().getStringExtra(TAG_NAMA_USER);
        no_hp = getActivity().getIntent().getStringExtra(TAG_NO_HP);
        alamat = getActivity().getIntent().getStringExtra(TAG_ALAMAT);

        Toast.makeText(getActivity(), nama_user, Toast.LENGTH_LONG).show();

        sharedpreferences = getActivity().getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        btnLogout = getActivity().findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putBoolean(LoginActivity.session_status, false);
                    editor.putString(TAG_ID_USER, null);
                    editor.putString(TAG_NAMA_USER, null);
                    editor.putString(TAG_USERNAME, null);
                    editor.putString(TAG_NO_HP, null);
                    editor.putString(TAG_ALAMAT, null);
                    editor.commit();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }


}
