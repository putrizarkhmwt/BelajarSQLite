package com.example.lenovo.belajarsqllite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.belajarsqllite.db.DBSource;
import com.example.lenovo.belajarsqllite.model.Barang;

public class AddActivity extends AppCompatActivity {

    Button btnSimpan;

    EditText edtName, edtBrand, edtPrice;
    DBSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnSimpan = findViewById(R.id.btn_simpan);

        edtName = findViewById(R.id.edt_name);
        edtBrand = findViewById(R.id.edt_brand);
        edtPrice = findViewById(R.id.edt_price);

        dataSource = new DBSource(this);

        dataSource.open();

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahData();
            }
        });
    }

    private void tambahData() {
        String name = null;
        String brand = null;
        String price = null;

        Barang barang = null;
        if (edtName.getText() != null && edtBrand.getText() != null && edtPrice.getText() != null) {
            name = edtName.getText().toString().trim();
            brand = edtBrand.getText().toString().trim();
            price = edtPrice.getText().toString().trim();
        }
        barang = dataSource.createBarang(name, brand, price);

        Toast.makeText(this, "Data Berhasil Disimpan", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(AddActivity.this, ReadActivity.class);
        startActivity(intent);
        AddActivity.this.finish();
    }

    @Override
    protected void onStop(){
        super.onStop();
        dataSource.close();
    }
}
