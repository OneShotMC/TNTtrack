package com.oneshotmc.tnttrack.listeners;																							

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dispenser;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
import com.oneshotmc.tnttrack.TNTtrack;
import com.oneshotmc.tnttrack.storage.PlotRecordArea;
import com.oneshotmc.tnttrack.storage.TNTLocations;
import com.oneshotmc.tnttrack.storage.TNTRecordList;
import com.oneshotmc.tnttrack.storage.TNTrecordListStorage;
import com.oneshotmc.tnttrack.util.ChatType;
import com.oneshotmc.tnttrack.util.ChatUtil;

public class DispenseListener implements Listener {
	TNTtrack plugin;
	TNTRecordList tntlist;
	PlotRecordArea storage;
	PlotAPI api = new PlotAPI();
	public DispenseListener(TNTtrack plugin){
		this.plugin=plugin;
		storage = new PlotRecordArea(plugin);
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onDispense(BlockDispenseEvent e){
		if(e.getItem().getType().equals(Material.COMPASS)){
			e.setCancelled(true);
			if(e.getBlock().getState() instanceof org.bukkit.block.Dispenser){
			Block block = e.getBlock();
			org.bukkit.block.Dispenser dispenser = (org.bukkit.block.Dispenser) block.getState();
			Dispenser dispen = (Dispenser) dispenser.getData();
			Inventory inv = dispenser.getInventory();
			TNTPrimed tnt;
			Entity entity;
			BlockFace bf = dispen.getFacing();
			System.out.println("facing: "+bf);
			Location loc = block.getLocation().add(0.5,0.5,0.5);
			switch(bf){
			case DOWN:
				entity = loc.getWorld().spawnEntity(loc.add(0, -1, 0), EntityType.PRIMED_TNT);
				break;
			case UP:
				entity = loc.getWorld().spawnEntity(loc.add(0, 1, 0), EntityType.PRIMED_TNT);
				break;
			case EAST:
				entity = loc.getWorld().spawnEntity(loc.add(1, 0, 0), EntityType.PRIMED_TNT);
				break;
			case WEST:
				entity = loc.getWorld().spawnEntity(loc.add(-1, 0, 0), EntityType.PRIMED_TNT);
				break;
			case SOUTH:
				entity = loc.getWorld().spawnEntity(loc.add(0, 0, 1), EntityType.PRIMED_TNT);
				break;
			case NORTH:
				entity = loc.getWorld().spawnEntity(loc.add(0, 0, -1), EntityType.PRIMED_TNT);
				break;
			default:
				entity=null;
				break;
			}
			tnt=(TNTPrimed)entity;
			Plot plot = api.getPlot(loc);
			if(!(storage.hashmap.containsKey(plot.getId()))){
			storage.hashmap.put(plot.getId(), new TNTrecordListStorage());
			storage.hashmap.get(plot.getId()).addRecording(entity, plugin, getCompassAmount(inv));
			}
			// ADD CONFIG HERE POSSIBLY? v
			else if(storage.hashmap.get(plot.getId()).list.size()<5){
				storage.hashmap.get(plot.getId()).addRecording(entity, plugin, getCompassAmount(inv));
			}
			else{
				ChatUtil.sendMessage(plot, "Too many TNT recordings at once!", ChatType.WARNING);
			}
		}
	}
}
	public int getCompassAmount(Inventory inv){
		int amount=0;
		for(ItemStack item : inv.getContents()){
			if(item==null)continue;
			if(item.getType()==Material.COMPASS){
				amount+=item.getAmount();
			}
		}
		return amount+1;
	}
}
																															