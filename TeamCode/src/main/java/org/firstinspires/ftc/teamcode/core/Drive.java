package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Hashtable;
import java.util.Map;

public class Drive {
    static DcMotor _mFrontLeft;
    static DcMotor _mFrontRight;
    static DcMotor _mRearLeft;
    static DcMotor _mRearRight;

    public static void initialize(HardwareMap hardwareMap, DcMotor.RunMode runMode) {
        _mFrontLeft = hardwareMap.get(DcMotor.class, "fl");
        _mFrontRight = hardwareMap.get(DcMotor.class, "fr");
        _mRearLeft = hardwareMap.get(DcMotor.class, "rl");
        _mRearRight = hardwareMap.get(DcMotor.class, "rr");

        _mFrontLeft.setDirection(DcMotor.Direction.FORWARD);
        _mRearLeft.setDirection(DcMotor.Direction.FORWARD);
        _mFrontRight.setDirection(DcMotor.Direction.REVERSE);
        _mRearRight.setDirection(DcMotor.Direction.REVERSE);

        _mFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        _mRearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        _mFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        _mRearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        setMode(runMode);
    }

    public static void setMode(DcMotor.RunMode mode) {
        _mFrontLeft.setMode(mode);
        _mFrontRight.setMode(mode);
        _mRearLeft.setMode(mode);
        _mRearRight.setMode(mode);
    }

    public static void stop() {
        setPower(0);
    }

    public static void setPower(double power) {
        setPower(power, power, power, power);
    }

    public static void setPower(double flp, double frp, double rlp, double rrp) {
        _mFrontLeft.setPower(flp);
        _mFrontRight.setPower(frp);
        _mRearLeft.setPower(rlp);
        _mRearRight.setPower(rrp);
    }

    public static void setRRPower(double power) {
        _mRearRight.setPower(power);
    }

    public static void setRLPower(double power) {
        _mRearLeft.setPower(power);
    }

    public static void setFRPower(double power) {
        _mFrontRight.setPower(power);
    }

    public static void setFLPower(double power) {
        _mFrontLeft.setPower(power);
    }

    public static void setPositionTolerance(int tolerance) {
        ((DcMotorEx) _mFrontLeft).setTargetPositionTolerance(tolerance);
        ((DcMotorEx) _mFrontRight).setTargetPositionTolerance(tolerance);
        ((DcMotorEx) _mRearLeft).setTargetPositionTolerance(tolerance);
        ((DcMotorEx) _mRearRight).setTargetPositionTolerance(tolerance);
    }

    public static boolean isBusy() {
        return _mFrontLeft.isBusy(); //|| _mFrontRight.isBusy() || _mRearLeft.isBusy() || _mRearRight.isBusy();
    }

    public static boolean isAtEncoder() {
        return Math.abs(_mFrontLeft.getCurrentPosition()) >= Math.abs(_mFrontLeft.getTargetPosition()) ||
                Math.abs(_mFrontRight.getCurrentPosition()) >= Math.abs(_mFrontRight.getTargetPosition());
//        if (_mFrontLeft.getPower() < 0)
//            return _mFrontLeft.getCurrentPosition() <= _mFrontLeft.getTargetPosition();
//        else if (_mFrontLeft.getPower() > 0)
//            return _mFrontLeft.getCurrentPosition() >= _mFrontLeft.getTargetPosition();
//
//        return true;
    }

    public static int getCurrentPosition() {
        return _mFrontLeft.getCurrentPosition();
    }

    public static Map<String, Integer> getCurrentPositions() {
        Map<String, Integer> positions = new Hashtable<>();
        positions.put("flp", _mFrontLeft.getCurrentPosition());
        positions.put("frp", _mFrontRight.getCurrentPosition());
        positions.put("rlp", _mRearLeft.getCurrentPosition());
        positions.put("rrp", -_mRearRight.getCurrentPosition());

        return positions;
    }

    public static void setTargetPosition(int pos) {
        setTargetPosition(pos, pos, pos, pos);
    }

    public static void setTargetPosition(int flp, int frp, int rlp, int rrp) {
        _mFrontLeft.setTargetPosition(flp);
        _mFrontRight.setTargetPosition(frp);
        _mRearLeft.setTargetPosition(rlp);
        _mRearRight.setTargetPosition(rrp);
    }
    public static Map<String, Double> getPowers(){
        Map<String, Double> powers = new Hashtable<>();
        powers.put("flp", _mFrontLeft.getPower());
        powers.put("frp", _mFrontRight.getPower());
        powers.put("rlp", _mRearLeft.getPower());
        powers.put("rrp", -_mRearRight.getPower());

        return powers;
    }
}
