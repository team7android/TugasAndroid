package com.andlab.tugasandroid.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.andlab.tugasandroid.LoginActivity;
import com.andlab.tugasandroid.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    Button btnLogout;
    SharedPreferences sharedpreferences;
    public static final String my_shared_preferences = "my_shared_preferences";
    String id_user, nama_user, username, no_hp, alamat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedpreferences = getActivity().getSharedPreferences(my_shared_preferences, MODE_PRIVATE);
//
        id_user = sharedpreferences.getString("id_user", "");
        username = sharedpreferences.getString("username", "");
        nama_user = sharedpreferences.getString("nama_user", "");
        no_hp = sharedpreferences.getString("no_hp", "");
        alamat = sharedpreferences.getString("alamat", "");



        Toast.makeText(getActivity(), username, Toast.LENGTH_LONG).show();

        btnLogout = getActivity().findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putBoolean(LoginActivity.session_status, false);
                    editor.putString(id_user, null);
                    editor.putString(username, null);
                    editor.putString(nama_user, null);
                    editor.putString(no_hp, null);
                    editor.putString(alamat, null);
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
