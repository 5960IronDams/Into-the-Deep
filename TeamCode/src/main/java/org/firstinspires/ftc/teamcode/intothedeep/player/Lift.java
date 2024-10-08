package org.firstinspires.ftc.teamcode.intothedeep.player;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.intothedeep.core.LiftMotors;

public class Lift {
    static LinearOpMode _linearOpMode;
    static final double _max_govenor = 0.6;
    static final double _min_govenor = 0.35;
    static final long _govenor_sleep_delay = 350;

    static double _govenor = _min_govenor;

    public static void initialize(LinearOpMode linearOpMode, DcMotor.RunMode runMode) {
        _linearOpMode = linearOpMode;
        LiftMotors.initialize(linearOpMode.hardwareMap, runMode);
    }

    static boolean setGovenor(float isTriggered) {
        if (isTriggered != 0) {
            if (_govenor == _max_govenor) _govenor = _min_govenor;
            else _govenor = _max_govenor;
            return true;
        }

        return false;
    }

    static String getGovenorPercentage() {
        return (_govenor * 100) + "%";
    }
    public static void run() {
        if (setGovenor(_linearOpMode.gamepad2.left_trigger)) _linearOpMode.sleep(_govenor_sleep_delay);
        double power = _linearOpMode.gamepad2.left_stick_y * _govenor;

        LiftMotors.setPower(power);
        _linearOpMode.telemetry.addLine("Lift");
        _linearOpMode.telemetry.addData("Power", power);
        _linearOpMode.telemetry.addData("Encoder", LiftMotors.getCurrentPosition());
    }
}
