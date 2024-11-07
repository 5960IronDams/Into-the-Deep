package org.firstinspires.ftc.teamcode.intothedeep.player;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.player.Govenor;
import org.firstinspires.ftc.teamcode.intothedeep.core.ExtMotor;

public class Extender {
    static LinearOpMode _linearOpMode;

    static Govenor _govenor;
    static final double _max_govenor = 1;
    static final double _min_govenor = 0.6;

    static final int minEncoderLimit = -770;

    public static void initialize(LinearOpMode linearOpMode, DcMotor.RunMode runMode) {
        _linearOpMode = linearOpMode;
        _govenor = new Govenor(_max_govenor, _min_govenor, true);
        ExtMotor.initialize(linearOpMode.hardwareMap, runMode);
    }

    public static void run() {
        if (_govenor.setActive(_linearOpMode.gamepad2.left_bumper? 1 : 0)) _linearOpMode.sleep(_govenor.getSleepDelay());

        double power = _linearOpMode.gamepad2.right_stick_y;
        if (power < 0 && ExtMotor.getCurrentPosition() <= minEncoderLimit) power = 0;

        ExtMotor.setPower(power * _govenor.getActive());
    }

    public static String getActiveGovenor() {
        return _govenor.toString();
    }
}
