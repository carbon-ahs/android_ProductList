package com.axiagroups.productlist;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewProductActivity extends AppCompatActivity {
    public static final String EXTRA_REPLAY_PRODUCT_NAME = "com.axiagroups.productlist.productName";
    public static final String EXTRA_REPLAY_PRODUCT_PRICE = "com.axiagroups.productlist.productPrice";
    private EditText productNameEt, productPriceEt;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        productNameEt = findViewById(R.id.productNameEt);
        productPriceEt = findViewById(R.id.productPriceEt);
        saveBtn = findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(productNameEt.getText()) || TextUtils.isEmpty(productPriceEt.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                }
                else {
                    String productName = productNameEt.getText().toString();
                    String productPrice = productPriceEt.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLAY_PRODUCT_NAME, productName);
                    replyIntent.putExtra(EXTRA_REPLAY_PRODUCT_PRICE, productPrice);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });

    }
}