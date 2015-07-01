package com.oneshotmc.tnttrack.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import com.intellectualcrafters.plot.object.Plot;
import com.oneshotmc.tnttrack.RecordPlayer;
import com.oneshotmc.tnttrack.debug.Debug;

public class TNTRecordList implements Listener{
	Plugin plugin;
	public final static int maxAmountPerPlot=5;
	Plot plot;
	List<TNTRecordList> list;
	boolean recording=false;
	boolean playing=false;
	boolean endPhase=false;
	boolean replayAuto=true;
	int slowMoSpeed;
	int recordingTicksLength;
	Entity entity;
	TNTPrimed tntprimed;
	UUID entityUUID;
	List<Vector> velocities = new ArrayList<Vector>();
	List<Block> blocksDestroyed = new ArrayList<Block>();
	int taskID;
	public TNTRecordList(Plugin plugin,int slowMoSpeed){
		this.plugin=plugin;
		this.slowMoSpeed=slowMoSpeed;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		Debug.message(plugin, "slowMoSpeed= "+slowMoSpeed);
		taskID = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){

			public void run() {
				if(recording){
					Vector el = entity.getVelocity();
					if(el==null){
						stop();
						return;
					}
					System.out.println("added loc");
					velocities.add(el);
				}
				
			}
			
		}, 0, 2);
	}
	public TNTRecordList(Plugin plugin, int slowMoSpeed, List<TNTRecordList> list){
		this(plugin,slowMoSpeed);
		this.list=list;
	}
	public HashMap<UUID, TNTLocations> hashmap = new HashMap<UUID,TNTLocations>();
	public void record() {
		recording=true;
	}
	public int pause() {
		recording=false;
		return recordingTicksLength;
	}
	public int stop() {
		System.out.println("stopped");
		Bukkit.getServer().getScheduler().cancelTask(taskID);
		recording=false;
		if(replayAuto){
			if(list!=null){
			list.remove(this);
			}
			RecordPlayer recordPlayer = new RecordPlayer(plugin,this);
			recordPlayer.play();
		}
		return recordingTicksLength;
	}
	public boolean isRecording() {
		return recording;
	}
	public void setRecording(boolean recording) {
		this.recording = recording;
	}
	public boolean isPlaying() {
		return playing;
	}
	public void setPlaying(boolean playing) {
		this.playing = playing;
	}
	public boolean isEndPhase() {
		return endPhase;
	}
	public void setEndPhase(boolean endPhase) {
		this.endPhase = endPhase;
	}
	public boolean isReplayAuto() {
		return replayAuto;
	}
	public void setReplayAuto(boolean replayAuto) {
		this.replayAuto = replayAuto;
	}
	public int getSlowMoSpeed() {
		return slowMoSpeed;
	}
	public void setSlowMoSpeed(int slowMoSpeed) {
		this.slowMoSpeed = slowMoSpeed;
	}
	public int getRecordingTicksLength() {
		return recordingTicksLength;
	}
	public void setRecordingTicksLength(int recordingTicksLength) {
		this.recordingTicksLength = recordingTicksLength;
	}
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	public TNTPrimed getTntprimed() {
		return tntprimed;
	}
	public void setTntprimed(TNTPrimed tntprimed) {
		this.tntprimed = tntprimed;
	}
	public UUID getEntityUUID() {
		return entityUUID;
	}
	public void setEntityUUID(UUID entityUUID) {
		this.entityUUID = entityUUID;
	}
	public List<Vector> getVelocities() {
		return velocities;
	}
	public void setVelocities(List<Vector> velocities) {
		this.velocities = velocities;
	}
	public List<Block> getBlocksDestroyed() {
		return blocksDestroyed;
	}
	public void setBlocksDestroyed(List<Block> blocksDestroyed) {
		this.blocksDestroyed = blocksDestroyed;
	}
	public void setListener(Entity e) {
		entity=e;
		entityUUID=e.getUniqueId();
		if(e instanceof TNTPrimed){
			tntprimed=(TNTPrimed)e;
		}
	}
	public int unpause() {
		recording=true;
		return recordingTicksLength;
	}
	
	@EventHandler
	public void onExplode(EntityExplodeEvent e){
		Entity en = e.getEntity();
		if(!(en instanceof TNTPrimed))return;
		UUID uuid = en.getUniqueId();
		if(!(entityUUID.equals(uuid)))return;
		e.blockList().addAll(e.blockList());
		EntityExplodeEvent.getHandlerList().unregister(this);
		System.out.println("exploded");
		Vector el = entity.getVelocity();
		velocities.add(el);
		stop();
	}
	
}
