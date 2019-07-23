package com.example.lenovo.belajarsqllite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.lenovo.belajarsqllite.db.DBSource;
import com.example.lenovo.belajarsqllite.model.Barang;

import java.util.ArrayList;

public class ReadActivity extends AppCompatActivity {

    ListView lvBarang;

    private DBSource dataSource;
    private ArrayList<Barang> values;

    private Button editButton;
    private Button deleteButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        lvBarang = findViewById(R.id.lv_barang);

        dataSource = new DBSource(this);

        dataSource.open();
        values = dataSource.getAllBarang();
        ArrayAdapter<Barang> adapter = new ArrayAdapter<Barang>(this,
                android.R.layout.simple_list_item_1, values);

        lvBarang.setAdapter(adapter);

        lvBarang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Barang barang = (Barang) parent.getAdapter().getItem(position);
                switchToGetData(barang.getId());
            }
        });
    }

    private void switchToGetData(long id) {
        Barang b = dataSource.getBarang(id);
        Intent i = new Intent(ReadActivity.this, UpdateActivity.class);
        Bundle bun = new Bundle();
        bun.putLong("id", b.getId());
        bun.putString("name", b.getName());
        bun.putString("brand", b.getBrand());
        bun.putString("price", b.getPrice());
        i.putExtras(bun);
        ReadActivity.this.finish();
        startActivity(i);
    }
}
