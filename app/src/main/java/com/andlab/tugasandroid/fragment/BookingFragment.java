package com.andlab.tugasandroid.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andlab.tugasandroid.R;

public class BookingFragment extends Fragment {

    private static final String TAG = BookingFragment.class.getSimpleName();

    public BookingFragment(){
        // Required empty public constructor
    }

    public static BookingFragment newInstance(String param1, String param2){
        BookingFragment fragment = new BookingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_booking, container, false);
    }
}

