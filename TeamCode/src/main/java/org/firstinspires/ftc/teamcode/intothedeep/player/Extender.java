package org.firstinspires.ftc.teamcode.intothedeep.player;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.player.Govenor;
import org.firstinspires.ftc.teamcode.intothedeep.core.ExtMotor;
import org.firstinspires.ftc.teamcode.intothedeep.core.LiftMotors;

public class Extender {
    static LinearOpMode _linearOpMode;

    static Govenor _govenor;
    static final double _max_govenor = 1;
    static final double _min_govenor = 0.6;

    static final int minEncoderLimit = -600;

    public static void initialize(LinearOpMode linearOpMode, DcMotor.RunMode runMode) {
        _linearOpMode = linearOpMode;
        _govenor = new Govenor(_max_govenor, _min_govenor, true);
        ExtMotor.initialize(linearOpMode.hardwareMap, runMode);
    }

    public static void run() {
        if (_govenor.setActive(_linearOpMode.gamepad2.x ? 1 : 0)) _linearOpMode.sleep(_govenor.getSleepDelay());


        double power = 0;
//        if (_linearOpMode.gamepad2.dpad_up && LiftMotors.getCurrentPosition() < -2000) {
//            if (ExtMotor.getCurrentPosition() > minEncoderLimit) power = -0.5;
//        } else if (_linearOpMode.gamepad2.dpad_down && LiftMotors.getCurrentPosition() < -390) {
//            // moving out
//            if (ExtMotor.getCurrentPosition() > -535) power = -0.4;
//            // moving in
//            else if (ExtMotor.getCurrentPosition() < -545) power = 0.4;
//        } else if ((_linearOpMode.gamepad2.dpad_left || _linearOpMode.gamepad2.dpad_right) && LiftMotors.getCurrentPosition() < -680) {
//            if (ExtMotor.getCurrentPosition() > -820) power = -0.5;
//            else if (ExtMotor.getCurrentPosition() < -860) power = 0.5;
//        } else {
            power = _linearOpMode.gamepad2.right_stick_y;
            if (LiftMotors.getCurrentPosition()< -4400) {
              if (ExtMotor.getCurrentPosition() < -50) power = 1;
              else power = 0;
            }
//        else if (LiftMotors.getCurrentPosition()> -4100 && ExtMotor.getCurrentPosition()< minEncoderLimit) power = 1;
//        }

        int encoderLimit = (LiftMotors.getCurrentPosition()<= -3500 && LiftMotors.getCurrentPosition()> -4400)? -900: minEncoderLimit;
        if (power < 0 && ExtMotor.getCurrentPosition() <= encoderLimit) power = 0;

        ExtMotor.setPower(power * _govenor.getActive());
    }

    public static String getActiveGovenor() {
        return _govenor.toString();
    }
}
