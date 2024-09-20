package org.firstinspires.ftc.teamcode.intothedeep.player;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;

public class Intake {
    static LinearOpMode _linearOpMode;
    static CRServo _servo;

    public static void initialize(LinearOpMode linearOpMode) {
        _linearOpMode = linearOpMode;
        _servo = linearOpMode.hardwareMap.get(CRServo.class, "crs");
    }

    public static void run() {
        if (_linearOpMode.gamepad2.left_bumper) {
            _servo.setPower(1);
        } else if (_linearOpMode.gamepad2.right_bumper) {
            _servo.setPower(-1);
        } else {
            _servo.setPower(0);
        }
    }
}
