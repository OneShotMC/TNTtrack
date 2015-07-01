package com.oneshotmc.tnttrack.storage;

import java.util.ArrayList;
import java.util.List;

import com.oneshotmc.tnttrack.LocTime;

public class TNTLocations {
	long time;
	public TNTLocations(long time){
		this.time=time;
	}
	public List<LocTime> list = new ArrayList<LocTime>();
}
