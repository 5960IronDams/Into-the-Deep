package org.firstinspires.ftc.teamcode.intothedeep.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.Drive;
import org.firstinspires.ftc.teamcode.intothedeep.core.ExtMotor;
import org.firstinspires.ftc.teamcode.intothedeep.core.LiftMotors;

public class SubSystem {
    static LinearOpMode _linearOpMode;
    static final int positionTolerence = 100;

    public static void initialize(LinearOpMode linearOpMode)
    {
        _linearOpMode = linearOpMode;
        Drive.initialize(linearOpMode.hardwareMap, DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LiftMotors.initialize(linearOpMode.hardwareMap, DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ExtMotor.initialize(linearOpMode.hardwareMap, DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Drive.setPositionTolerance(positionTolerence);
        LiftMotors.setPositionTolerance(positionTolerence);
        ExtMotor.setPositionTolerance(positionTolerence);
    }

    static void DriveStrafeSetup(int targetTicks) {
        Drive.setTargetPosition(-targetTicks, targetTicks, targetTicks, -targetTicks);
        Drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    static void DrivePosSetup(int position) {
        Drive.setTargetPosition(position);
        Drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    static void ExtPosSetup(int position) {
        ExtMotor.setTargetPosition(position);
        ExtMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ExtMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    static void LiftPosSetup(int position) {
        LiftMotors.setTargetPosition(position);
        LiftMotors.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LiftMotors.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public static void DriveToPosition(double drivePower, int drivePosition) {
        DrivePosSetup(drivePosition);
        Drive.setPower(drivePower);
        while (Drive.isBusy() && !_linearOpMode.isStopRequested()) {
            if (!Drive.isBusy()) Drive.stop();
        }
        Drive.stop();
    }

    public static void DriveAndLiftToPosition(double drivePower, int drivePosition, double liftPower, int liftPosition) {
        DrivePosSetup(drivePosition);
        LiftPosSetup(liftPosition);

        Drive.setPower(drivePower);
        LiftMotors.setPower(liftPower);
        while ((Drive.isBusy() || LiftMotors.isBusy())
                && !_linearOpMode.isStopRequested()) {
            if (!Drive.isBusy()) Drive.stop();
            if (!LiftMotors.isBusy()) LiftMotors.stop();
        }

        Drive.stop();
        LiftMotors.stop();
    }

    public static void DriveAndExtToPosition(double drivePower, int drivePosition, double extPower, int extPosition) {
        DrivePosSetup(drivePosition);
        ExtPosSetup(extPosition);

        Drive.setPower(drivePower);
        ExtMotor.setPower(extPower);
        while ((Drive.isBusy() || ExtMotor.isBusy())
                && !_linearOpMode.isStopRequested()) {
            if (!Drive.isBusy()) Drive.stop();
            if (!ExtMotor.isBusy()) LiftMotors.stop();
        }

        Drive.stop();
        ExtMotor.stop();
    }

    public static void DriveAndRunAllToPos(double drivePower, int drivePosition, double liftPower, int liftPosition,
                                           double extPower, int extPosition) {
        DrivePosSetup(drivePosition);
        ExtPosSetup(extPosition);
        LiftPosSetup(liftPosition);

        Drive.setPower(drivePower);
        ExtMotor.setPower(extPower);
        LiftMotors.setPower(liftPower);
        while ((Drive.isBusy() || ExtMotor.isBusy() || LiftMotors.isBusy())
                && !_linearOpMode.isStopRequested()) {
            if (!Drive.isBusy()) Drive.stop();
            if (!ExtMotor.isBusy()) ExtMotor.stop();
            if (!LiftMotors.isBusy()) LiftMotors.stop();
        }

        Drive.stop();
        ExtMotor.stop();
        LiftMotors.stop();
    }

    public static void StrafeToEncoder(double drivePower, int targetTicks) {
        DriveStrafeSetup(targetTicks);
        Drive.setPower(
                targetTicks > 0 ? -drivePower : drivePower,
                targetTicks > 0 ? drivePower : -drivePower,
                targetTicks > 0 ? drivePower : -drivePower,
                targetTicks > 0 ? -drivePower : drivePower
        );
        while (!Drive.isAtEncoder() && !_linearOpMode.isStopRequested()) { }
        Drive.stop();
    }
}
