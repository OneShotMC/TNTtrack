package com.oneshotmc.tnttrack.storage;

import java.util.HashMap;

import org.bukkit.plugin.Plugin;

import com.intellectualcrafters.plot.object.PlotId;

public class PlotRecordArea {
	public PlotRecordArea(Plugin plugin){
	
	}
	public HashMap<PlotId,TNTrecordListStorage> hashmap = new HashMap<PlotId,TNTrecordListStorage>();
}
