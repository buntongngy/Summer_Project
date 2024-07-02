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
    private int waveTickLimit = 5 * 60;
    private int waveTick = waveTickLimit;
    private boolean waveStartTimer = false, waveTickTimerOver = false;

    public WaveManager(Playing playing) {
        this.playing = playing;
        createWave();
    }

    public void update() {

        if(enemySpawnTick<enemySpawnTickLimit)
            enemySpawnTick++;

        if(waveStartTimer) {
            waveTick++;
            if(waveTick >= waveTickLimit) {
                waveTickTimerOver = true;
            }
        }
    }

    public void increaseWaveIndex() {
        waveIndex++;
        waveTickTimerOver = false;
        waveStartTimer = false;
    }

    public int getNextEnemy() {
        enemySpawnTick = 0;
        return wave.get(waveIndex).getEnemyList().get(enemyIndex++);
    }

    private void createWave() {
        wave.add(new Wave(new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,1))));
        wave.add(new Wave(new ArrayList<>(Arrays.asList(0,0,0,0,1,1,1,2,2,3))));
    }

    public ArrayList<Wave> getWave() {
        return wave;
    }

    public boolean isTimeNewEnemy() {
        return enemySpawnTick >= enemySpawnTickLimit;
    }

    public void startWaveTimer() {
        waveStartTimer = true;
    }

    public boolean moreEnemyinWave() {
        return enemyIndex < wave.get(waveIndex).getEnemyList().size();
    }

    public boolean isTheremoreWave() {
        return waveIndex + 1 < wave.size();
    }

    public boolean isWaveTimeStart() {
        return waveStartTimer;
    }

    public boolean isWaveTimeOver() {
        return waveTickTimerOver;
    }

    public void resetEnemyIndex() {
        enemyIndex = 0;
    }

    public int getWaveIndex() {
        return waveIndex;
    }

    public float getTimeLeft() {
        float tickLeft = waveTickLimit - waveTick;
        return tickLeft / 60.0f;
    }
}
