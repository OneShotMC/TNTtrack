package com.oneshotmc.tnttrack.debug;

import org.bukkit.plugin.Plugin;

public class Debug {
	public static final boolean debug = true;
	public static void message(Plugin plu,String str){
		if(debug)plu.getLogger().info(str);
	}
}
