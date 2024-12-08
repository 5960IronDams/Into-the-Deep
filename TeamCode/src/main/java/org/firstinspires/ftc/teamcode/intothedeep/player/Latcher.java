package org.firstinspires.ftc.teamcode.intothedeep.player;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class Latcher {
    static LinearOpMode _linearOpMode;
    static Servo _mLatcher;
    public static boolean _openOrClosed;
    public final static double _clawMinimumRange = 0;
    public final static double _clawMaxRange = 0;

    public static void initialize(LinearOpMode linearOpMode) {
        _linearOpMode = linearOpMode;
        _mLatcher = linearOpMode.hardwareMap.servo.get("Latcher");
    }

//    public static void open (){
//        _mLatcher.setPosition(_clawMaxRange);
//    }

    public static void close (){
        _mLatcher.setPosition(_clawMinimumRange);
    }

//    public static boolean getOpenOrClosed() {
//        return _openOrClosed;
//    }
//    public static double getPosition() {
//        return _mLatcher.getPosition();
//    }

    public static void run() {

        if (_linearOpMode.gamepad2.right_bumper) {
            _mLatcher.setPosition(_clawMaxRange);
            _openOrClosed = true;
        } else {
            _mLatcher.setPosition(_clawMinimumRange);
            _openOrClosed = false;
        }
    }
}
