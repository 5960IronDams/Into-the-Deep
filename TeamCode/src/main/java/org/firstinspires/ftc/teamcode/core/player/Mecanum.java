package org.firstinspires.ftc.teamcode.core.player;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.core.Drive;

import java.util.Dictionary;
import java.util.Hashtable;

public class Mecanum {
    static LinearOpMode _LinearOpMode;
    Drive _drive;

    static double _govenor = 1.0;

    public static void initialize(LinearOpMode LinearOpMode){
        _LinearOpMode = LinearOpMode;
    }
    static boolean setGovenor(float isTriggered){
        if (isTriggered != 0) {
            if (_govenor == 1.0) _govenor = 0.5;
            else _govenor = 1.0;
            return true;
        }

        return false;
    }

    static Dictionary<String, Double> getPowers(double vertical, double horizontal, double pivot) {
        Dictionary<String, Double> powers = new Hashtable<>();
        double flp = (pivot + vertical + horizontal) * _govenor;
        double frp = (-pivot + (vertical - horizontal)) * _govenor;
        double rlp = (pivot + (vertical - horizontal)) * _govenor;
        double rrp = (-pivot + vertical + horizontal) * _govenor;

        return powers;
    }
    private void mecanumDrive() {
        //double govenor = 1.0;


        double vertical = gamepad1.right_stick_y;
        double horizontal = -gamepad1.right_stick_x;
        double pivot = -gamepad1.left_stick_x;



        if ((flp > 0 || frp > 0 || rlp > 0 || rrp > 0) && _limiter.isPressed()) {
            _drive.stop();
        } else {
            _drive.setPower(flp, frp, rlp, rrp);
        }

        telemetry.addData("Drive Mode", _govenor == 1.0 ? "Fast" : "Slow");
    }
}

