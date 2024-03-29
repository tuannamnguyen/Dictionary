package nam.Dictionary.GUI;

import java.util.Scanner;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextToSpeech {
    VoiceManager freeVM;
    Voice voice;

    /**
     * constructor.
     * 
     * @param words word to pronounce.
     */
    public TextToSpeech(String words) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();// Allocating Voice
            try {
                voice.setRate(190);// Setting the rate of the voice
                voice.setPitch(150);// Setting the Pitch of the voice
                voice.setVolume(3);// Setting the volume of the voice

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } else {
            throw new IllegalStateException("Cannot find voice: kevin16");
        }
    }

    /**
     * pronounce selected word.
     * 
     * @param words selected word.
     */
    public void SpeakText(String words) {
        voice.speak(words);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String words = sc.nextLine();
        TextToSpeech tts = new TextToSpeech(words);
        tts.SpeakText(words);
        sc.close();
    }
}