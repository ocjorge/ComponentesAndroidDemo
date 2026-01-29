package com.example.componentesandroiddemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<String> requestContactsPermission;
    private ActivityResultLauncher<String> requestNotificationsPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // --- Permisos runtime ---
        requestContactsPermission = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) readOneContact();
                    else Toast.makeText(this, "Permiso de contactos denegado", Toast.LENGTH_SHORT).show();
                }
        );

        requestNotificationsPermission = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) Toast.makeText(this, "Permiso de notificaciones concedido", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(this, "Sin permiso de notificaciones: puede no verse el Foreground Service", Toast.LENGTH_LONG).show();
                }
        );

        // Pedir permiso de notificaciones en Android 13+ (API 33+)
        if (Build.VERSION.SDK_INT >= 33) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                requestNotificationsPermission.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }

        // --- Botón 1: Activity ---
        findViewById(R.id.btnActivity).setOnClickListener(v -> {
            Toast.makeText(this, "Abriendo DetailActivity...", Toast.LENGTH_SHORT).show();
            try {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("PARAMETRO_ID", 101);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "Error al abrir DetailActivity: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // --- Botón 2: Iniciar Service ---
        findViewById(R.id.btnService).setOnClickListener(v -> {
            Toast.makeText(this, "Iniciando Foreground Service...", Toast.LENGTH_SHORT).show();
            Intent serviceIntent = new Intent(this, MyForegroundService.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) startForegroundService(serviceIntent);
            else startService(serviceIntent);
        });

        // --- Botón 2b: Detener Service ---
        findViewById(R.id.btnStopService).setOnClickListener(v -> {
            boolean stopped = stopService(new Intent(this, MyForegroundService.class));
            Toast.makeText(this, stopped ? "Service detenido" : "Service no estaba corriendo", Toast.LENGTH_SHORT).show();
        });

        // --- Botón 4: Contacts Provider ---
        findViewById(R.id.btnContacts).setOnClickListener(v -> {
            Toast.makeText(this, "Leyendo contactos...", Toast.LENGTH_SHORT).show();
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                    == PackageManager.PERMISSION_GRANTED) {
                readOneContact();
            } else {
                requestContactsPermission.launch(Manifest.permission.READ_CONTACTS);
            }
        });

        // --- Botón 5: Share intent ---
        findViewById(R.id.btnShare).setOnClickListener(v -> {
            Toast.makeText(this, "Abriendo menú de compartir...", Toast.LENGTH_SHORT).show();
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Este es mi mensaje compartido.");
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, "Compartir usando:"));
        });
    }

    private void readOneContact() {
        Cursor cursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME},
                null, null, null
        );

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int idx = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                String name = cursor.getString(idx);
                Toast.makeText(this, "Primer contacto: " + name, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "No hay contactos (o el emulador está vacío)", Toast.LENGTH_LONG).show();
            }
            cursor.close();
        } else {
            Toast.makeText(this, "Cursor null (no se pudo consultar)", Toast.LENGTH_LONG).show();
        }
    }
}
