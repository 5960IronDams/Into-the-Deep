package org.firstinspires.ftc.teamcode.core.player;

import androidx.annotation.NonNull;

public class Govenor {
    double _max;
    double _min;

    long _sleep_delay = 350;

    double _active;

    public Govenor(double max, double min, boolean defaultToMin) {
        _max = max;
        _min = min;
        _active = defaultToMin ? _min : _max;
    }

    public void setSleepDelay(long delay) {
        _sleep_delay = delay;
    }

    public long getSleepDelay() {
        return _sleep_delay;
    }

    public void setMax(double max) {
        _max = max;
    }

    public double getMax() {
        return _max;
    }

    public void setMin(double min) {
        _min = min;
    }

    public double getMin() {
        return _min;
    }

    public boolean setActive(float isTriggered) {
        if (isTriggered != 0) {
            if (_active == _max) _active = _min;
            else _active = _max;
            return true;
        }

        return false;
    }

    public double getActive() {
        return _active;
    }

    @NonNull
    public String toString() {
        return (_active * 100) + "%";
    }
}
