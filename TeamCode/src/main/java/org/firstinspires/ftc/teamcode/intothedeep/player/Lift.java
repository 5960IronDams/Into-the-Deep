package org.firstinspires.ftc.teamcode.intothedeep.player;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.intothedeep.core.LiftMotors;

public class Lift {
    static LinearOpMode _linearOpMode;
    static double _govenor = 1;

    public static void initialize(LinearOpMode linearOpMode, DcMotor.RunMode runMode) {
        _linearOpMode = linearOpMode;
        LiftMotors.initialize(linearOpMode.hardwareMap, runMode);
    }

    public static void run() {
        double power = _linearOpMode.gamepad2.left_stick_y * _govenor;

        LiftMotors.setPower(power);
        _linearOpMode.telemetry.addLine("Lift");
        _linearOpMode.telemetry.addData("Power", power);
        _linearOpMode.telemetry.addData("Encoder", LiftMotors.getCurrentPosition());
    }
}
