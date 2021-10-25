package model;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Word {
    private String wordTarget;
    private String wordSound;
    private String wordExplain;

    public Word(String wordTarget, String wordSound, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordSound=wordSound;
        this.wordExplain = wordExplain;
    }

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }

    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    public void setWordSound(String wordSound) {
        this.wordSound = wordSound;
    }

    public String getWordExplain() {
        return wordExplain;
    }

    public String getWordSound() {
        return wordSound;
    }

    public String getWordTarget() {
        return wordTarget;
    }

    public void speak(){
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice!=null){
            voice.allocate();
            voice.speak(this.wordTarget);
            voice.deallocate();
        } else {
            System.out.println("Error in getting Voices");
        }
    }
}
