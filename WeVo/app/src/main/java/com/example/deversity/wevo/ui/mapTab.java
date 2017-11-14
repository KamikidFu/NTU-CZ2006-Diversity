package com.example.deversity.wevo.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.deversity.wevo.Entity.VWO;
import com.example.deversity.wevo.R;
import com.example.deversity.wevo.mgr.VolunteerClientMgr;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.geojson.GeoJsonFeature;
import com.google.maps.android.geojson.GeoJsonLayer;
import com.google.maps.android.geojson.GeoJsonPointStyle;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * MapTab is a boundary class for showing VWOs on google map
 * @author John; Fu, Yunhao
 */
public class mapTab extends Fragment implements GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback {

    private GoogleMap mMap;
    private MapView mMapView;
    private View mView;
    private GeoJsonLayer layer;
    private final static int PERMISSION_FINE_LOCATION = 101;
    private static List<MarkerOptions> VWOMarkerList = VolunteerClientMgr.getVWOMarkerList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_maptab, container, false);

        VolunteerClientMgr.addVWOMarker(1.3473497,103.6830713,"Nanyang Technological University Welfare Services Club");
        VolunteerClientMgr.addVWOMarker(1.4293756,103.7710292,"Sports and Recreation");
        VolunteerClientMgr.addVWOMarker(1.3194957,103.8270117,"The Netherlands Charity Association");
        VolunteerClientMgr.addVWOMarker(1.2652194,103.81829,"The Singapore Branch Of The Missions To Seafarers");
        VolunteerClientMgr.addVWOMarker(1.3516291,103.9476706,"BCSS - Tampines Youth Centre");

        mMapView = (MapView) mView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(this);
        return mView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap = googleMap;
        LatLng Sg = new LatLng(1.350524, 103.815610);
        CameraPosition target = CameraPosition.builder().target(Sg).zoom(10).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(target));
        for(MarkerOptions m:VWOMarkerList){
            mMap.addMarker(m);
        }

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);

        try {
            layer = new GeoJsonLayer( mMap, R.raw.vwo, getActivity().getApplicationContext() );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (GeoJsonFeature feature : layer.getFeatures()) {
            if (feature.hasProperty( "Name" )) {
                String name = feature.getProperty("Name");
            }
        }

        layer.addLayerToMap();


        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        // Set a listener for info window events.
        mMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        for(MarkerOptions markerOptions: VWOMarkerList){
            if(markerOptions.getTitle().equals(marker.getTitle())){
                Intent intent = new Intent(getContext(), VWOView.class);
                intent.putExtra("VWOName",marker.getTitle());
                startActivity(intent);
                try {
                    finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                return;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(getContext(), "This app requires location permissions!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
