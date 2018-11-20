package com.example.jorda.experimentoretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        ModeloApi apiService = retrofit.create(ModeloApi.class);
        Call<Modelo> call = apiService.getModelo(1);

        call.enqueue(new Callback<Modelo>() {
            @Override
            public void onResponse(Call<Modelo> call, Response<Modelo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    writeViewData(Objects.requireNonNull(response.body()));

                }
            }

            @Override
            public void onFailure(Call<Modelo> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void writeViewData(Modelo modelo) {
        mUserIdTextView.setText(modelo.getUserId().toString());
        mIdTextView.setText(Integer.toString(modelo.getId()));
        mBodyTextView.setText(modelo.getBody());
        mTitleTextView.setText(modelo.getTitle());
    }

    private void initViews() {

        mUserIdTextView = findViewById(R.id.user_id);
        mIdTextView = findViewById(R.id.id);
        mTitleTextView = findViewById(R.id.title);
        mBodyTextView = findViewById(R.id.body);
    }
}
