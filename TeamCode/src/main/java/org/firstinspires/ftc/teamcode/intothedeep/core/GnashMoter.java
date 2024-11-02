package org.firstinspires.ftc.teamcode.intothedeep.core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class GnashMoter {
    static DcMotor _mGnash;

    public static void initialize(HardwareMap hardwareMap, DcMotor.RunMode runMode) {
        _mGnash = hardwareMap.get(DcMotor.class, "The_Ghastly_Gnasher");

        _mGnash.setDirection(DcMotor.Direction.FORWARD);

        _mGnash.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        setMode(runMode);
    }

    public static void setMode(DcMotor.RunMode mode) {
        _mGnash.setMode(mode);
    }

    public static void stop() {
        setPower(0);
    }

    public static void setPower(double power) {
        _mGnash.setPower(power);
    }

    public static double getPower() {
        return  _mGnash.getPower();
    }

    public static boolean isBusy() {
        return _mGnash.isBusy();
    }

    public static void setPositionTolerance(int tolerance) {
        ((DcMotorEx) _mGnash).setTargetPositionTolerance(tolerance);
    }

    public static int getCurrentPosition() {
        return _mGnash.getCurrentPosition();
    }

    public static void setTargetPosition(int pos) {
        _mGnash.setTargetPosition(pos);
    }
}
