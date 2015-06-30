package com.oneshotmc.tnttrack.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;

public class SmoothUtil {
	public static List<Location> smoothLinear(List<Location> locs, int amount){
		List<Location> allLocations = new ArrayList<Location>();
		for(int i=0;i<locs.size()-1;i++){
			Location loc1 = locs.get(i);
			Location loc2 = locs.get(i+1);
			for(int t=0;t<amount;t++){
				allLocations.add(getBetween(loc1,loc2,(t/amount)));
			}
			if(i==locs.size()-2){
				allLocations.add(getBetween(loc1,loc2,(1)));
			}
		}
		return allLocations;
	}
	
	public static Location getBetween(Location loc1 , Location loc2, long percent){
		World world1=loc1.getWorld();
		World world2=loc2.getWorld();
		if(!(world1.getName().equals(world2.getName())))return null;
		
		double x1=loc1.getX();
		double y1=loc1.getY();
		double z1=loc1.getZ();
		
		double x2=loc2.getX();
		double y2=loc2.getY();
		double z2=loc2.getZ();
		
		double x3=get1Dpercent(x1,x2,percent);
		double y3=get1Dpercent(y1,y2,percent);
		double z3=get1Dpercent(z1,z2,percent);
		
		return new Location(world1,x3,y3,z3);
	}
	
	public static double get1Dpercent(double a, double b, long p){
		return a+p*(b-a);		
	}
}
