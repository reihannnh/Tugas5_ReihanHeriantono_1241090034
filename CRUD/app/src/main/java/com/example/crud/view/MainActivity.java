package com.example.crud.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.crud.R;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.entity.AppDatabase;
import com.example.crud.entity.DataPengguna;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContact.view{
    private AppDatabase appDatabase;
    private MainPresenter mainPresenter;
    private MainAdapter mainAdapter;

    private Button btnSubmit;
    private RecyclerView recyclerView;
    private EditText etName, etBirthday, etAddress, etPhone, etEmail;

    private int id = 0;
    boolean edit = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);
        etName = findViewById(R.id.et_name);
        etBirthday = findViewById(R.id.et_birthday);
        etAddress = findViewById(R.id.et_address);
        etPhone = findViewById(R.id.et_phone);
        etEmail = findViewById(R.id.et_email);
        recyclerView = findViewById(R.id.rc_main);

        appDatabase = AppDatabase.inidb(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mainPresenter = new MainPresenter(this);
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void successAdd() {
        Toast.makeText(this, "data added", Toast.LENGTH_LONG).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void successDelete() {
        Toast.makeText(this, "data deleted", Toast.LENGTH_LONG).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void resetForm() {
        etName.setText("");
        etBirthday.setText("");
        etAddress.setText("");
        etPhone.setText("");
        etEmail.setText("");
        btnSubmit.setText("Submit");
    }

    @Override
    public void getData(List<DataPengguna> list) {
        mainAdapter = new MainAdapter(this,list, this);
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void editData(DataPengguna item) {
        etName.setText(item.getName());
        etBirthday.setText(item.getBirthday());
        etAddress.setText(item.getAddress());
        etPhone.setText(item.getPhone());
        etEmail.setText(item.getEmail());
        id = item.getId();
        edit = true;
        btnSubmit.setText("Edit Data");
    }

    @Override
    public void deleteData(DataPengguna item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        }else{
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("data deleted")
                .setMessage("Do you really sure to delete this data?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resetForm();
                        mainPresenter.deleteData(item,appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_dialer)
                .show();
    }

    @Override
    public void onClick(View view) {
        if (view==btnSubmit){
            if(etName.getText().toString().equals("")||etBirthday.getText().toString().equals("")||etAddress.getText().toString().equals("")||etPhone.getText().toString().equals("")||etEmail.getText().toString().equals("")){
                Toast.makeText(this, "please fulfilled all data!", Toast.LENGTH_SHORT).show();
            }else{
                if(!edit){
                    mainPresenter.insertData(etName.getText().toString(),etBirthday.getText().toString(),etAddress.getText().toString(),etPhone.getText().toString(),etEmail.getText().toString(),appDatabase);
                }else{
                    mainPresenter.editData(etName.getText().toString(),etBirthday.getText().toString(),etAddress.getText().toString(),etPhone.getText().toString(),etEmail.getText().toString(),id,appDatabase);
                    edit = false;
                }
                resetForm();
            }
        }
    }
}