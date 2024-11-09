package org.firstinspires.ftc.teamcode.core.autonomous;

import android.util.Log;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class ControlHubGyro {
    static BHI260IMU.Parameters _parameters;
    static BHI260IMU _imu;
    static Orientation _angles;

    static double _previousAngle = 0;
    static double _offsetAngle = 0;

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

    public static double getOffsetAngle() { return _offsetAngle; }
    public static void resetOffsetAngle() {
        _offsetAngle = 0;
        _previousAngle = 0;
    }

    public static double getPreviousAngle() { return _previousAngle; }


    public static double getCurrentDegrees() {
        _angles = _imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double currentAngle = Math.abs(AngleUnit.DEGREES.fromUnit(_angles.angleUnit, _angles.firstAngle));

        if (_previousAngle != 0) {
            double gap = 0;
            if (currentAngle > _previousAngle) gap = currentAngle - _previousAngle;
            else gap = _previousAngle - currentAngle;

            Log.i("5960", "=== BEGIN ===");
            Log.i("5960", "currentAngle:" + currentAngle);
            Log.i("5960", "_previousAngle:" + _previousAngle);
            Log.i("5960", "Gap:" + gap);

            if (gap > 120) {
                Log.i("5960", "Gap > 120:" + gap);
                Log.i("5960", "=== END ===");
                return _offsetAngle; //gap = 360 - _previousAngle - currentAngle;
            }

            _offsetAngle += gap;
        }
        Log.i("5960", "currentAngle:" + currentAngle);
        Log.i("5960", "_previousAngle:" + _previousAngle);

        loggingAngles();

        _previousAngle = currentAngle;
        Log.i("5960", "=== END ===");
        return _offsetAngle;
    }
}