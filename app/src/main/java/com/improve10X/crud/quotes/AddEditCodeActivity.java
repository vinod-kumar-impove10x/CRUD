package com.improve10X.crud.quotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.improve10X.crud.R;
import com.improve10X.crud.base.BaseActivity;

public class AddEditCodeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_code);
        getSupportActionBar().setTitle("Add Quote");
    }
}