package com.example.samsung.gp;

import android.media.Image;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginActivityFragment extends Fragment {

    private EditText Password;
    private EditText Email;
    private Button Sign_in_button ;
    private Image Facebook;
    private Image Twitter;
    private Image Gmail;

    public LoginActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_login_activity, container, false);

        Email=(EditText)rootView.findViewById(R.id.email);
        Password=(EditText)rootView.findViewById(R.id.password);
        Sign_in_button=(Button)rootView.findViewById(R.id.email_sign_in_button);
        return rootView;

    }
}
