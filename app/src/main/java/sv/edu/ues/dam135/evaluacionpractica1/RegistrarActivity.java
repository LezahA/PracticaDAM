package sv.edu.ues.dam135.evaluacionpractica1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistrarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnRegresar = findViewById(R.id.btnRegresar);

        btnRegresar.setOnClickListener(v -> {
            finish(); // Regresa a main
        });

        EditText edtNombre = findViewById(R.id.edtNombreUsuario);
        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtPassword = findViewById(R.id.edtPassword);
        EditText edtConfirmarPassword = findViewById(R.id.edtConfirmarPassword);

        Button btnRegistrar = findViewById(R.id.btnGuardar);

        btnRegistrar.setOnClickListener(v -> {
            String nombre = edtNombre.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();
            String confirmarPassword = edtConfirmarPassword.getText().toString().trim();

            boolean hayErrores = false;

            if (nombre.isEmpty()) {
                edtNombre.setError("Ingrese un nombre");
                hayErrores = true;
            } else if (nombre.length() < 4) {
                edtNombre.setError("El nombre debe tener al menos 4 caracteres");
                hayErrores = true;
            }

            if (email.isEmpty()) {
                edtEmail.setError("Ingrese un correo");
                hayErrores = true;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                edtEmail.setError("Correo inválido");
                hayErrores = true;
            }

            if (password.isEmpty()) {
                edtPassword.setError("Ingrese una contraseña");
                hayErrores = true;
            } else if (password.length() < 5 ){
                edtPassword.setError("Debe contener al menos 5 caracteres");
                hayErrores = true;
            } else if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$")) {
                edtPassword.setError("Debe contener letras y números");
                hayErrores = true;
            }

            if (confirmarPassword.isEmpty()) {
                edtConfirmarPassword.setError("Confirme su contraseña");
                hayErrores = true;
            } else if (!password.equals(confirmarPassword)) {
                edtConfirmarPassword.setError("Las contraseñas no coinciden");
                hayErrores = true;
            }

            if (hayErrores) {
                Toast.makeText(this, "Corrige los errores antes de continuar", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences prefs = getSharedPreferences("usuarios", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            editor.putString("nombre", nombre);
            editor.putString("email", email);
            editor.putString("password", password);
            editor.apply();

            Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

}