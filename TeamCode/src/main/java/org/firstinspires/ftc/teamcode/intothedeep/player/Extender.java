package org.firstinspires.ftc.teamcode.intothedeep.player;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.intothedeep.core.ExtMotor;

public class Extender {
    static LinearOpMode _linearOpMode;
    static double _govenor = 1;

    public static void initialize(LinearOpMode linearOpMode, DcMotor.RunMode runMode) {
        _linearOpMode = linearOpMode;
        ExtMotor.initialize(linearOpMode.hardwareMap, runMode);
    }

    public static void run() {
        ExtMotor.setPower(_linearOpMode.gamepad2.right_stick_y * _govenor);
    }
}
