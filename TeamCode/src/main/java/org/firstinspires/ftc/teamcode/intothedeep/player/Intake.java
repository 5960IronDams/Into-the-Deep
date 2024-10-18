package org.firstinspires.ftc.teamcode.intothedeep.player;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    static LinearOpMode _linearOpMode;
    static Servo _mClaw;
    public final static double _clawMinimumRange = 0.15;
    public final static double _clawMaxRange = 1.5;

    public static void initialize(LinearOpMode linearOpMode) {
        _linearOpMode = linearOpMode;
        _mClaw = linearOpMode.hardwareMap.servo.get("claw");
        _mClaw.setPosition(_clawMinimumRange);
        _linearOpMode.telemetry.addData("ClawPosition", _mClaw.getPosition());
    }

    public static void open (){
        _mClaw.setPosition(_clawMaxRange);
    }

    public static void close (){
        _mClaw.setPosition(_clawMinimumRange);
    }
    public static double getPosition() {
        return _mClaw.getPosition();
    }

    public static void run() {
        if (_linearOpMode.gamepad2.left_bumper) {
            _mClaw.setPosition(_clawMinimumRange);
        } else if (_linearOpMode.gamepad2.right_bumper) {
            _mClaw.setPosition(_clawMaxRange);
        }
    }
}
