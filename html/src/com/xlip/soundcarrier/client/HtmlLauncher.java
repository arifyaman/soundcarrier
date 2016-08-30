package com.xlip.soundcarrier.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.xlip.soundcarrier.SoundCarrier;
import com.xlip.soundcarrier.Utils.Ayar;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                GwtApplicationConfiguration configuration=new GwtApplicationConfiguration(800,600);
                configuration.preferFlash=true;

                return configuration;
        }

        @Override
        public ApplicationListener getApplicationListener () {
                ApplicationListener listener=new SoundCarrier();

                return  listener;
        }
}