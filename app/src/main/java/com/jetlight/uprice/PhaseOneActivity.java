package com.jetlight.uprice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jetlight.uprice.Models.Product;

import java.util.ArrayList;
import java.util.Random;

public class PhaseOneActivity extends AppCompatActivity {

    ArrayList<Product> products =new ArrayList<>();
    ImageView imageView;
    TextView textView;
    int productPrice;
    EditText editText;
    int [] playersPrices;
    static int j=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phase_one);
        imageView = (ImageView)findViewById(R.id.ImageViewproductImage);
        textView = (TextView)findViewById(R.id.TextViewproductName);
        editText = (EditText)findViewById(R.id.EditTextproductPrice);
        Intent intent = getIntent();
        products.add(new Product("OMO 250 KG",65, R.mipmap.omo));
        products.add(new Product("ARIEL 250 KG",44,R.mipmap.ariel));
        playersPrices = new int[intent.getIntExtra("playersCount",0)];
        settingInfo();
    }

    void settingInfo(){
        int randomIndex= new Random().nextInt(products.size());
        Product product = products.get(randomIndex);
        imageView.setImageDrawable(getResources().getDrawable(product.getImageLink()));
        textView.setText(product.getName());
        productPrice = product.getPrice();
        products.remove(randomIndex);
    }

    public void submitPrice(View view) {
        playersPrices[j]=Integer.parseInt(editText.getText().toString());
        editText.setText("");
        passTurn();
    }

    public void SecondActivityTransaction(View v)
    {
        Intent intent = new Intent(this,PhaseTwoActivity.class);
        startActivity(intent);
    }

    private void passTurn() {
        j++;
        if(j==playersPrices.length){
            j=0;
            winner();
        }
    }

    private void winner() {
        settingInfo();
    }
}
