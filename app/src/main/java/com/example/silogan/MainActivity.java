package com.example.silogan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, description, price, quantity, customer, date;
    Button insertData, updateData, deleteData, viewData, searchData;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.product_name);
        description = findViewById(R.id.product_description);
        price = findViewById(R.id.product_price);
        quantity = findViewById(R.id.product_quantity);
        customer = findViewById(R.id.product_customer);
        date = findViewById(R.id.product_date);

        insertData = findViewById(R.id.insertDataButton);
        updateData = findViewById(R.id.updateDataButton);
        deleteData = findViewById(R.id.deletesearchDataButton);
        viewData = findViewById(R.id.searchDataButton);


        DB = new DBHelper(this);
        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String descriptionTXT = description.getText().toString();
                String priceTXT = price.getText().toString();
                String quantityTXT = quantity.getText().toString();
                String customerTXT = customer.getText().toString();
                String dateTXT = date.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTXT, descriptionTXT, priceTXT, quantityTXT, customerTXT, dateTXT);
                if(checkinsertdata)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });


        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String descriptionTXT = description.getText().toString();
                String priceTXT = price.getText().toString();
                String quantityTXT = quantity.getText().toString();
                String customerTXT = customer.getText().toString();
                String dateTXT = date.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTXT, descriptionTXT, priceTXT, quantityTXT, customerTXT, dateTXT);

                if(checkupdatedata)
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });


        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });


        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID :"+res.getString(0)+"\n");
                    buffer.append("Name :"+res.getString(1)+"\n");
                    buffer.append("Product Description :"+res.getString(2)+"\n");
                    buffer.append("Product Price :"+res.getString(3)+"\n");
                    buffer.append("Quantity :"+res.getString(4)+"\n");
                    buffer.append("Customer's Name :"+res.getString(5)+"\n");
                    buffer.append("Date :"+res.getString(6)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Silogan Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }}

