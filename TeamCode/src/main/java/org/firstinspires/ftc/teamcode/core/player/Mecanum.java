package org.firstinspires.ftc.teamcode.core.player;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.Drive;

import java.util.Dictionary;
import java.util.Hashtable;

public class Mecanum {
    static LinearOpMode _linearOpMode;

    static Govenor _govenor;
    static final double _max_govenor = 1.0;
    static final double _min_govenor = 0.5;

    public static void initialize(LinearOpMode linearOpMode) {
        _linearOpMode = linearOpMode;
        _govenor = new Govenor(_max_govenor, _min_govenor, false);
        Drive.initialize(linearOpMode.hardwareMap, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    static Dictionary<String, Double> getPowers(double vertical, double horizontal, double pivot) {
        Dictionary<String, Double> powers = new Hashtable<>();
        powers.put("flp", (pivot + vertical + horizontal) * _govenor.getActive());
        powers.put("frp", (-pivot + (vertical - horizontal)) * _govenor.getActive());
        powers.put("rlp", (pivot + (vertical - horizontal)) * _govenor.getActive());
        powers.put("rrp", (-pivot + vertical + horizontal) * _govenor.getActive());

        return powers;
    }

    public static void drive() {
        float _isPressingTriggers = _linearOpMode.gamepad1.left_trigger + _linearOpMode.gamepad1.right_trigger;
        if (_govenor.setActive(_isPressingTriggers)) _linearOpMode.sleep(_govenor.getSleepDelay());

        _linearOpMode.telemetry.addData("Throttle", _govenor.toString());

        Dictionary<String, Double> powers = getPowers(_linearOpMode.gamepad1.right_stick_y,
                -_linearOpMode.gamepad1.right_stick_x, -_linearOpMode.gamepad1.left_stick_x);
        Drive.setPower(powers.get("flp"), powers.get("frp"), powers.get("rlp"), powers.get("rrp"));

        Dictionary<String, Double> motors = Drive.getPowers();

        _linearOpMode.telemetry.addData("flp",motors.get("flp"));
        _linearOpMode.telemetry.addData("frp",motors.get("frp"));
        _linearOpMode.telemetry.addData("rlp",motors.get("rlp"));
        _linearOpMode.telemetry.addData("rrp",motors.get("rrp"));
    }
}
