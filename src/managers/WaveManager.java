package managers;

import event.Wave;
import scenes.Playing;

import java.util.ArrayList;
import java.util.Arrays;

public class WaveManager {

    private Playing playing;
    private ArrayList<Wave> wave = new ArrayList<>();

    public WaveManager(Playing playing) {
        this.playing = playing;
        createWave();
    }

    private void createWave() {

        wave.add(new Wave(new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0,0,1))));

    }

    public ArrayList<Wave> getWave() {
        return wave;
    }

}
