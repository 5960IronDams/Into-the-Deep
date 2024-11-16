package org.firstinspires.ftc.teamcode.intothedeep.player;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.intothedeep.autonomous.Utility;
import org.firstinspires.ftc.teamcode.intothedeep.core.ExtMotor;
import org.firstinspires.ftc.teamcode.intothedeep.core.LiftMotors;


public class Lift {
    static LinearOpMode _linearOpMode;

    static final int minEncoderLimit = -5300;
    static final int submersibleBarTargetLimit = -850;

    public static void initialize(LinearOpMode linearOpMode, DcMotor.RunMode runMode) {
        _linearOpMode = linearOpMode;
        LiftMotors.initialize(linearOpMode.hardwareMap, runMode);
    }

    public static void run() {
        double power = 0;
        double currentPosition = LiftMotors.getCurrentPosition();

        if (ExtMotor.getCurrentPosition() < Utility.EXTENDER_REACH) {
        // The claw is fully extended so we can't move the lift until if comes in
            power = 0;
        } else if (power < 0 && currentPosition <= minEncoderLimit) {
        // The lift is all the way up, typically when we climb
            power = 0;
        } else if (power > 0 && LiftMotors.isTouching()) {
        // The lift is all the way down
            power = 0;
            LiftMotors.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            LiftMotors.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        } else if (Intake.isClosed() && currentPosition > submersibleBarTargetLimit && _linearOpMode.gamepad1.right_stick_y > 0) {
        // The claw is closed and the lift has it below the submersible so we want to lift it up a bit
            power = -1;
        } else {
        // Player power
            power = _linearOpMode.gamepad2.left_stick_y;
        }

        LiftMotors.setPower(power);
    }

//    public static String getActiveGovenor() {
//        return _govenor.toString();
//    }
}
