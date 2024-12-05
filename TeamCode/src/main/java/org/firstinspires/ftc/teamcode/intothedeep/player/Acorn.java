package org.firstinspires.ftc.teamcode.intothedeep.player;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

public class Acorn {
    static LinearOpMode _linearOpMode;
    static Servo _sAcornWrist;
    static CRServo _sAcornExt;
    static Servo _sAcornClaw;
//    public static boolean _openOrClosed;
    final static double _acornLowFlickPosition = 1;
    final static double _acornMiddleFlickPosition = 0.5f;
    final static double _acornHighFlickPosition = 0;
    final static double _acornClawClosed = 0;
    final static double _acornClawOpen = 0.5;

    public static void initialize(LinearOpMode linearOpMode) {
        _linearOpMode = linearOpMode;

        _sAcornWrist = linearOpMode.hardwareMap.servo.get("acorn_wrist");
        _sAcornClaw = linearOpMode.hardwareMap.servo.get("acorn_claw");
        _sAcornExt = linearOpMode.hardwareMap.crservo.get("acorn_ext");
    }
    public static void setWristPosition(double pos) {
        _sAcornWrist.setPosition(pos);
    }

    public static void setClawPosition(double pos) {
        _sAcornClaw.setPosition(pos);
    }

    public static void run(double position) {
        _sAcornWrist.setPosition(position);
    }

    public static double getWristPosition() {
        return _sAcornWrist.getPosition();
    }
    public static double getClawPosition() {
        return _sAcornClaw.getPosition();
    }

    public static void run() {
        if (_linearOpMode.gamepad2.left_stick_y < 0) {
            setWristPosition(_acornMiddleFlickPosition);
        } else if (_linearOpMode.gamepad2.left_stick_y > 0) {
            setWristPosition(_acornLowFlickPosition);
        } else if(_linearOpMode.gamepad2.left_stick_button) {
            setWristPosition(_acornHighFlickPosition);
        }

        if(_linearOpMode.gamepad2.right_stick_y > 0){
            setWristPosition(_acornMiddleFlickPosition);
        }

        _sAcornExt.setPower(_linearOpMode.gamepad2.right_stick_y);

        if(_linearOpMode.gamepad2.left_bumper){
            setClawPosition(_acornClawClosed);
        }else if(_linearOpMode.gamepad2.right_bumper){
            setClawPosition(_acornClawOpen);
        } else if (_linearOpMode.gamepad2.right_stick_button) {
            setClawPosition(0.18);
        }
    }
}
