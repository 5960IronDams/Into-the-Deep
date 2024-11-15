package org.firstinspires.ftc.teamcode.intothedeep.player;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    static LinearOpMode _linearOpMode;
    static Servo _mClaw;
//    public static boolean _openOrClosed;
    final static double _clawOpenPosition = 1;
    final static double _clawClosedPosition = 0;

    public static void initialize(LinearOpMode linearOpMode) {
        _linearOpMode = linearOpMode;
        _mClaw = linearOpMode.hardwareMap.servo.get("claw");
    }

    public static boolean isClosed() { return _mClaw.getPosition() == _clawClosedPosition; }
//    public static boolean isOpen() { return _mClaw.getPosition() == _clawOpenPosition; }

    public static void open() {
        _mClaw.setPosition(_clawOpenPosition);
    }

    public static void close() {
        _mClaw.setPosition(_clawClosedPosition);
    }

    public static void run(double position) {
        _mClaw.setPosition(position);
    }

    public static double getPosition() {
        return _mClaw.getPosition();
    }

    public static void run() {
        if (_linearOpMode.gamepad2.right_bumper) {
            open();
        } else if (_linearOpMode.gamepad2.left_bumper) {
            close();
        }
    }
}
