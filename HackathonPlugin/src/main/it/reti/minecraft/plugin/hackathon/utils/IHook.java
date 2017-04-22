package it.reti.minecraft.plugin.hackathon.utils;

import net.canarymod.hook.player.ItemUseHook;

public interface IHook {
	
	public void onInteract(ItemUseHook event);
	
}
