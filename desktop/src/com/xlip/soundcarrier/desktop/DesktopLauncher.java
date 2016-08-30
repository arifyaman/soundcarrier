package com.xlip.soundcarrier.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.xlip.soundcarrier.SoundCarrier;
import com.xlip.soundcarrier.Utils.Ayar;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width= Ayar.appwidth;
		config.height=Ayar.appheight;
		config.title="Sound Carrier";
		new LwjglApplication(new SoundCarrier(), config);
	}
}
