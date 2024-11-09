package org.firstinspires.ftc.teamcode.intothedeep.player;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcontroller.external.samples.BasicOmniOpMode_Linear;
import org.firstinspires.ftc.teamcode.core.player.Govenor;
import org.firstinspires.ftc.teamcode.intothedeep.core.ClipperThingamabobberMoter;
import org.firstinspires.ftc.teamcode.intothedeep.core.ExtMotor;
import org.firstinspires.ftc.teamcode.intothedeep.core.GnashMoter;

public class ClipperThingamabobber {
    static LinearOpMode _linearOpMode;

    static int _govenor;
    static final double _max_govenor = 1;
    static final double _min_govenor = 0.6;

    public static void initialize(LinearOpMode linearOpMode, DcMotor.RunMode runMode) {
        _linearOpMode = linearOpMode;
        _govenor = 1;
        ClipperThingamabobberMoter.initialize(linearOpMode.hardwareMap, runMode);
    }

    public static void run() {
        double power = _linearOpMode.gamepad2.a ? -1: _linearOpMode.gamepad2.y ? 1: 0;
//        double power = _linearOpMode.gamepad2.dpad_down ? -1 : _linearOpMode.gamepad2.dpad_up ? 1 : 0;
        ClipperThingamabobberMoter.setPower(power);

    }
}
