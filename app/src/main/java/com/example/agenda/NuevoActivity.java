package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agenda.db.DbContactos;

public class NuevoActivity extends AppCompatActivity {
    private Button btnGuarda;
    private EditText txtNombre, txtApellido, txtCorreoElectronico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre=findViewById(R.id.txtNombre);
        txtApellido=findViewById(R.id.txtApellido);
        txtCorreoElectronico=findViewById(R.id.txtCorreoElectronico);
        btnGuarda=findViewById(R.id.btnGuarda);
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbContactos dbContactos= new DbContactos(NuevoActivity.this);
                long id= dbContactos.insertarContacto(txtNombre.getText().toString(),txtApellido.getText().toString(),txtCorreoElectronico.getText().toString());
                if (id>0){
                    Toast.makeText(NuevoActivity.this, "Registro Agregado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(NuevoActivity.this, "Error al guardar registro", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void limpiar(){
        txtNombre.setText("");
        txtApellido.setText("");
        txtCorreoElectronico.setText("");
    }
}