package org.firstinspires.ftc.teamcode.core.autonomous;

import android.util.Log;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class Gyro {
    static BHI260IMU.Parameters _parameters;
    static BHI260IMU _imu;
    static Orientation _angles;

    public static void initialization(HardwareMap hardwareMap) {
        _parameters = new com.qualcomm.robotcore.hardware.IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.FORWARD)
        );

        _imu = hardwareMap.get(BHI260IMU.class, "imu");
        _imu.initialize(_parameters);
    }

    public static void loggingAngles() {
        _angles = _imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        Log.d("5960", "First Angle: " + _angles.firstAngle);
        Log.d("5960", "Second Angle: " + _angles.secondAngle);
        Log.d("5960", "Third Angle: " + _angles.thirdAngle);
    }

    public static double getCurrentDegrees() {
        _angles = _imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        return AngleUnit.DEGREES.fromUnit(_angles.angleUnit, _angles.firstAngle);
    }
}
