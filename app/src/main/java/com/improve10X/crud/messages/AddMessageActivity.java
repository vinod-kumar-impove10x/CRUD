package com.improve10X.crud.messages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.improve10X.crud.CrudApi;
import com.improve10X.crud.CrudService;
import com.improve10X.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMessageActivity extends AppCompatActivity {

    private CrudService crudService;
    private Button addBtn;
    private EditText nameTxt;
    private EditText phoneNumberTxt;
    private EditText messageTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);
        getSupportActionBar().setTitle("Add Message");
        setupViews();
        handleAdd();
        setupApiService();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void handleAdd() {
        addBtn.setOnClickListener(view -> {
            // Get data from edit text components. name, phoneNumber, messageText.
            String nameText = nameTxt.getText().toString();
            String phoneText = phoneNumberTxt.getText().toString();
            String messageText = messageTxt.getText().toString();
            //create message object using name, phoneNumber, messageTxt.
            Message message = createMessage(nameText,phoneText,messageText);
            //save message in server
            saveMessage(message);
        });
    }

    private void saveMessage(Message message) {
        Call<Message> call = crudService.createMessage(message);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                showToast("SuccessFully loaded");
                finish();
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                showToast("Failed to get loaded");

            }
        });
    }

    private Message createMessage(String nameText, String phoneText, String messageText) {
        Message message = new Message();
        message.name = nameText;
        message.number = phoneText;
        message.messageText = messageText;


        CrudApi crudApi = new CrudApi();
        CrudService crudService = crudApi.createCrudService();

        return message;

    }

    private void setupViews() {
        addBtn = findViewById(R.id.add_btn);
        nameTxt = findViewById(R.id.name_txt);
        phoneNumberTxt = findViewById(R.id.phone_txt);
        messageTxt = findViewById(R.id.message_txt);
    }
}