package com.example.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.db.DbContactos;
import com.example.agenda.entidades.Contactos;

public class EditarActivity extends AppCompatActivity {

    EditText txtNombre,txtTelefono, txtCorreoElectronico;
    Button btnGuarda;
    Contactos contacto;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre=findViewById(R.id.txtNombre);
        txtTelefono=findViewById(R.id.txtTelefono);
        txtCorreoElectronico=findViewById(R.id.txtCorreoElectronico);
        btnGuarda=findViewById(R.id.btnGuarda);

        if (savedInstanceState==null){
            Bundle extras = getIntent().getExtras();
            if (extras==null){
                id=Integer.parseInt(null);
            }else{
                id=extras.getInt("ID");
            }
        }else{
            id=(int) savedInstanceState.getSerializable("ID");
        }

        DbContactos dbContactos=new DbContactos(EditarActivity.this);
        contacto= dbContactos.verContacto(id);

        if (contacto!=null){
            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(contacto.getTelefono());
            txtCorreoElectronico.setText(contacto.getCorreo());
        }

    btnGuarda.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("") && !txtCorreoElectronico.getText().toString().equals("")){
               boolean correcto= dbContactos.editarContacto(id,txtNombre.getText().toString(), txtTelefono.getText().toString(), txtCorreoElectronico.getText().toString());
                if (correcto){
                    Intent intent =new Intent(EditarActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(EditarActivity.this, "REGISTRO ACTUALIZADO", Toast.LENGTH_SHORT).show();
                    verRegistro();
                }else{
                    Toast.makeText(EditarActivity.this, "ERRROR AL ACTUALIZAR", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(EditarActivity.this, "SE DEBEN LLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
            }
        }
    });
    }

    private void verRegistro(){
        Intent intent=new Intent(this, VerActivity.class);
    }
}
