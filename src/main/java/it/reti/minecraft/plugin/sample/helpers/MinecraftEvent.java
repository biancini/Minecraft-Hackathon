package it.reti.minecraft.plugin.sample.helpers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Interfaccia che implementa l'annotazione per tutte le estensioni per minecraft create dal plugin.
 * 
 * La documentazione sulla libreria Forge è navigabile qui:
 * http://mcforge.readthedocs.io/en/latest/
 * 
 * @author Andrea Biancini <andrea.biancini@gmail.com>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MinecraftEvent {
	
	String[] aliases();
	String description();
	
	boolean registerInEventBus() default true;
	
}
