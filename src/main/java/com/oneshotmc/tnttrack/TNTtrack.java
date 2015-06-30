package com.oneshotmc.tnttrack;

import org.bukkit.plugin.java.JavaPlugin;

import com.oneshotmc.tnttrack.listeners.DispenseListener;

public class TNTtrack extends JavaPlugin{
	
	@Override
	public void onEnable(){
		new DispenseListener(this);
	}
	
	@Override
	public void onDisable(){
		
	}
}
