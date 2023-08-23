package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agenda.db.DbContactos;

public class NuevoActivity extends AppCompatActivity {
    private Button btnGuarda;
    private EditText txtNombre, txtTelefono, txtCorreoElectronico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre=findViewById(R.id.txtNombre);
        txtTelefono=findViewById(R.id.txtTelefono);
        txtCorreoElectronico=findViewById(R.id.txtCorreoElectronico);
        btnGuarda=findViewById(R.id.btnGuarda);
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbContactos dbContactos= new DbContactos(NuevoActivity.this);
                long id= dbContactos.insertarContacto(txtNombre.getText().toString(),txtTelefono.getText().toString(),txtCorreoElectronico.getText().toString());
                if (id>0){
                    Toast.makeText(NuevoActivity.this, "Registro Agregado", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(NuevoActivity.this, MainActivity.class);
                    startActivity(intent);
                    limpiar();
                }else{
                    Toast.makeText(NuevoActivity.this, "Error al guardar registro", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void limpiar(){
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreoElectronico.setText("");
    }
}