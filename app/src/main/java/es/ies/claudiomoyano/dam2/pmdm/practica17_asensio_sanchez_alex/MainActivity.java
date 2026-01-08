package es.ies.claudiomoyano.dam2.pmdm.practica17_asensio_sanchez_alex;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    Alumno alumnoSeleccionado = null;

    Button botonDelegado;

    Button opcionSelecionada;

    int idNotifications = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        100
                );
            }
        }

        Alumno alumno1 = new Alumno("Alex", "Asensio Sanchez", "12345678Z", R.drawable.i1);
        Alumno alumno2 = new Alumno("Diego", "Tabuyo Rebordinos", "23456789C", R.drawable.i2);
        Alumno alumno3 = new Alumno("Tirso", "Guerrero Fernandez", "34567891V", R.drawable.i3);
        Alumno alumno4 = new Alumno("Natalia", "No me acuerdo", "45678912G", R.drawable.i4);
        Alumno alumno5 = new Alumno("Jorge", "Tabuyo Rebordinos", "56789123E", R.drawable.i5);
        Alumno alumno6 = new Alumno("SinFoto", "Alumno sin foto", "56789123E");

        Alumno[] arraySpinner = {alumno1, alumno2, alumno3, alumno4, alumno5, alumno6};

        Spinner spinnerAlumnos = findViewById(R.id.spinnerAlumnos);

        ArrayAdapter<Alumno> adapter = new ArrayAdapter<Alumno>(this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAlumnos.setAdapter(adapter);



        Button botonDatos = findViewById(R.id.botonDatos);
        Button botonFoto = findViewById(R.id.botonFoto);

        opcionSelecionada=botonDatos;


        botonDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                opcionSelecionada=botonDatos;

                FragmentManager fragmentManager = getSupportFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                FragmentDatos miFragmento = new FragmentDatos();

                fragmentTransaction.replace(R.id.placeholderFragment, miFragmento);

                fragmentTransaction.commitNow();

                miFragmento.mostrarDatosAlumno(alumnoSeleccionado);

                botonDelegado = miFragmento.getBotonDelegado();

                if(!botonDelegado.hasOnClickListeners()){
                    botonDelegado.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView delegado = findViewById(R.id.textViewDelegado);

                            delegado.setText("Delegado: "+alumnoSeleccionado.getNombre());

                            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                            String channelId = "some_channel_id";
                            CharSequence channelName = "Some Channel";
                            int importance = NotificationManager.IMPORTANCE_DEFAULT;

                            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
                            notificationManager.createNotificationChannel(notificationChannel);


                            NotificationCompat.Builder constructorNotificacion = new NotificationCompat.Builder(MainActivity.this, channelId);
                            constructorNotificacion.setSmallIcon(R.drawable.ic_notification);
                            constructorNotificacion.setContentTitle("Delegado asignado");

                            Bitmap fotoAlumno = BitmapFactory.decodeResource(
                                    getResources(),
                                    alumnoSeleccionado.getIdFoto()
                            );

                            String texto = "Nombre: " + alumnoSeleccionado.getNombre() + " Apellidos: " + alumnoSeleccionado.getApellidos() + " DNI: " + alumnoSeleccionado.getDni();

                            constructorNotificacion.setStyle(new NotificationCompat.BigPictureStyle()
                                    .bigPicture(fotoAlumno)
                                    .setSummaryText(texto));

                            constructorNotificacion.setAutoCancel(true);


                            notificationManager.notify(idNotifications, constructorNotificacion.build());

                            idNotifications++;
                        }
                    });
                }
            }
        });

        botonFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                opcionSelecionada=botonFoto;

                FragmentManager fragmentManager = getSupportFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                FragmentFoto miFragmento2 = new FragmentFoto();

                miFragmento2.setAlumno(alumnoSeleccionado);

                fragmentTransaction.replace(R.id.placeholderFragment, miFragmento2);

                fragmentTransaction.commit();

            }
        });

        spinnerAlumnos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                alumnoSeleccionado = (Alumno)parent.getSelectedItem();

                opcionSelecionada.callOnClick();

                if(alumnoSeleccionado.getIdFoto() == 0){

                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                    String channelId = "some_channel_id";
                    CharSequence channelName = "Some Channel";
                    int importance = NotificationManager.IMPORTANCE_DEFAULT;

                    NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
                    notificationManager.createNotificationChannel(notificationChannel);


                    NotificationCompat.Builder constructorNotificacion = new NotificationCompat.Builder(MainActivity.this, channelId);
                    constructorNotificacion.setSmallIcon(R.drawable.ic_notification);
                    constructorNotificacion.setContentTitle("Alumno sin foto");

                    constructorNotificacion.setContentText("El alumno "+ alumnoSeleccionado.getNombre() +" no tiene ninguna foto asignada");

                    constructorNotificacion.setAutoCancel(true);


                    notificationManager.notify(idNotifications, constructorNotificacion.build());

                    idNotifications++;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}