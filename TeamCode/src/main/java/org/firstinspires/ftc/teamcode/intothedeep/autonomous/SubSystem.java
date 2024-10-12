package org.firstinspires.ftc.teamcode.intothedeep.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.Drive;
import org.firstinspires.ftc.teamcode.core.autonomous.Gyro;
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
        Gyro.initialization(linearOpMode.hardwareMap);

        Drive.setPositionTolerance(positionTolerence);
        LiftMotors.setPositionTolerance(positionTolerence);
        ExtMotor.setPositionTolerance(positionTolerence);
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

    static void DrivePosSetup(int drivePosition, int liftPosition, int extPosition) {
        Drive.setTargetPosition(drivePosition);
        Drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        LiftPosSetup(liftPosition);
        ExtPosSetup(extPosition);
    }

    static void DriveStrafeSetup(int targetTicks) {
        Drive.setTargetPosition(-targetTicks, targetTicks, targetTicks, -targetTicks);
        Drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    static void DriveTurnSetup() {
        Drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    static void StopAll() {
        Drive.stop();
        ExtMotor.stop();
        LiftMotors.stop();
    }

    public static void DriveAll(double drivePower, int drivePosition,
                                           double liftPower, int liftPosition,
                                           double extPower, int extPosition) {
        DrivePosSetup(drivePosition, liftPosition, extPosition);
        if (drivePower != 0) Drive.setPower(drivePower);
        if (extPower != 0) ExtMotor.setPower(extPower);
        if (liftPower != 0) LiftMotors.setPower(liftPower);

        while ((Drive.isBusy() || ExtMotor.isBusy() || LiftMotors.isBusy())
                && !_linearOpMode.isStopRequested()) {
            if (drivePower != 0 && !Drive.isBusy()) Drive.stop();
            if (extPower != 0 && !ExtMotor.isBusy()) ExtMotor.stop();
            if (liftPower != 0 && !LiftMotors.isBusy()) LiftMotors.stop();
        }

        StopAll();
    }

    public static void StrafeAll(double drivePower, int targetTicks,
                                       double liftPower, int liftPosition,
                                       double extPower, int extPosition) {
        StrafeAll(drivePower, drivePower, targetTicks, liftPower, liftPosition, extPower, extPosition);
    }

    /* frrlPower Front Right & Rear Left
     * flrrPower Front Left & Rear Right */
    public static void StrafeAll(double frrlPower, double flrrPower, int targetTicks,
                                 double liftPower, int liftPosition,
                                 double extPower, int extPosition) {
        if (frrlPower != 0 || flrrPower != 0) {
            DriveStrafeSetup(targetTicks);
            Drive.setPower(
                    targetTicks > 0 ? -flrrPower : flrrPower,
                    targetTicks > 0 ? frrlPower : -frrlPower,
                    targetTicks > 0 ? frrlPower : -frrlPower,
                    targetTicks > 0 ? -flrrPower : flrrPower
            );
        }
        if (extPower != 0) {
            ExtPosSetup(extPosition);
            ExtMotor.setPower(extPower);
        }
        if (liftPower != 0) {
            LiftPosSetup(liftPosition);
            LiftMotors.setPower(liftPower);
        }

        while (!Drive.isAtEncoder() || ExtMotor.isBusy() || LiftMotors.isBusy()
                && !_linearOpMode.isStopRequested()) {
            if ((frrlPower != 0 || flrrPower != 0) && Drive.isAtEncoder()) Drive.stop();
            if (extPower != 0 && !ExtMotor.isBusy()) ExtMotor.stop();
            if (liftPower != 0 && !LiftMotors.isBusy()) LiftMotors.stop();
        }

        StopAll();
    }

    public static void TurnAll(double drivePower, double targetDegrees,
                               double liftPower, int liftPosition,
                               double extPower, int extPosition) {
        double currentDegrees = Gyro.getCurrentDegrees();

        if (targetDegrees < currentDegrees) {
            TurnLeft(drivePower, targetDegrees, currentDegrees, liftPower, liftPosition, extPower, extPosition);
        } else {
            TurnRight(drivePower, targetDegrees, currentDegrees, liftPower, liftPosition, extPower, extPosition);
        }

        StopAll();
    }

    static void TurnLeft(double drivePower, double targetDegrees, double currentDegrees,
                         double liftPower, int liftPosition,
                         double extPower, int extPosition) {
        if (drivePower != 0) {
            DriveTurnSetup();
            Drive.setPower(-drivePower, drivePower, -drivePower, drivePower);
        }
        if (extPower != 0) {
            ExtPosSetup(extPosition);
            ExtMotor.setPower(extPower);
        }
        if (liftPower != 0) {
            LiftPosSetup(liftPosition);
            LiftMotors.setPower(liftPower);
        }

        while ((targetDegrees < currentDegrees || ExtMotor.isBusy() || LiftMotors.isBusy())
                && !_linearOpMode.isStopRequested()) {
            currentDegrees = Gyro.getCurrentDegrees();

            if (drivePower != 0 && targetDegrees >= currentDegrees) Drive.stop();
            if (extPower != 0 && !ExtMotor.isBusy()) ExtMotor.stop();
            if (liftPower != 0 && !LiftMotors.isBusy()) LiftMotors.stop();
        }
    }

    static void TurnRight(double drivePower, double targetDegrees, double currentDegrees,
                         double liftPower, int liftPosition,
                         double extPower, int extPosition) {
        if (drivePower != 0) {
            DriveTurnSetup();
            Drive.setPower(drivePower, -drivePower, drivePower, -drivePower);
        }
        if (extPower != 0) {
            ExtPosSetup(extPosition);
            ExtMotor.setPower(extPower);
        }
        if (liftPower != 0) {
            LiftPosSetup(liftPosition);
            LiftMotors.setPower(liftPower);
        }

        while ((targetDegrees > currentDegrees || ExtMotor.isBusy() || LiftMotors.isBusy())
                && !_linearOpMode.isStopRequested()) {
            currentDegrees = Gyro.getCurrentDegrees();

            if (drivePower != 0 && targetDegrees <= currentDegrees) Drive.stop();
            if (extPower != 0 && !ExtMotor.isBusy()) ExtMotor.stop();
            if (liftPower != 0 && !LiftMotors.isBusy()) LiftMotors.stop();
        }
    }
}
