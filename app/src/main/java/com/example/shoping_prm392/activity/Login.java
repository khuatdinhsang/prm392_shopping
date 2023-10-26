package com.example.shoping_prm392.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoping_prm392.MainActivity;
import com.example.shoping_prm392.R;
import com.example.shoping_prm392.model.Account;
import com.example.shoping_prm392.utils.Utils;

import java.io.Serializable;
import java.util.List;

public class Login extends AppCompatActivity  {
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnRegister;
    private TextView textRegister;
    private List<Account> listAccount;
    private Utils utils= new Utils();
    private Account currentAccount;
    private void bindingView(){
        edtEmail=findViewById(R.id.login_edtEmail);
        edtPassword=findViewById(R.id.login_edtPassword);
        btnRegister= findViewById(R.id.login_btnLogin);
        textRegister= findViewById(R.id.login_textRegister);
    }
    private void bindingAction(){
        btnRegister.setOnClickListener(this::loginAccount);
        textRegister.setOnClickListener(this::registerAccount);
    }

    private void registerAccount(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
    private void loginAccount(View view) {
        String email=edtEmail.getText().toString().trim();
        String password=edtPassword.getText().toString().trim();
        boolean check=false;
        for(int i=0; i< listAccount.size();i++){
            if(listAccount.get(i).getEmail().equals(email) && listAccount.get(i).getPassword().equals(password)){
                check=true;
               currentAccount=listAccount.get(i);
            }
        }
        if(check){
            Toast.makeText(Login.this, "Login successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            Log.i("ac",currentAccount.toString());
            intent.putExtra("currentAccount", currentAccount);
            startActivity(intent);
        } else{
            Toast.makeText(Login.this, "Wrong email or password!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        listAccount= utils.getListAccount();

        bindingView();
        bindingAction();
    }
}