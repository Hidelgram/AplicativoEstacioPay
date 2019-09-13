package com.aplicativo.aplicativoestaciopay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView email, senha;
    private TextView textEsqueceuSenha;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        email = findViewById(R.id.txtEmail);
        senha= findViewById(R.id.txtSenha);
        textEsqueceuSenha = findViewById(R.id.textEsqueceuSenha);

      btnLogin.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              RequestQueue rq = Volley.newRequestQueue(MainActivity.this);
              String urlWebService = ""+"http://10.87.107.36/WebServiceEstacioPay/EstacioPay.asmx/Logar";


              StringRequest sr = new StringRequest(Request.Method.POST, urlWebService, new  Response.Listener<String>() {

                  @Override
                  public void onResponse(String resposta) {

                      if (resposta.equals("true")){

                          Intent i = new Intent(getApplicationContext(), TelaCadastro.class);
                          startActivity(i);

                          Toast.makeText(MainActivity.this, "Resposta TRUE", Toast.LENGTH_LONG).show();
                      }else {
                          Toast.makeText(MainActivity.this, "Resposta FALSE", Toast.LENGTH_LONG).show();
                      }
                    }


                  },new Response.ErrorListener() {
                      @Override
                      public void onErrorResponse(VolleyError error) {
                          Toast.makeText(MainActivity.this, "Erro", Toast.LENGTH_LONG).show();
                      }

                  }){
                      protected Map<String,String> getParams(){

                          Map<String, String> parametro = new HashMap<String, String>();

                          parametro.put("email", email.getText().toString());
                          parametro.put("senha", senha.getText().toString());

                          return parametro;

                      }
                  };

                rq.add(sr);


              }
      });


    }
}
