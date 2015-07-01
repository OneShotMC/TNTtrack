package com.oneshotmc.tnttrack.storage;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.oneshotmc.tnttrack.TntRecorder;

public class RecorderStorage {
	HashMap<UUID,TntRecorder> recordings = new HashMap<UUID, TntRecorder>();
	public RecorderStorage(){
	}
	public void compressRecordings(){
		for(UUID ui : recordings.keySet()){
			if(!(Bukkit.getPlayer(ui).isOnline())){
				recordings.remove(ui);
			}
		}
	}
	public void addPlayer(Player player){
		if(player==null){
			System.out.println("caught null! 1");
		}
		UUID pu=player.getUniqueId();
		if(player.getUniqueId()==null){
			System.out.println("caught null! 2");
		}
		if(recordings==null){
			System.out.println("caught null 3.");
		}
		System.out.println(pu);
		recordings.put(pu, new TntRecorder(player));
	}
	public HashMap<UUID,TntRecorder> getTntRecorders (){
		return recordings;
	}
	public TntRecorder getRecorder(Player player){
		return recordings.get(player.getUniqueId());
	}
}
