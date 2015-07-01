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
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.oneshotmc.tnttrack.debug.Debug;
import com.oneshotmc.tnttrack.exception.Finished;
import com.oneshotmc.tnttrack.storage.TNTRecordList;
import com.oneshotmc.tnttrack.util.SmoothUtil;

public class RecordPlayer implements Listener {
	TNTRecordList recordList;
	TNTPrimed tnt;
	Location startloc;
	List<Vector> tntvels;
	List<Block> blocksDestroyed;
	int speed;
	List<Location> smoothedLocs;
	int playScheduleID;
	Plugin plugin;
	public RecordPlayer(Plugin plugin,TNTRecordList recordList){
		this.recordList=recordList;
		this.plugin=plugin;
		tnt=recordList.getTntprimed();
		this.startloc=tnt.getSourceLoc();
		tntvels=recordList.getVelocities();
		blocksDestroyed=recordList.getBlocksDestroyed();
		speed=recordList.getSlowMoSpeed();
		if(speed>15)speed=15;
	}
	
	public void play(){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		int ticksLeft=40;
		int ticksLived=0;
		final World world = startloc.getWorld();
		final TNTPrimed tnt = (TNTPrimed) world.spawnEntity(startloc.subtract(0, 0, 0), EntityType.PRIMED_TNT);
		
		tnt.setFuseTicks(40);
		final int size= tntvels.size();
		int ID1=0;
		/*
		hologram.appendTextLine(ChatColor.GREEN+""+ticksLived);
		hologram.appendTextLine(ChatColor.RED+""+ticksLeft);
		*/
		new BukkitRunnable(){
			int timesRun=1;
			int arrayPlace=0;
			Iterator<Vector> it = tntvels.iterator();
			Vector currentVel = tntvels.get(0);
			public void run(){
				if(arrayPlace<size){
					tnt.setFuseTicks(40);
					if(timesRun>speed){
						arrayPlace++;
						if(arrayPlace<size){
							currentVel=tntvels.get(arrayPlace).multiply(1/speed);
						}
						timesRun=1;
						return;
					}
					tnt.setVelocity(currentVel);
					/*
					double amountRan=Math.floor((21)*40);
					*/
					/*
					hologram.removeLine(2);
					hologram.removeLine(1);
					hologram.appendTextLine(ChatColor.GREEN+""+Double.toString(amountRan));
					hologram.appendTextLine(ChatColor.RED+""+Double.toString(40-amountRan));
					*/
					timesRun++;
				}
				else{
					final Firework firework = world.spawn(startloc, Firework.class);
					FireworkMeta fireworkmeta = firework.getFireworkMeta();
					FireworkEffect effect = FireworkEffect.builder().trail(true).flicker(false).withColor(Color.RED).with(Type.BALL).build();
					fireworkmeta.addEffect(effect);
					fireworkmeta.setPower(3);
					firework.setFireworkMeta(fireworkmeta);
					timesRun++;
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){

						public void run() {
							firework.teleport(tnt.getLocation());
							firework.detonate();
						}
						
						
					}, 1);
					tnt.remove();
					Debug.message(plugin,"FINISHED1");
					this.cancel();
				}
			}
		}.runTaskTimer(plugin, 0, 2);
	}
	public void cancelID(Plugin plugin, int ID){
		plugin.getServer().getScheduler().cancelTask(ID);
	}

}
