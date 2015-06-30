package com.oneshotmc.tnttrack.storage;

import org.bukkit.entity.Entity;

public interface RecordingMaker {
	void record();
	int unpause();
	//Should return amount of frames
	int pause();
	int stop();
	void addListener(Entity e);
	void removeListener(Entity e);
}
