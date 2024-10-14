package org.firstinspires.ftc.teamcode.intothedeep.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.Drive;
import org.firstinspires.ftc.teamcode.core.autonomous.Gyro;
import org.firstinspires.ftc.teamcode.intothedeep.core.ExtMotor;
import org.firstinspires.ftc.teamcode.intothedeep.core.LiftMotors;

import java.util.Map;

/*
 * Drive encoders are negative moving forward and positive moving in reverse.
 * strafe encoders are negative moving left and positive moving right.
 * Lift encoders are negative moving up and positive moving down.
 * Extender encoder is negative out and positive in.
 *   To prevent the extender from running into the ground are saying it can't run until the lift's
 *      encoder is < the safetyExtenderPos variable value
 *
 * We need a counter on the lift to prevent fall?
 */
public class SubSystem {
    static LinearOpMode _linearOpMode;
    static final int safetyExtenderPos = -500;
    static final int positionTolerence = 15;

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

    static boolean canRunExtender(int targetPosition) {
        int currentPosition = LiftMotors.getCurrentPosition();
        return targetPosition > currentPosition || currentPosition < safetyExtenderPos;
    }

    public static void DriveAll(double drivePower, int drivePosition,
                                           double liftPower, int liftPosition,
                                           double extPower, int extPosition) {

        boolean isMoving;
        isMoving = true;

        DrivePosSetup(drivePosition, liftPosition, extPosition);
        if (drivePower != 0) Drive.setPower(drivePower);
        if (extPower != 0 && canRunExtender(extPosition)) ExtMotor.setPower(extPower);
        if (liftPower != 0) LiftMotors.setPower(liftPower);

        while ((Drive.isBusy() || ExtMotor.isBusy() || LiftMotors.isBusy())
                && !_linearOpMode.isStopRequested()) {
            if (drivePower != 0 && !Drive.isBusy()) Drive.stop();

            if (liftPower != 0 && !LiftMotors.isBusy()) LiftMotors.stop();

            if (canRunExtender(extPosition) && isMoving && ExtMotor.getPower() != extPower) ExtMotor.setPower(extPower);
            else if (extPower != 0 && !ExtMotor.isBusy()) {
                ExtMotor.stop();
                isMoving = false;
            }

            driveTelemetry();
            liftTelemetry();
            extTelemery(extPosition);
            _linearOpMode.telemetry.update();
        }

        driveTelemetry();
        liftTelemetry();
        extTelemery(extPosition);
        _linearOpMode.telemetry.update();
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

        boolean isMoving = true;

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
            if (canRunExtender(extPosition)) ExtMotor.setPower(extPower);
        }
        if (liftPower != 0) {
            LiftPosSetup(liftPosition);
            LiftMotors.setPower(liftPower);
        }

        while (!Drive.isAtEncoder() || ExtMotor.isBusy() || LiftMotors.isBusy()
                && !_linearOpMode.isStopRequested()) {

            if ((frrlPower != 0 || flrrPower != 0) && Drive.isAtEncoder()) Drive.stop();

            if (liftPower != 0 && !LiftMotors.isBusy()) LiftMotors.stop();

            if (isMoving && canRunExtender(extPosition) && ExtMotor.getPower() != extPower) ExtMotor.setPower(extPower);
            else if (extPower != 0 && !ExtMotor.isBusy()) {
                ExtMotor.stop();
                isMoving = false;
            }

            driveTelemetry();
            liftTelemetry();
            extTelemery(extPosition);
            _linearOpMode.telemetry.update();
        }

        driveTelemetry();
        liftTelemetry();
        extTelemery(extPosition);
        _linearOpMode.telemetry.update();
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

