package com.example.locationtracker;


import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utils.SystemConfig;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity implements LocationSource, LocationListener, OnMapClickListener{
	final int RQS_GooglePlayServices = 1;
	GoogleMap googleMap;
	TextView tvLocInfo;
	Geocoder geoCoder;
	List<Address> addresses;
	public MarkerOptions maropt;
	//OnMarkerClickListener markerClickListener;
	LocationManager myLocationManager = null;
	Location location;
	OnLocationChangedListener myLocationListener = null;
	Criteria myCriteria;
	public static double LAT = 21.000;
	public static double LON = 78.000;
	//final LatLng INDIA = new LatLng(21.000, 78.000);


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initilizeMap();
	}


	/**
	 * function to load map. If map is not created it will create it for you
	 * */
	@SuppressLint({ "NewApi", "ServiceCast" })
	private void initilizeMap() {
		tvLocInfo = (TextView)findViewById(R.id.locinfo);

		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();

			googleMap.setMyLocationEnabled(true);

			//googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			//myMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			//myMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			//googleMap.setOnMapClickListener((OnMapClickListener) MainActivity.this);
			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}

			myCriteria = new Criteria();
			myCriteria.setAccuracy(Criteria.ACCURACY_FINE);

			myLocationManager = (LocationManager)getSystemService(LOCATION_SERVICE);

			//String provider = myLocationManager.getBestProvider(myCriteria, false);
			Location location = myLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			LatLng userLocation = new LatLng(location.getLatitude(),location.getLongitude());


			if (googleMap != null) {
				Marker current_location_marker = googleMap.addMarker(new MarkerOptions().position(userLocation)
						.title("Current Location"));
			} else {

			}

			googleMap.moveCamera(
					CameraUpdateFactory.newLatLngZoom(userLocation, 10));

			// Zoom in, animating the camera.
			googleMap.animateCamera(
					CameraUpdateFactory.zoomTo(10), 2000, null);


			// Zoom in, animating the camera.
			googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null); 
			googleMap.setOnMapClickListener(this);
		}
	}




	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		initilizeMap();

		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

		if (resultCode == ConnectionResult.SUCCESS){
			Toast.makeText(getApplicationContext(), 
					"isGooglePlayServicesAvailable SUCCESS", 
					Toast.LENGTH_LONG).show();
		}else{
			GooglePlayServicesUtil.getErrorDialog(resultCode, this, RQS_GooglePlayServices);
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_legalnotices:
			String LicenseInfo = GooglePlayServicesUtil.getOpenSourceSoftwareLicenseInfo(
					getApplicationContext());
			AlertDialog.Builder LicenseDialog = new AlertDialog.Builder(MainActivity.this);
			LicenseDialog.setTitle("Legal Notices");
			LicenseDialog.setMessage(LicenseInfo);
			LicenseDialog.show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		googleMap.setLocationSource(null);
		myLocationManager.removeUpdates(this);
		super.onPause();
	}
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		if (myLocationListener != null) {
			myLocationListener.onLocationChanged(location);

			double lat = location.getLatitude();
			double lon = location.getLongitude();

			tvLocInfo.setText(
					"lat: " + lat + "\n" +
							"lon: " + lon);
		}
	}


	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}


	@Override
	public void activate(OnLocationChangedListener listener) {
		// TODO Auto-generated method stub
		myLocationListener = listener;
	}


	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		myLocationListener = null;
	}




	@Override
	public void onMapClick(LatLng arg0) {
		String address = null;
		String city = null;
		String country = null ;
		// TODO Auto-generated method stub
		tvLocInfo.setText(arg0.toString());
		try {
			geoCoder = new Geocoder(this, Locale.ENGLISH);
			addresses = geoCoder.getFromLocation(arg0.latitude, arg0.longitude, 1);
			if(addresses!=null){
				address = addresses.get(0).getAddressLine(0);
				city = addresses.get(0).getAddressLine(1);
				country = addresses.get(0).getAddressLine(2);
			}
			else{
				Toast.makeText(this, "No Address returned!", Toast.LENGTH_SHORT).show();

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Toast.makeText(this, "Address="+address+",City"+city+",Country"+country, Toast.LENGTH_SHORT).show();
		googleMap.animateCamera(CameraUpdateFactory.newLatLng(arg0));
		maropt = new MarkerOptions().position(new LatLng(arg0.latitude, arg0.longitude))
				.snippet("any text").title(address);
		googleMap.addMarker(maropt);


	}

	OnMarkerClickListener markerClickListener = new OnMarkerClickListener() {

		@Override
		public boolean onMarkerClick(Marker arg0) {
			// TODO Auto-generated method stub
			return false;
		}
	};

}
