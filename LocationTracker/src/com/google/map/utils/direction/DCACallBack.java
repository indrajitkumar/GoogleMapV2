
package com.google.map.utils.direction;

import java.util.List;

import com.google.map.utils.direction.model.GDirection;



public interface DCACallBack {

	public void onDirectionLoaded(List<GDirection> directions);
}
