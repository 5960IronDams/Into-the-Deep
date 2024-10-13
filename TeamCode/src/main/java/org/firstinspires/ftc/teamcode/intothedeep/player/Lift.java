package org.firstinspires.ftc.teamcode.intothedeep.player;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.player.Govenor;
import org.firstinspires.ftc.teamcode.intothedeep.core.LiftMotors;

public class Lift {
    static LinearOpMode _linearOpMode;

    static Govenor _govenor;
    static final double _max_govenor = 0.6;
    static final double _min_govenor = 0.35;

    public static void initialize(LinearOpMode linearOpMode, DcMotor.RunMode runMode) {
        _linearOpMode = linearOpMode;
        _govenor = new Govenor(_max_govenor, _min_govenor, true);
        LiftMotors.initialize(linearOpMode.hardwareMap, runMode);
    }

    public static void run() {
        if (_govenor.setActive(_linearOpMode.gamepad2.left_trigger)) _linearOpMode.sleep(_govenor.getSleepDelay());
        double power = _linearOpMode.gamepad2.left_stick_y * _govenor.getActive();

        LiftMotors.setPower(power);
        _linearOpMode.telemetry.addLine("Lift");
        _linearOpMode.telemetry.addData("Power", power);
        _linearOpMode.telemetry.addData("Encoder", LiftMotors.getCurrentPosition());
    }

    public static String getActiveGovenor() {
        return _govenor.toString();
    }
}
