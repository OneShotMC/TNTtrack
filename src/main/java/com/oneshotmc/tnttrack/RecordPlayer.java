package com.oneshotmc.tnttrack;

import java.util.Iterator;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Firework;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.oneshotmc.tnttrack.debug.Debug;
import com.oneshotmc.tnttrack.exception.Finished;
import com.oneshotmc.tnttrack.storage.TNTRecordList;
import com.oneshotmc.tnttrack.util.SmoothUtil;

public class RecordPlayer {
	TNTRecordList recordList;
	TNTPrimed tnt;
	Location startloc;
	List<Location> tntlocs;
	List<Block> blocksDestroyed;
	int speed;
	List<Location> smoothedLocs;
	int playScheduleID;
	Plugin plugin;
	public RecordPlayer(Plugin plugin,TNTRecordList recordList){
		this.recordList=recordList;
		this.plugin=plugin;
		tnt=recordList.getTntprimed();
		startloc=tnt.getSourceLoc();
		tntlocs=recordList.getLocations();
		blocksDestroyed=recordList.getBlocksDestroyed();
		speed=recordList.getSlowMoSpeed();
		if(speed>15)speed=15;
		smoothedLocs = SmoothUtil.smoothLinear(tntlocs, speed);
	}
	
	public void play(){
		int ticksLeft=40;
		int ticksLived=0;
		
		final int arraySize=smoothedLocs.size();
		final Location lastLoc=tntlocs.get(tntlocs.size()-1);
		final World world = lastLoc.getWorld();
		final int outOf=0;
		int ID1=0;
		final Hologram hologram = HologramsAPI.createHologram(plugin, smoothedLocs.get(0));
		hologram.appendItemLine(new ItemStack(Material.TNT));
		/*
		hologram.appendTextLine(ChatColor.GREEN+""+ticksLived);
		hologram.appendTextLine(ChatColor.RED+""+ticksLeft);
		*/
		new BukkitRunnable(){
			Iterator<Location> it = smoothedLocs.iterator();
			public void run(){
				if(it.hasNext()){
					Location loc = it.next();
					double amountRan=Math.floor((outOf/arraySize)*40);
					hologram.teleport(loc);
					/*
					hologram.removeLine(2);
					hologram.removeLine(1);
					hologram.appendTextLine(ChatColor.GREEN+""+Double.toString(amountRan));
					hologram.appendTextLine(ChatColor.RED+""+Double.toString(40-amountRan));
					*/
				}
				else{
					Firework firework = world.spawn(lastLoc, Firework.class);
					FireworkMeta fireworkmeta = firework.getFireworkMeta();
					FireworkEffect effect = FireworkEffect.builder().trail(true).flicker(false).withColor(Color.RED).with(Type.BALL).build();
					fireworkmeta.addEffect(effect);
					fireworkmeta.setPower(3);
					firework.setFireworkMeta(fireworkmeta);
					hologram.delete();
					Debug.message(plugin,"FINISHED1");
					this.cancel();
				}
			}
		}.runTaskTimer(plugin, 0, 1);
	}
	public void cancelID(Plugin plugin, int ID){
		plugin.getServer().getScheduler().cancelTask(ID);
	}

}
