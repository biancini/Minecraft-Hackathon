package it.reti.minecraft.plugin.sample.hooks;

/*
public class LeatherHook implements PluginListener {

	@HookHandler
	public void onInteract(ItemUseHook event) {
		// Recupera il giocatore che ha scatenato l'evento.
		Player player = ((ItemUseHook) event).getPlayer();

		// Controlla se il giocatore ha selezionato un elemento di tipo pelle.
		if (player.getItemHeld().getType() == ItemType.Leather) {
			Location loc = player.getLocation();
			loc.setY(loc.getY() + 2);

			// Crea una nuova mucca e associale un task che la fa bruciare ed esplodere quando tocca terra.
			Cow victim = (Cow) HelperFunctions.creaEssereVivente(loc, EntityType.COW);
			Canary.getServer().addSynchronousTask(new MuccaTask(victim));
			SamplePlugin.logger.info("Creato un MuccaTask per la povera mucca appena lanciata!");

			// Lancia la mucca e incendiala.
			HelperFunctions.svolazza(player, victim, 3);
			victim.setFireTicks(600);
		}
	}
}
*/