package it.reti.minecraft.plugin.sample.helpers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Interfaccia che implementa l'annotazione per tutte le estensioni per minecraft create dal plugin.
 * 
 * La documentazione sulla libreria Canary è navigabile qui:
 * http://docs.visualillusionsent.net/CanaryLib/1.0-RC-3/overview-summary.html
 * 
 * @author Andrea Biancini <andrea.biancini@gmail.com>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MinecraftEvent {
	
	String[] aliases();
	String description();
	
	boolean registerInEventBus() default true;
	
}
