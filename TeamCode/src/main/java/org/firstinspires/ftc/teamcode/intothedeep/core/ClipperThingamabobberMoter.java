package org.firstinspires.ftc.teamcode.intothedeep.core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.intothedeep.player.ClipperThingamabobber;

public class ClipperThingamabobberMoter {
    static DcMotor _mClipperThingamabobber;

    public static void initialize(HardwareMap hardwareMap, DcMotor.RunMode runMode) {
        _mClipperThingamabobber = hardwareMap.get(DcMotor.class, "clipper_thingamabobber");

        _mClipperThingamabobber.setDirection(DcMotor.Direction.FORWARD);

        _mClipperThingamabobber.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        setMode(runMode);
    }

    public static void setMode(DcMotor.RunMode mode) {
        _mClipperThingamabobber.setMode(mode);
    }

    public static void stop() {
        setPower(0);
    }

    public static void setPower(double power) {
        _mClipperThingamabobber.setPower(power);
    }

    public static double getPower() {
        return  _mClipperThingamabobber.getPower();
    }

    public static boolean isBusy() {
        return _mClipperThingamabobber.isBusy();
    }

    public static void setPositionTolerance(int tolerance) {
        ((DcMotorEx) _mClipperThingamabobber).setTargetPositionTolerance(tolerance);
    }

    public static int getCurrentPosition() {
        return _mClipperThingamabobber.getCurrentPosition();
    }

    public static void setTargetPosition(int pos) {
        _mClipperThingamabobber.setTargetPosition(pos);
    }
}
