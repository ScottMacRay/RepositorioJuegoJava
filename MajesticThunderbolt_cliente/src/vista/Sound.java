package vista;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Sound {
	
	public static void music(final String file) {
		new Thread() {
			public void run() {
				try {
					final URL songPath= ClassLoader.getSystemResource(file);
					final AudioClip song= Applet.newAudioClip(songPath);
					song.play();
					Thread.sleep(5000);
					song.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	/*
	 * RVA: este metodo falla si el fichero de musica no esta en la raiz del proyecto
	 * TODO: Alejandro!
	 */
	public static void BGmusic(final String file) {
		new Thread() {
			public void run() {
				try {
					final URL songPath= ClassLoader.getSystemResource(file);
					final AudioClip song= Applet.newAudioClip(songPath);
					song.play();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
}  
