package sv.edu.ues.dam135.evaluacionpractica1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnSalir = findViewById(R.id.btnSalir);

        btnSalir.setOnClickListener(v -> {
            // Confirmar si realmente quiere salir de la aplicacion
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Salir")
                    .setMessage("¿Deseas salir de la aplicación?")
                    .setPositiveButton("Sí", (dialog, which) -> finishAffinity())
                    .setNegativeButton("No", null)
                    .show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_register) {
            Intent intent = new Intent(MainActivity.this, RegistrarActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.menu_salir) {
            finishAffinity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}