import java.applet.Applet;
import java.applet.AudioClip;
import java.util.Random;

public class Sound {
	//Gets sounds from class folder to use if Sound is called
	public static final AudioClip COUNTDOWN = Applet.newAudioClip(Sound.class.getResource("beep07.wav"));
	public static final AudioClip GO = Applet.newAudioClip(Sound.class.getResource("bell1.wav"));
	public static final AudioClip SCORE = Applet.newAudioClip(Sound.class.getResource("score.wav"));
	public static final AudioClip BACK1 = Applet.newAudioClip(Sound.class.getResource("back1.wav")); 
	public static final AudioClip BACK2 = Applet.newAudioClip(Sound.class.getResource("back2.wav")); 
	public static final AudioClip BACK3 = Applet.newAudioClip(Sound.class.getResource("back3.wav")); 
	//returns an AudioClip depending on n which is randomly generated
	public static AudioClip impact(){
		int n = (new Random()).nextInt(5) + 1;
		AudioClip impact = null;
		if(n == 1){
			impact = Applet.newAudioClip(Sound.class.getResource("impact1.wav")); 
		}
		else if(n == 2){
			impact = Applet.newAudioClip(Sound.class.getResource("impact2.wav")); 
		}
		else if(n == 3){
			impact = Applet.newAudioClip(Sound.class.getResource("impact3.wav")); 
		}
		else if(n == 4){
			impact = Applet.newAudioClip(Sound.class.getResource("impact4.wav")); 
		}
		else if(n == 5){
			impact = Applet.newAudioClip(Sound.class.getResource("impact5.wav")); 
		}
		else if(n == 6){
			impact = Applet.newAudioClip(Sound.class.getResource("impact6.wav")); 
		}
		return impact;
	}
	
	public static AudioClip wallimpact(){
		int n = (new Random()).nextInt(2) + 1;
		AudioClip impact = null;
		if(n == 1){
			impact = Applet.newAudioClip(Sound.class.getResource("wallimpact1.wav")); 
		}
		else if(n == 2){
			impact = Applet.newAudioClip(Sound.class.getResource("wallimpact2.wav")); 
		}
		return impact;
	}
}
