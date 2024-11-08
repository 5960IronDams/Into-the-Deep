package org.firstinspires.ftc.teamcode.intothedeep.core;

import android.util.Log;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Hashtable;
import java.util.Map;

public class LiftMotors {
    static DcMotor _mLeft;
//    static DcMotor _mRight;

    public static void initialize(HardwareMap hardwareMap, DcMotor.RunMode runMode) {
        _mLeft = hardwareMap.get(DcMotor.class, "lftl");
//        _mRight = hardwareMap.get(DcMotor.class, "lftr");

        _mLeft.setDirection(DcMotor.Direction.REVERSE);
//        _mRight.setDirection(DcMotor.Direction.REVERSE);

        _mLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        _mRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        setMode(runMode);
    }

    public static void setMode(DcMotor.RunMode mode) {
        _mLeft.setMode(mode);
//        _mRight.setMode(mode);
    }

    public static void stop() {
        setPower(0);
    }

//    public static void setPower(double power) {
//        setPower(power, power);
//    }

    public static void setPower(double left) {
        _mLeft.setPower(left);
//        _mRight.setPower(right);
    }

    public static Map<String, Double> getPowers() {
        Hashtable<String, Double> powers = new Hashtable<>();
//        powers.put("right", _mRight.getPower());
        powers.put("left", _mLeft.getPower());
        return powers;
    }

    public static double getPower() { return _mLeft.getPower(); }

    public static void setPositionTolerance(int tolerance) {
        ((DcMotorEx) _mLeft).setTargetPositionTolerance(tolerance);
//        ((DcMotorEx) _mRight).setTargetPositionTolerance(tolerance);
    }

    public static boolean isBusy() {
        return _mLeft.isBusy();
    }

    public static int getCurrentPosition() {
        return _mLeft.getCurrentPosition();
    }

    public static Map<String, Integer> getCurrentPositions() {
        Hashtable<String, Integer> positions = new Hashtable<>();
        positions.put("left", _mLeft.getCurrentPosition());
        return positions;
    }

    public static void setTargetPosition(int left) {
        _mLeft.setTargetPosition(left);
    }
}
