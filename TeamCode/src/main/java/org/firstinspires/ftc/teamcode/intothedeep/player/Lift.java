package org.firstinspires.ftc.teamcode.intothedeep.player;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.player.Govenor;
import org.firstinspires.ftc.teamcode.intothedeep.core.LiftMotors;

public class Lift {
    static LinearOpMode _linearOpMode;

    static final int minEncoderLimit = -3850;
    static final int submersableBarTargetLimit = -850;

    static Govenor _govenor;
    static final double _max_govenor = 0.6;
    static final double _min_govenor = 0.35;

    public static void initialize(LinearOpMode linearOpMode, DcMotor.RunMode runMode) {
        _linearOpMode = linearOpMode;
        _govenor = new Govenor(_max_govenor, _min_govenor, true);
        LiftMotors.initialize(linearOpMode.hardwareMap, runMode);
    }

    public static void run() {
//        if (_govenor.setActive(_linearOpMode.gamepad2.left_bumper ? 1 : 0)) _linearOpMode.sleep(_govenor.getSleepDelay());

        double power = _linearOpMode.gamepad2.left_stick_y;// * _govenor.getActive();
        double currentPosition = LiftMotors.getCurrentPosition();

        if (power < 0 && currentPosition <= minEncoderLimit) power = 0;
        else if (Intake.isClosed() && currentPosition > submersableBarTargetLimit && _linearOpMode.gamepad1.right_stick_y > 0) power = -1;

        LiftMotors.setPower(power);
    }

//    public static String getActiveGovenor() {
//        return _govenor.toString();
//    }
}
