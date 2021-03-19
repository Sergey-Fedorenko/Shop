package com.example.sportsshop;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    String[] addresses = {"fediyaa1@mail.ru"};
    String subject = "Order from SportShop";
    String emailText;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent receivedOIntent = getIntent();
        String userName = receivedOIntent.getStringExtra("userNameForIntent");
        String goodsName = receivedOIntent.getStringExtra("goodsName");
        int  quantity = receivedOIntent.getIntExtra("quantity",0);
        double orderPrice = receivedOIntent.getDoubleExtra("orderPrice", 0);
        double price = receivedOIntent.getDoubleExtra("price", 0);

        emailText = "Имя: " + userName + "\n" +
                "Название товара: " + goodsName + "\n" +
                "Колличество: " + quantity + "\n" +
                "Цена за 1 товар: " + price + "\n" +
                "Итоговая цена: " + orderPrice;

        TextView orderTextView = findViewById(R.id.orderText);
        orderTextView.setText(emailText);
    }

    //Передача по email
    public void sumbitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}