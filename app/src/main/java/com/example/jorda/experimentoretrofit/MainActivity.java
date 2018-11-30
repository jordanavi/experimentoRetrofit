package com.example.jorda.experimentoretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jorda.experimentoretrofit.domain.Modelo;
import com.example.jorda.experimentoretrofit.service.ModeloApi;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mUserIdTextView;
    private TextView mIdTextView;
    private TextView mTitleTextView;
    private TextView mBodyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        Call<Modelo> call = getModeloCall(1);

        call.enqueue(new Callback<Modelo>() {
            @Override
            public void onResponse(Call<Modelo> call, Response<Modelo> response) {
                Modelo obj = response.body();
                //Log.i("teste","O titulo cujo id é 1 = "+obj.getTitle());
                mTitleTextView.setText(obj.getTitle());
               // mIdTextView.setText(obj.getId());
                //mUserIdTextView.setText(obj.getUserId());
            }

            @Override
            public void onFailure(Call<Modelo> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Call<List<Modelo>> call2 = getModeloCall2();

        call2.enqueue(new Callback<List<Modelo>>() {
            @Override
            public void onResponse(Call<List<Modelo>> call, Response<List<Modelo>> response) {
                List<Modelo> lista = response.body();
                Log.i("teste","tamanho da lista = "+lista.size());
            }

            @Override
            public void onFailure(Call<List<Modelo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Call<Modelo> getModeloCall(int i) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        ModeloApi apiService = retrofit.create(ModeloApi.class);
        return apiService.getModelo(i);
    }

    private Call<List<Modelo>> getModeloCall2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        ModeloApi apiService = retrofit.create(ModeloApi.class);
        return apiService.getTodosModelo();
    }

    private void initViews() {
        mUserIdTextView = findViewById(R.id.user_id);
        mIdTextView = findViewById(R.id.id);
        mTitleTextView = findViewById(R.id.title);
        mBodyTextView = findViewById(R.id.body);
    }
}
