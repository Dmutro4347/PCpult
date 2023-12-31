package com.newenergy.arfors.pcpult;

public class Device {

    private boolean flMute;
    private boolean flPause;
    private int volume;
    private int maxVolume;

    private int minVolume;

    public boolean isFlMute() {
        return flMute;
    }

    public int getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(int max_volume) {
        this.maxVolume = max_volume;
    }

    public int getMinVolume() {
        return minVolume;
    }

    public void setMinVolume(int min_volume) {
        this.minVolume = min_volume;
    }

    public void setFlMute(boolean flMute) {
        this.flMute = flMute;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }


    public boolean isFlPause() {
        return flPause;
    }

    public void setFlPause(boolean flPause) {
        this.flPause = flPause;
    }

    public Device(int volume, int maxVolume, int minVolume, int brightnes, int maxBrightnes, int minBrightnes) {
        this.volume = volume;
        this.maxVolume = maxVolume;
        this.minVolume = minVolume;
        this.flMute = false;
        this.flPause = false;

    }

}

