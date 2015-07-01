package com.oneshotmc.tnttrack.storage;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

public class TNTrecordListStorage {
	public List<TNTRecordList> list = new ArrayList<TNTRecordList>();
	public void addRecording(Entity entity,Plugin plugin,int speed){
		TNTRecordList trl = new TNTRecordList(plugin, speed, list);
		trl.setListener(entity);
		trl.record();
		list.add(trl);
	}
}
