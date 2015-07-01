package com.oneshotmc.tnttrack.replay;

import java.util.List;
import java.util.UUID;

import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class CustomTNT implements org.bukkit.entity.TNTPrimed{

	public float getYield() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isIncendiary() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setIsIncendiary(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setYield(float arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean eject() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getEntityId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public float getFallDistance() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getFireTicks() {
		// TODO Auto-generated method stub
		return 0;
	}

	public EntityDamageEvent getLastDamageCause() {
		// TODO Auto-generated method stub
		return null;
	}

	public Location getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	public Location getLocation(Location arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getMaxFireTicks() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Entity> getNearbyEntities(double arg0, double arg1, double arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	public Entity getPassenger() {
		// TODO Auto-generated method stub
		return null;
	}

	public Server getServer() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getTicksLived() {
		// TODO Auto-generated method stub
		return 0;
	}

	public EntityType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	public UUID getUniqueId() {
		// TODO Auto-generated method stub
		return null;
	}

	public Entity getVehicle() {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector getVelocity() {
		// TODO Auto-generated method stub
		return null;
	}

	public World getWorld() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isInsideVehicle() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isOnGround() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean leaveVehicle() {
		// TODO Auto-generated method stub
		return false;
	}

	public void playEffect(EntityEffect arg0) {
		// TODO Auto-generated method stub
		
	}

	public void remove() {
		// TODO Auto-generated method stub
		
	}

	public void setFallDistance(float arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setFireTicks(int arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setLastDamageCause(EntityDamageEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean setPassenger(Entity arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setTicksLived(int arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setVelocity(Vector arg0) {
		// TODO Auto-generated method stub
		
	}

	public Spigot spigot() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean teleport(Location arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean teleport(Entity arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean teleport(Location arg0, TeleportCause arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean teleport(Entity arg0, TeleportCause arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<MetadataValue> getMetadata(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasMetadata(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public void removeMetadata(String arg0, Plugin arg1) {
		// TODO Auto-generated method stub
		
	}

	public void setMetadata(String arg0, MetadataValue arg1) {
		// TODO Auto-generated method stub
		
	}

	public int getFuseTicks() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Entity getSource() {
		// TODO Auto-generated method stub
		return null;
	}

	public Location getSourceLoc() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setFuseTicks(int arg0) {
		// TODO Auto-generated method stub
		
	}

}
