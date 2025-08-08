package com.example.pr_maps_api;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback {
    GoogleMap mapa;

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
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        // Configuración del mapa
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mapa.getUiSettings().setZoomControlsEnabled(true);
        mapa.setBuildingsEnabled(true);
        CameraUpdate camUpd1 =
                CameraUpdateFactory
                        .newLatLngZoom(new LatLng(-1.0125927642192343, -79.46947385214916), 16);
        mapa.moveCamera(camUpd1);
        LatLng madrid = new LatLng(-1.0126947079254736, -79.46937765359151);
        CameraPosition camPos = new CameraPosition.Builder()
                .target(madrid)
                .zoom(18f)
                .bearing(290f) //noreste arriba
                .tilt(70f) //punto de vista de la cámara 70 grados
                .build();
        CameraUpdate camUpd3 =
                CameraUpdateFactory.newCameraPosition(camPos);
        mapa.animateCamera(camUpd3);
        PolylineOptions lineas = new
                PolylineOptions()
                .add(new LatLng(-1.0118939727809237, -79.47179600923006))
                .add(new LatLng(-1.012382058657092, -79.46727380499331))
                .add(new LatLng(-1.01344404743434, -79.46737572891723))
                .add(new LatLng(-1.0131908315611293, -79.4717932757677))
                .add(new LatLng(-1.0118939727809237, -79.47179600923006));

        lineas.width(8);
        lineas.color(Color.RED);

        mapa.addPolyline(lineas);

        LatLng punto =
                new LatLng(-1.0126947079254736, -79.46937765359151);
        mapa.addMarker(new
                MarkerOptions().position(punto)
                .title("UTEQ"));
    }
}