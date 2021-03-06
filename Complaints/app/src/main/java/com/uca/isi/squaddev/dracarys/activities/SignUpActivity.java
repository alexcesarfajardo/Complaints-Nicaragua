package com.uca.isi.squaddev.dracarys.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tumblr.remember.Remember;
import com.uca.isi.squaddev.dracarys.MainActivity;
import com.uca.isi.squaddev.dracarys.R;
import com.uca.isi.squaddev.dracarys.api.Api;
import com.uca.isi.squaddev.dracarys.models.AccessToken;
import com.uca.isi.squaddev.dracarys.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private EditText username;
    private EditText email;
    private EditText password;
    private Button signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initViews();
        initActions();
    }


    private void initViews(){
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        signUp = (Button) findViewById(R.id.signUpButton);
    }

    private void initActions(){
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    private void signUp() {
        if(username.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese su nombre de usuario", Toast.LENGTH_SHORT).show();
        } else if (email.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Ingrese un correo electrónico", Toast.LENGTH_LONG).show();
        } else if(password.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Ingrese su contraseña", Toast.LENGTH_LONG).show();
        } else {
            // iinstancia de usuario
            User user = new User();
            user.setUsername(username.getText().toString());
            user.setEmail(email.getText().toString());
            user.setPassword(password.getText().toString());

            // call
            Call<User> call = Api.instance().signUp(user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.body() != null) {
                        Toast.makeText(SignUpActivity.this, "Usuario creado", Toast.LENGTH_SHORT).show();
                        loginRequest(email.getText().toString(), password.getText().toString());
                    } else {
                        Log.i("Respuesta nula", "TRUE");
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
        }
    }

    private void loginRequest(String email, String password) {
        // instanciar usuario
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        // realizacion de call
        Call<AccessToken> call = Api.instance().login(user);
        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                if (response.body() != null) {
                    Log.i("access_token", response.body().getId());
                    Remember.putString("access_token", response.body().getId(), new Remember.Callback() {
                        @Override
                        public void apply(Boolean success) {
                            if (success) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {

            }
        });
    }
}
