package com.andlab.tugasandroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andlab.tugasandroid.Api.BaseApiService;
import com.andlab.tugasandroid.Api.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by greatsoft on 09/11/18.
 */

public class LoginActivity extends AppCompatActivity {
    EditText etUsername;
    EditText etPasswd;
    Button btnLogin;
    ProgressDialog loading;

    public static final String TAG_ID_USER = "id_user";
    public static final String TAG_USERNAME = "username";
    public static final String TAG_NAMA_USER= "nama_user";
    public static final String TAG_NO_HP = "no_hp";
    public static final String TAG_ALAMAT = "alamat";


    SharedPreferences sharedpreferences;
    Boolean session = false;
    String id_user, username, nama_user, no_hp, alamat;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";

    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = this;
        mApiService = UtilsApi.getAPIService();
        etUsername = (EditText) findViewById(R.id.etEmail);
        etPasswd = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        sharedpreferences = LoginActivity.this.getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        id_user = sharedpreferences.getString(TAG_ID_USER, null);
        username = sharedpreferences.getString(TAG_USERNAME, null);
        nama_user = sharedpreferences.getString(TAG_NAMA_USER, null);
        no_hp = sharedpreferences.getString(TAG_NO_HP, null);
        alamat = sharedpreferences.getString(TAG_ALAMAT, null);

        if(session){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra(TAG_ID_USER, id_user);
            intent.putExtra(TAG_USERNAME, username);
            intent.putExtra(TAG_NAMA_USER, nama_user);
            intent.putExtra(TAG_NO_HP, no_hp);
            intent.putExtra(TAG_ALAMAT, alamat);
            intent.putExtra(TAG_ID_USER, id_user);
            startActivity(intent);
            LoginActivity.this.finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                requestLogin();
            }
        });
    }

    private void requestLogin(){
        mApiService.loginRequest(etUsername.getText().toString(), etPasswd.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("success").equals("1")){
                                    // Jika login berhasil maka data nama yang ada di response API
                                    // akan diparsing ke activity selanjutnya.
                                    Toast.makeText(mContext, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
                                    String id_user = jsonRESULTS.getString("id_user");
                                    String username = jsonRESULTS.getString("username");
                                    String nama_user = jsonRESULTS.getString("nama_user");
                                    String no_hp = jsonRESULTS.getString("no_hp");
                                    String alamat = jsonRESULTS.getString("alamat");
                                    Intent intent = new Intent(mContext, MainActivity.class);

                                    //menyimpan login ke session
                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.putBoolean(session_status, true);
                                    editor.putString(TAG_ID_USER, id_user);
                                    editor.putString(TAG_NAMA_USER, nama_user);
                                    editor.putString(TAG_USERNAME, username);
                                    editor.putString(TAG_NO_HP, no_hp);
                                    editor.putString(TAG_ALAMAT, alamat);
                                    editor.commit();

                                    //Memanggil main Activity
                                    intent.putExtra("TAG_ID_USER", id_user);
                                    intent.putExtra("TAG_USERNAME", username);
                                    intent.putExtra("TAG_NAMA_USER", nama_user);
                                    intent.putExtra("TAG_NO_HP", no_hp);
                                    intent.putExtra("TAG_ALAMAT", alamat);
                                    startActivity(intent);
                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("message");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                    }
                });
    }
}
