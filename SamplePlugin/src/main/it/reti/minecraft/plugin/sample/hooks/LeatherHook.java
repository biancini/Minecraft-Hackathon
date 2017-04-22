package it.reti.minecraft.plugin.sample.hooks;

import it.reti.minecraft.plugin.sample.tasks.MuccaTask;
import it.reti.minecraft.plugin.sample.utils.HelperFunctions;
import it.reti.minecraft.plugin.sample.utils.IHook;
import net.canarymod.Canary;
import net.canarymod.api.entity.EntityType;
import net.canarymod.api.entity.living.animal.Cow;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.ItemType;
import net.canarymod.api.world.position.Location;
import net.canarymod.hook.player.ItemUseHook;

public class LeatherHook implements IHook {

	@Override
	public void onInteract(ItemUseHook event) {
		Player player = event.getPlayer();

		if (player.getItemHeld().getType() == ItemType.Leather) {
			Location loc = player.getLocation();
			loc.setY(loc.getY() + 2);

			Cow victim = (Cow) HelperFunctions.creaEssereVivente(loc, EntityType.COW);
			Canary.getServer().addSynchronousTask(new MuccaTask(victim));

			HelperFunctions.svolazza(player, victim, 3);
			victim.setFireTicks(600);
		}
	}

}
