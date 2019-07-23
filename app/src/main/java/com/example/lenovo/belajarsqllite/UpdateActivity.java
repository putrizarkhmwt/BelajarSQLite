package com.example.lenovo.belajarsqllite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lenovo.belajarsqllite.db.DBSource;
import com.example.lenovo.belajarsqllite.model.Barang;

public class UpdateActivity extends AppCompatActivity {

    long id;
    String name, brand, price;
    Button btnSubmit, btnDelete;

    EditText edtName, edtBrand, edtPrice;
    TextView tvId;
    DBSource dataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        btnSubmit = findViewById(R.id.btn_simpan);
        btnDelete = findViewById(R.id.btn_hapus);
        tvId = findViewById(R.id.tv_id_barang);
        edtName = findViewById(R.id.edt_name);
        edtBrand = findViewById(R.id.edt_brand);
        edtPrice = findViewById(R.id.edt_price);

        dataSource = new DBSource(this);
        dataSource.open();

        Bundle bun = this.getIntent().getExtras();

        id = bun.getLong("id");
        name = bun.getString("name");
        brand = bun.getString("brand");
        price = bun.getString("price");

        tvId.setText(""+id);
        edtName.setText(name);
        edtBrand.setText(brand);
        edtPrice.setText(price);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateData(id);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteData(id);
            }
        });
    }

    private void DeleteData(long id) {
        dataSource.deleteBarang(id);

        Intent intent = new Intent(UpdateActivity.this, ReadActivity.class);
        startActivity(intent);
        UpdateActivity.this.finish();
    }

    private void UpdateData(long id) {
        Barang barang = new Barang();

        barang.setName(edtName.getText().toString().trim());
        barang.setPrice(edtPrice.getText().toString().trim());
        barang.setBrand(edtBrand.getText().toString().trim());
        barang.setId(id);

        dataSource.updateBarang(barang);

        Intent intent = new Intent(UpdateActivity.this, ReadActivity.class);
        startActivity(intent);
        UpdateActivity.this.finish();
    }

    @Override
    protected void onStop(){
        super.onStop();
        dataSource.close();
    }
}
