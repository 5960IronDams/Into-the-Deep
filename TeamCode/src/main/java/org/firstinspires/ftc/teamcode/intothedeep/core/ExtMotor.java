package org.firstinspires.ftc.teamcode.intothedeep.core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ExtMotor {
    static DcMotor _mExt;

    public static void initialize(HardwareMap hardwareMap, DcMotor.RunMode runMode) {
        _mExt = hardwareMap.get(DcMotor.class, "ext");

        _mExt.setDirection(DcMotor.Direction.FORWARD);

        _mExt.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        setMode(runMode);
    }

    public static void setMode(DcMotor.RunMode mode) {
        _mExt.setMode(mode);
    }

    public static void stop() {
        setPower(0);
    }

    public static void setPower(double power) {
        _mExt.setPower(power);
    }

    public static double getPower() {
        return  _mExt.getPower();
    }

    public static boolean isBusy() {
        return _mExt.isBusy();
    }

    public static void setPositionTolerance(int tolerance) {
        ((DcMotorEx) _mExt).setTargetPositionTolerance(tolerance);
    }

    public static boolean isAtEncoder() {
        if (_mExt.getPower() < 0)
            return _mExt.getCurrentPosition() <= _mExt.getTargetPosition();
        else if (_mExt.getPower() > 0)
            return _mExt.getCurrentPosition() >= _mExt.getTargetPosition();

        return true;
    }

    public static int getCurrentPosition() {
        return _mExt.getCurrentPosition();
    }

    public static void setTargetPosition(int pos) {
        _mExt.setTargetPosition(pos);
    }
}