        driveTelemetry();
        liftTelemetry();
        extTelemery(extPosition);
        _linearOpMode.telemetry.update();
        StopAll();
    }

    static void TurnLeft(double drivePower, double targetDegrees, double currentDegrees,
                         double liftPower, int liftPosition,
                         double extPower, int extPosition) {

        boolean isMoving;

        isMoving = true;

        if (drivePower != 0) {
            DriveTurnSetup();
            Drive.setPower(-drivePower, drivePower, -drivePower, drivePower);
        }
        if (extPower != 0) {
            ExtPosSetup(extPosition);
            if (canRunExtender(extPosition)) ExtMotor.setPower(extPower);
        }
        if (liftPower != 0) {
            LiftPosSetup(liftPosition);
            LiftMotors.setPower(liftPower);
        }

        while ((targetDegrees < currentDegrees || ExtMotor.isBusy() || LiftMotors.isBusy())
                && !_linearOpMode.isStopRequested()) {
            currentDegrees = Gyro.getCurrentDegrees();

            if (drivePower != 0 && targetDegrees >= currentDegrees) Drive.stop();

            if (liftPower != 0 && !LiftMotors.isBusy()) LiftMotors.stop();

            if (isMoving && canRunExtender(extPosition) && ExtMotor.getPower() != extPower) ExtMotor.setPower(extPower);
            else if (extPower != 0 && !ExtMotor.isBusy()) {

                ExtMotor.stop();
                isMoving = false;
            }

            driveTelemetry();
            liftTelemetry();
            extTelemery(extPosition);
            _linearOpMode.telemetry.update();
        }
    }

    static void TurnRight(double drivePower, double targetDegrees, double currentDegrees,
                         double liftPower, int liftPosition,
                         double extPower, int extPosition) {

        boolean isMoving;
        isMoving = true;
        if (drivePower != 0) {
            DriveTurnSetup();
            Drive.setPower(drivePower, -drivePower, drivePower, -drivePower);
        }
        if (extPower != 0) {
            ExtPosSetup(extPosition);
            if (canRunExtender(extPosition)) ExtMotor.setPower(extPower);
        }
        if (liftPower != 0) {
            LiftPosSetup(liftPosition);
            LiftMotors.setPower(liftPower);
        }

        while ((targetDegrees > currentDegrees || ExtMotor.isBusy() || LiftMotors.isBusy())
                && !_linearOpMode.isStopRequested()) {
            currentDegrees = Gyro.getCurrentDegrees();

            if (drivePower != 0 && targetDegrees <= currentDegrees) Drive.stop();

            if (liftPower != 0 && !LiftMotors.isBusy()) LiftMotors.stop();

            if (isMoving && canRunExtender(extPosition) && ExtMotor.getPower() != extPower) ExtMotor.setPower(extPower);
            else if (extPower != 0 && !ExtMotor.isBusy())
            {

                ExtMotor.stop();
                isMoving = false;
            }
            driveTelemetry();
            liftTelemetry();
            extTelemery(extPosition);
            _linearOpMode.telemetry.update();
        }
    }

    static void driveTelemetry() {
        Map<String, Integer> positions = Drive.getCurrentPositions();
        Map<String, Double> powers = Drive.getPowers();

        _linearOpMode.telemetry.addLine("Drive");
        for (Map.Entry<String, Integer> entry : positions.entrySet()) {
            _linearOpMode.telemetry.addData(entry.getKey(), entry.getValue());
        }
        _linearOpMode.telemetry.addLine("");
        for (Map.Entry<String, Double> entry : powers.entrySet()) {
            _linearOpMode.telemetry.addData(entry.getKey(), entry.getValue());
        }

        _linearOpMode.telemetry.addLine("");
        _linearOpMode.telemetry.addData("deg", Gyro.getCurrentDegrees());
    }

    static void liftTelemetry() {
        Map<String, Integer> positions = LiftMotors.getCurrentPositions();

        _linearOpMode.telemetry.addLine("Lift");
        for (Map.Entry<String, Integer> entry : positions.entrySet()) {
            _linearOpMode.telemetry.addData(entry.getKey(), entry.getValue());
        }
    }

    static void extTelemery(int target) {
        int position = ExtMotor.getCurrentPosition();

        _linearOpMode.telemetry.addLine("Extension");
        _linearOpMode.telemetry.addData("enabled", canRunExtender(target));
        _linearOpMode.telemetry.addData("pos", position);
    }
}
