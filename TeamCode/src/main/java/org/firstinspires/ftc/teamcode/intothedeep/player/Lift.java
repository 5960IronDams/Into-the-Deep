package org.firstinspires.ftc.teamcode.intothedeep.player;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.intothedeep.core.ExtMotor;
import org.firstinspires.ftc.teamcode.intothedeep.core.LiftMotors;


public class Lift {
    static LinearOpMode _linearOpMode;

    static final int minEncoderLimit = -5470;
    static final int submersableBarTargetLimit = -850;

//    static Govenor _govenor;
    static final double _down_power = 0.20;
//    static final double _min_govenor = 0.35;

    public static void initialize(LinearOpMode linearOpMode, DcMotor.RunMode runMode) {
        _linearOpMode = linearOpMode;
//        _govenor = new Govenor(_down_power, _min_govenor, true);
        LiftMotors.initialize(linearOpMode.hardwareMap, runMode);
    }

    public static void run() {
//        if (_govenor.setActive(_linearOpMode.gamepad2.left_bumper ? 1 : 0)) _linearOpMode.sleep(_govenor.getSleepDelay());
        double power = 0;

//        if (_linearOpMode.gamepad2.dpad_up) {
//            // moving up
//            if (LiftMotors.getCurrentPosition() > -3180) power = -0.5;
//            else if (LiftMotors.getCurrentPosition() < -3225) power = 0.5;
//        } else if (_linearOpMode.gamepad2.dpad_down) {
//            // moving up
//            if (LiftMotors.getCurrentPosition() > -680) power = -0.5;
//            // moving down
//            else if (LiftMotors.getCurrentPosition() < -730) power = 0.5;
//        } else if (_linearOpMode.gamepad2.dpad_left || _linearOpMode.gamepad2.dpad_right) {
//            if (LiftMotors.getCurrentPosition() > -700) power = -0.5;
//            else if (LiftMotors.getCurrentPosition() < -730) power = 0.5;
//        } else {
            power = _linearOpMode.gamepad2.left_stick_y;// * _govenor.getActive();
            double currentPosition = LiftMotors.getCurrentPosition();

            if (currentPosition <= -3500 && ExtMotor.getCurrentPosition()<= -670)
                power = 0;
           else
            if (power < 0 && currentPosition <= minEncoderLimit) power = 0;
            else if (power > 0 && LiftMotors.isTouching()) {
                power = 0;
                LiftMotors.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                LiftMotors.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
            else if (Intake.isClosed() && currentPosition > submersableBarTargetLimit && _linearOpMode.gamepad1.right_stick_y > 0)
                power = -1;
//        }

        LiftMotors.setPower(power);
    }

//    public static String getActiveGovenor() {
//        return _govenor.toString();
//    }
}
