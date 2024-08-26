package konrad.poker;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import konrad.poker.client.Dimensions;
import konrad.poker.client.PokerGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("poker");
		//720p (HD): 1280 x 720 pikseli
		config.setWindowedMode(Dimensions.WINDOW_WIDTH , Dimensions.WINDOW_HEIGHT );
		//1080p (Full HD): 1920 x 1080 pikseli
		new Lwjgl3Application(new PokerGame(), config);
	}
}
