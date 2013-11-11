/**
 * @author Indrajit Kumar (Android)
 */
package com.google.map.utils.direction.model;

import java.util.List;

import com.google.android.gms.maps.model.LatLng;

/**
 * @author Indrajit Kumar(Android)
 * @goals
 *        This class aims to define a GoogleDirection which is bound to the JSon structure
 *        returned by the webService :
 *        "http://maps.googleapis.com/maps/api/directions/json?" + "origin=" + start.latitude + ","
 *        + start.longitude + "&destination=" + end.latitude + "," + end.longitude
 *        + "&sensor=false&units=metric&mode=driving";
 */
public class GDirection {
	/**
	 * A GDirection is a list of GDLegs
	 */
	List<GDLegs> mLegsList;
	/**
	 * The North East corner of the square enclosing the road
	 */
	LatLng mNorthEastBound;
	/**
	 * The South West corner of the square enclosing the road
	 */
	LatLng mSouthWestBound;
	/**
	 * Copyrights
	 */
	String copyrights;

	/**
	 * @param pathsList
	 */
	public GDirection(List<GDLegs> legsList) {
		super();
		this.mLegsList = legsList;
	}

	/**
	 * @return the mPathsList
	 */
	public final List<GDLegs> getLegsList() {
		return mLegsList;
	}

	/**
	 * @param mPathsList
	 *            the mPathsList to set
	 */
	public final void setPathsList(List<GDLegs> mLegsList) {
		this.mLegsList = mLegsList;
	}

	/**
	 * @return the mNorthEastBound
	 */
	public final LatLng getmNorthEastBound() {
		return mNorthEastBound;
	}

	/**
	 * @param mNorthEastBound the mNorthEastBound to set
	 */
	public final void setmNorthEastBound(LatLng mNorthEastBound) {
		this.mNorthEastBound = mNorthEastBound;
	}

	/**
	 * @return the mSouthWestBound
	 */
	public final LatLng getmSouthWestBound() {
		return mSouthWestBound;
	}

	/**
	 * @param mSouthWestBound the mSouthWestBound to set
	 */
	public final void setmSouthWestBound(LatLng mSouthWestBound) {
		this.mSouthWestBound = mSouthWestBound;
	}
	/**
	 * @return the copyrights
	 */
	public final String getCopyrights() {
		return copyrights;
	}

	/**
	 * @param copyrights the copyrights to set
	 */
	public final void setCopyrights(String copyrights) {
		this.copyrights = copyrights;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder strB = new StringBuilder("GDirection\r\n");
		for (GDLegs path : mLegsList) {
			strB.append(path.toString());
			strB.append("\r\n");
		}
		return strB.toString();
	}

}
