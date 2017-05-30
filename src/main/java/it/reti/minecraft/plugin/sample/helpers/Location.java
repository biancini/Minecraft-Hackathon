package it.reti.minecraft.plugin.sample.helpers;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Location {	
	World world = null;
	BlockPos pos = null;

	public Location(World world, double posX, double posY, double posZ) {
		this.world = world;
		this.pos = new BlockPos(posX, posY, posZ);
	}
	
	public Location(Entity entity) {
		this.world = entity.getEntityWorld();
		this.pos = entity.getPosition();
	}

	public double getPosX() {
		return pos.getX();
	}

	public void setPosX(double posX) {
		this.pos = new BlockPos(posX, pos.getY(), pos.getZ());
	}

	public double getPosY() {
		return pos.getY();
	}

	public void setPosY(double posY) {
		this.pos = new BlockPos(pos.getX(), posY, pos.getZ());
	}

	public double getPosZ() {
		return pos.getZ();
	}

	public void setPosZ(double posZ) {
		this.pos = new BlockPos(pos.getX(), pos.getY(), posZ);
	}
	
	public World getWorld() {
		return world;
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public BlockPos getPos() {
		return pos;
	}

}
