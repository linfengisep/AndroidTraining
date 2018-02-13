package isep.googlemapplaying;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button btnSatelite,btnHybrid,btnMap;
    private boolean mapReady=false;
    //move camera;
    static final CameraPosition cpParis = CameraPosition.builder()
            .target(new LatLng(48.855649, 2.344957))
            .zoom(12)
            .bearing(0)
            .tilt(15)
            .build();

    //marker
    private MarkerOptions luxembourg=null;
    private MarkerOptions champsDeMars=null;
    private MarkerOptions placeRepublique=null;
    private MarkerOptions luyan=null;

    //polyline
    LatLng heartBottom = new LatLng(48.779662, 2.319422);
    LatLng heartTop = new LatLng(48.820144, 2.319264);
    LatLng heartLeftTop = new LatLng(48.821350, 2.305214);
    LatLng heartLeftTop2 = new LatLng(48.816410, 2.296155);
    LatLng heartRightTop = new LatLng(48.821350, 2.306000);
    LatLng heartRightTop2 = new LatLng(48.815370, 2.346051);

/*
    static final CameraPosition cpNewyork = CameraPosition.builder()
            .target(new LatLng(40.701781, -74.013144))
            .zoom(10)
            .bearing(0)
            .tilt(15)
            .build();

    static final CameraPosition cpDublin = CameraPosition.builder()
            .target(new LatLng(53.3498053, -6.2603097))
            .zoom(10)
            .bearing(0)
            .tilt(15)
            .build();
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        btnSatelite = (Button)findViewById(R.id.btnSatelite);
        btnSatelite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mapReady){
                    flyTo(cpParis);
                }
            }
        });

        /*
        btnMap = (Button)findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mapReady){
                    flyTo(cpNewyork);
                }
            }
        });
        btnHybrid = (Button)findViewById(R.id.btnHybrid);
        btnHybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mapReady){
                    flyTo(cpSeattle);
                }
            }
        });

        */

        //define some marker
        luxembourg= new MarkerOptions()
                .position(new LatLng(48.846956, 2.336964))
                .title("Luxembourg")
                .snippet("jardin");

        champsDeMars = new MarkerOptions()
                .position(new LatLng(48.856496, 2.298853))
                .title("Champs de Mars")
                .snippet("Tour");
        placeRepublique = new MarkerOptions()
                .position(new LatLng(48.867780, 2.364034))
                .title("Place Republique")
                .snippet("Place");
        luyan = new MarkerOptions()
                .position(new LatLng(48.794050, 2.320512))
                .title("Luyan est là")
                .snippet("avec son pote à côté.")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.luyan1));

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady=true;
        mMap = googleMap;//mMap is a local instance of googleMap;

        //Add a marker in Sydney and move the camera
        LatLng tianjin = new LatLng(39.161265, 117.205199);
        mMap.addMarker(new MarkerOptions().position(tianjin).title("Chez Luyan"));

        CameraPosition target = CameraPosition.builder().target(tianjin).zoom(4).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(target));

        mMap.addMarker(luxembourg);
        mMap.addMarker(placeRepublique);
        mMap.addMarker(champsDeMars);
        mMap.addMarker(luyan);

//polyline
        mMap.addPolyline(new PolylineOptions().geodesic(true)
                .add(heartBottom)
                .add(heartLeftTop2)
                .add(heartLeftTop)
                .add(heartTop)
                .add(heartRightTop)
                .add(heartRightTop2)
                .add(heartBottom));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    private void flyTo(CameraPosition targetCity){
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(targetCity),8000,null);
    }
}
