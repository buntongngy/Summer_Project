package managers;

import event.Wave;
import scenes.Playing;

import java.util.ArrayList;
import java.util.Arrays;

public class WaveManager {

    private Playing playing;
    private ArrayList<Wave> wave = new ArrayList<>();
    private int enemyIndex = 0, waveIndex = 0;
    private int enemySpawnTickLimit = 60 * 1;
    private int enemySpawnTick = enemySpawnTickLimit;

    public WaveManager(Playing playing) {
        this.playing = playing;
        createWave();
    }

    public void update() {

        if(enemySpawnTick<enemySpawnTickLimit)
        enemySpawnTick++;
    }

    public int getNextEnemy() {

        enemySpawnTick = 0;
       return wave.get(waveIndex).getEnemyList().get(enemyIndex++);

    }

    private void createWave() {

        wave.add(new Wave(new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0,0,1))));

    }

    public ArrayList<Wave> getWave() {
        return wave;
    }

    public boolean isTimeNewEnemy() {

            return enemySpawnTick >= enemySpawnTickLimit;
    }

    public boolean moreEnemyinWave() {
        return enemyIndex < wave.get(waveIndex).getEnemyList().size();
    }
}
