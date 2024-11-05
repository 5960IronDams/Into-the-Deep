
package org.firstinspires.ftc.teamcode.intothedeep.player;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.player.Govenor;
import org.firstinspires.ftc.teamcode.intothedeep.core.ExtMotor;
import org.firstinspires.ftc.teamcode.intothedeep.core.GnashMoter;

public class Gnasher {
    static LinearOpMode _linearOpMode;

    static Govenor _govenor;
    static final double _max_govenor = 1;
    static final double _min_govenor = 0.6;

    public static void initialize(LinearOpMode linearOpMode, DcMotor.RunMode runMode) {
        _linearOpMode = linearOpMode;
        _govenor = new Govenor(_max_govenor, _min_govenor, false);
        GnashMoter.initialize(linearOpMode.hardwareMap, runMode);
    }

    public static void run() {
        double _DPadPower = 0;
        if(_govenor.setActive(_linearOpMode.gamepad2.left_trigger)){
            _DPadPower = 1;
        }else if(_govenor.setActive(_linearOpMode.gamepad2.right_trigger)){
            _DPadPower = -1;
        }else{
            _DPadPower = 0;
        }
//        if (_govenor.setActive(_linearOpMode.gamepad2.right_trigger)) _linearOpMode.sleep(_govenor.getSleepDelay());
        GnashMoter.setPower(_DPadPower * _govenor.getActive());
    }

    public static String getActiveGovenor() {
        return _govenor.toString();
    }
}
