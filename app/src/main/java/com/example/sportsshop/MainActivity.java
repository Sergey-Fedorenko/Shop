package com.example.sportsshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int quantity = 0;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;
    String goodsName;
    double price;
    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = findViewById(R.id.editTextTextPersonName2);

        //Вызов методов
        createSpinner();
        createMap();
    }

    //Создание spinner
    void createSpinner() {
        spinner =  findViewById(R.id.Spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();

        spinnerArrayList.add("Персональный");
        spinnerArrayList.add("Офисный");
        spinnerArrayList.add("Домашний");
        spinnerArrayList.add("Игровой");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    //Создание HashMap
    void createMap() {
        goodsMap = new HashMap();
        goodsMap.put("Персональный", 2000.0);
        goodsMap.put("Офисный", 1000.0);
        goodsMap.put("Домашний", 4000.0);
        goodsMap.put("Игровой", 100000.0);
    }

    //Кол-во минус
    public void ButtonOnClickMinus(View view) {
        quantity --;

        if (quantity < 0) {
            quantity = 0;
        }
        TextView textview = findViewById(R.id.priseO);
        textview.setText(Integer.toString(quantity));
        TextView priceTextView = findViewById(R.id.priseTextView);
        priceTextView.setText("" + quantity * price);
    }

    //Кол-во плюс
    public void ButtonOnClickPlus(View view) {
        quantity ++;
        TextView textview = findViewById(R.id.priseO);
        textview.setText(Integer.toString(quantity));
        TextView priceTextView = findViewById(R.id.priseTextView);
        priceTextView.setText("" + quantity * price);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double)goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.priseTextView);
        priceTextView.setText("" + quantity * price);

        ImageView goodsImageView = findViewById(R.id.goodsImageView);

       /* if (goodsName.equals("Персональный")) {
            goodsImageView.setImageResource(R.drawable.pc_icon);
        } else if (goodsName.equals("Офисный")) {
            goodsImageView.setImageResource(R.drawable.pc_icon_offic);
        } else if (goodsName.equals("Домашний")) {
            goodsImageView.setImageResource(R.drawable.pc_home);
        } else if (goodsName.equals("Игровой")) {
            goodsImageView.setImageResource(R.drawable.pc_game);
        }*/

        //Совпадение названия с картинкой
        switch (goodsName) {
            case "Персональный":
                goodsImageView.setImageResource(R.drawable.pc_icon);
                break;
            case "Офисный":
                goodsImageView.setImageResource(R.drawable.pc_icon_offic);
                break;
            case "Домашний":
                goodsImageView.setImageResource(R.drawable.pc_home);
                break;
            case "Игровой":
                goodsImageView.setImageResource(R.drawable.pc_game);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.pc_icon);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {

        Order order = new Order();

        order.userName = userNameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = quantity;
        order.orderPrice = quantity * price;
        order.price = price;

        //Переход на новое Activity и отображение на нём информации
        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);

        orderIntent.putExtra("userNameForIntent", order.userName);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("orderPrice", order.orderPrice);
        orderIntent.putExtra("price", order.price);

        startActivity(orderIntent);
    }
}