
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
    public static int _max_position = 1940;

    public static void initialize(LinearOpMode linearOpMode, DcMotor.RunMode runMode) {
        _linearOpMode = linearOpMode;
        _govenor = new Govenor(_max_govenor, _min_govenor, false);
        GnashMoter.initialize(linearOpMode.hardwareMap, runMode);
    }

    public static void run() {
        double _DPadPower = 0;

        if (_linearOpMode.gamepad1.dpad_up && _max_position < 1940) {
        _max_position += 50;
        _linearOpMode.sleep(300);
        } else if(_linearOpMode.gamepad1.dpad_down && _max_position > 1800){
            _max_position -= 50;
            _linearOpMode.sleep(300);
        }

        if (_linearOpMode.gamepad1.b) {
            GnashMoter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            GnashMoter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        if(_linearOpMode.gamepad2.left_trigger != 0 && GnashMoter.getCurrentPosition() < _max_position) {
            _DPadPower = 1;
        } else if(_linearOpMode.gamepad2.right_trigger != 0) { // && GnashMoter.getCurrentPosition() > 0) {
            _DPadPower = -1;
       // } else if(GnashMoter.getCurrentPosition() > _max_position){
        //    _DPadPower = -1;
        }
        else {
            _DPadPower = 0;
        }

        GnashMoter.setPower(_DPadPower * _govenor.getActive());
    }

    public static String getActiveGovenor() {
        return _govenor.toString();
    }
}
