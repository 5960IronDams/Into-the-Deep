package org.firstinspires.ftc.teamcode.intothedeep.player;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.player.Govenor;
import org.firstinspires.ftc.teamcode.intothedeep.autonomous.Utility;
import org.firstinspires.ftc.teamcode.intothedeep.core.ExtMotor;
import org.firstinspires.ftc.teamcode.intothedeep.core.LiftMotors;

public class Extender {
    static LinearOpMode _linearOpMode;

    static Govenor _govenor;
    static final double _max_govenor = 1;
    static final double _min_govenor = 0.6;

    public static void initialize(LinearOpMode linearOpMode, DcMotor.RunMode runMode) {
        _linearOpMode = linearOpMode;
        _govenor = new Govenor(_max_govenor, _min_govenor, true);
        ExtMotor.initialize(linearOpMode.hardwareMap, runMode);
    }

    public static void run() {
        if (_govenor.setActive(_linearOpMode.gamepad2.x ? 1 : 0)) _linearOpMode.sleep(_govenor.getSleepDelay());

        double power = 0;

        // If we are in the range of the high basket we will allow full extension.
        int encoderLimit = (LiftMotors.getCurrentPosition() <= -4100 && LiftMotors.getCurrentPosition() > -4400) ? -900: Utility.EXTENDER_REACH;
        // If they are going to hang we want to bring in the claw.
        if (LiftMotors.getCurrentPosition() < -4400) {
            if (ExtMotor.getCurrentPosition() < 25) {
                power = 1;
            } else {
                power = 0;
            }
        // If the extender reaches the max limit then shut it down
        } else if (power < 0 && ExtMotor.getCurrentPosition() <= encoderLimit) {
            power = 0;
        } else {
            // Apply the player's power
            power = _linearOpMode.gamepad2.right_stick_y;
        }

        ExtMotor.setPower(power * _govenor.getActive());
    }

    public static String getActiveGovenor() {
        return _govenor.toString();
    }
}
