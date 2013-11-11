
package com.google.map.utils.direction.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * @author Indrajit Kumar (Android)
 * @goals
 *        This class aims to define a GoogleDirection Point which is bound to the JSon structure
 *        returned by the webService :
 *        "http://maps.googleapis.com/maps/api/directions/json?" + "origin=" + start.latitude + ","
 *        + start.longitude + "&destination=" + end.latitude + "," + end.longitude
 *        + "&sensor=false&units=metric&mode=driving";
 */
public class GDPoint {
	double mLat;
	double mLng;
	/**
	 * The corresponding LatLng
	 * Not in the JSon Object. It's an helpful attribute
	 */
	private LatLng mLatLng = null;
	
	/**
	 * The builder
	 * @param coordinate retrieve from JSon
	 */
	public GDPoint(double lat,double lng) {
		super();
		this.mLat = lat;
		this.mLng=lng;
	}

	/**
	 * @return The LatLng Object linked with that point
	 */
	public LatLng getLatLng() {
		if (mLatLng == null) {
			mLatLng = new LatLng(mLat,mLng);
		}
		return mLatLng;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "["+mLat+","+mLng+"]";
	}
}
