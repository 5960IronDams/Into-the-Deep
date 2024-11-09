package org.firstinspires.ftc.teamcode.intothedeep.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.core.Drive;
import org.firstinspires.ftc.teamcode.core.autonomous.ExpansionHubGyro;
import org.firstinspires.ftc.teamcode.intothedeep.core.ExtMotor;
import org.firstinspires.ftc.teamcode.intothedeep.core.GnashMoter;
import org.firstinspires.ftc.teamcode.intothedeep.core.LiftMotors;

import java.util.Map;

/*
 * Drive encoders are negative moving forward and positive moving in reverse.
 * strafe encoders are negative moving left and positive moving right.
 * Lift encoders are negative moving up and positive moving down.
 * Extender encoder is negative out and positive in.
 *   To prevent the extender from running into the ground are saying it can't run until the lift's
 *      Positive for rotation is left (:
 *      encoder is < the safetyExtenderPos variable value
 *  positive position (Gnasher) is up
 * We need a counter on the lift to prevent fall?
 */
public class SubSystem {
    static LinearOpMode _linearOpMode;
    static final int safetyExtenderPos = -500;
    static final int positionTolerence = 100;

    public static void initialize(LinearOpMode linearOpMode)
    {
        _linearOpMode = linearOpMode;
        Drive.initialize(linearOpMode.hardwareMap, DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LiftMotors.initialize(linearOpMode.hardwareMap, DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ExtMotor.initialize(linearOpMode.hardwareMap, DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        GnashMoter.initialize(linearOpMode.hardwareMap, DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ExpansionHubGyro.initialization(linearOpMode.hardwareMap);

        Drive.setPositionTolerance(positionTolerence);
        LiftMotors.setPositionTolerance(positionTolerence);
        ExtMotor.setPositionTolerance(positionTolerence);
        GnashMoter.setPositionTolerance(positionTolerence);
    }

    static void ExtPosSetup(int position) {
        ExtMotor.setTargetPosition(position);
       // ExtMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ExtMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    static void LiftPosSetup(int position) {
        LiftMotors.setTargetPosition(position);
       // LiftMotors.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LiftMotors.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    static void GnasherPosSetup(int position) {
        GnashMoter.setTargetPosition(position);
        GnashMoter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    static void DrivePosSetup(int drivePosition, int liftPosition, int extPosition, int gnasherPosition) {
        Drive.setTargetPosition(drivePosition);
        Drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        LiftPosSetup(liftPosition);
        ExtPosSetup(extPosition);
        GnasherPosSetup(gnasherPosition);
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
        GnashMoter.stop();
    }

    static boolean canRunExtender(int targetPosition) {
        int currentPosition = LiftMotors.getCurrentPosition();
        return targetPosition > currentPosition || currentPosition < safetyExtenderPos;
    }

    public static void DriveAll(double drivePower, int drivePosition,
                                           double liftPower, int liftPosition,
                                           double extPower, int extPosition,
                                           double gnasherPower, int gnasherPosition) {

        boolean isMoving;
        isMoving = true;

        DrivePosSetup(drivePosition, liftPosition, extPosition, gnasherPosition);
        if (drivePower != 0) Drive.setPower(drivePower);
        if (extPower != 0 && canRunExtender(extPosition)) ExtMotor.setPower(extPower);
        if (liftPower != 0) LiftMotors.setPower(liftPower);
        if (gnasherPower != 0) GnashMoter.setPower(gnasherPower);

        while ((Drive.isBusy() || ExtMotor.isBusy() || LiftMotors.isBusy() || GnashMoter.isBusy())
                && !_linearOpMode.isStopRequested()) {
            if (drivePower != 0 && !Drive.isBusy()) Drive.stop();

            if (liftPower != 0 && !LiftMotors.isBusy()) {
                LiftMotors.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                LiftMotors.stop();
            }

            if (gnasherPower != 0 && !GnashMoter.isBusy()) {
                GnashMoter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                GnashMoter.stop();
            }

            if (canRunExtender(extPosition) && isMoving && ExtMotor.getPower() != extPower) ExtMotor.setPower(extPower);
            else if (extPower != 0 && !ExtMotor.isBusy()) {
                ExtMotor.stop();
                isMoving = false;
            }

            driveTelemetry();
            liftTelemetry();
            extTelemery(extPosition);
            gnasherTelemery();
            _linearOpMode.telemetry.update();
        }

        driveTelemetry();
        liftTelemetry();
        extTelemery(extPosition);
        gnasherTelemery();
        _linearOpMode.telemetry.update();
        StopAll();
    }

    public static void StrafeAll(double drivePower, int targetTicks,
                                       double liftPower, int liftPosition,
                                       double extPower, int extPosition,
                                       double gnasherPower, int gnasherPosition) {
        StrafeAll(drivePower, drivePower, targetTicks, liftPower, liftPosition, extPower, extPosition, gnasherPower, gnasherPosition);
    }

    /* frrlPower Front Right & Rear Left
     * flrrPower Front Left & Rear Right */
    public static void StrafeAll(double frrlPower, double flrrPower, int targetTicks,
                                 double liftPower, int liftPosition,
                                 double extPower, int extPosition,
                                 double gnasherPower, int gnasherPosition) {

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

        if (gnasherPower != 0) {
            GnasherPosSetup(gnasherPosition);
            GnashMoter.setPower(gnasherPower);
        }

        while ((!Drive.isAtEncoder() || ExtMotor.isBusy() || LiftMotors.isBusy() || GnashMoter.isBusy())
                && !_linearOpMode.isStopRequested()) {

            if ((frrlPower != 0 || flrrPower != 0) && Drive.isAtEncoder()) Drive.stop();

            if (liftPower != 0 && !LiftMotors.isBusy()) {
                LiftMotors.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                LiftMotors.stop();
            }

            if (gnasherPower != 0 && !GnashMoter.isBusy()) {
                GnashMoter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                GnashMoter.stop();
            }

            if (isMoving && canRunExtender(extPosition) && ExtMotor.getPower() != extPower) ExtMotor.setPower(extPower);
            else if (extPower != 0 && !ExtMotor.isBusy()) {
                ExtMotor.stop();
                isMoving = false;
            }

            driveTelemetry();
            liftTelemetry();
            extTelemery(extPosition);
            gnasherTelemery();
            _linearOpMode.telemetry.update();
        }

        driveTelemetry();
        liftTelemetry();
        extTelemery(extPosition);
        gnasherTelemery();
        _linearOpMode.telemetry.update();
        StopAll();
    }

    public static void TurnAll(double drivePower, double targetDegrees,
                               double liftPower, int liftPosition,
                               double extPower, int extPosition,
                               double gnasherPower, int gnasherPosition) {

        ExpansionHubGyro.resetOffsetAngle();
        double currentDegrees = ExpansionHubGyro.getCurrentDegrees();

        if (targetDegrees < 0) {
            TurnLeft(drivePower, Math.abs(targetDegrees), currentDegrees, liftPower, liftPosition, extPower, extPosition, gnasherPower, gnasherPosition);
        } else {
            TurnRight(drivePower, targetDegrees, currentDegrees, liftPower, liftPosition, extPower, extPosition, gnasherPower, gnasherPosition);
        }

        _linearOpMode.telemetry.addLine("Gyro");
        _linearOpMode.telemetry.addData("deg", currentDegrees);
        _linearOpMode.telemetry.addData("Target", targetDegrees);
        driveTelemetry();
        liftTelemetry();
        extTelemery(extPosition);
        gnasherTelemery();
        _linearOpMode.telemetry.update();
        StopAll();
    }

    static void TurnLeft(double drivePower, double targetDegrees, double currentDegrees,
                         double liftPower, int liftPosition,
                         double extPower, int extPosition,
                         double gnasherPower, int gnasherPosition) {

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
        if (gnasherPower != 0) {
            GnasherPosSetup(gnasherPosition);
            GnashMoter.setPower(gnasherPower);
        }

        while ((targetDegrees > currentDegrees || ExtMotor.isBusy() || LiftMotors.isBusy())
                && !_linearOpMode.isStopRequested()) {
            currentDegrees = ExpansionHubGyro.getCurrentDegrees();

            if (drivePower != 0 && targetDegrees <= currentDegrees) Drive.stop();

            if (liftPower != 0 && !LiftMotors.isBusy()) {
                LiftMotors.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                LiftMotors.stop();
            }

            if (gnasherPower != 0 && !GnashMoter.isBusy()) {
                GnashMoter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                GnashMoter.stop();
            }

            if (isMoving && canRunExtender(extPosition) && ExtMotor.getPower() != extPower) ExtMotor.setPower(extPower);
            else if (extPower != 0 && !ExtMotor.isBusy()) {
                ExtMotor.stop();
                isMoving = false;
            }

            _linearOpMode.telemetry.addLine("Gyro");
            _linearOpMode.telemetry.addData("deg", currentDegrees);
            _linearOpMode.telemetry.addData("Left Target", targetDegrees);
            driveTelemetry();
            liftTelemetry();
            extTelemery(extPosition);
            gnasherTelemery();
            _linearOpMode.telemetry.update();
        }
    }

    static void TurnRight(double drivePower, double targetDegrees, double currentDegrees,
                         double liftPower, int liftPosition,
                         double extPower, int extPosition,
                          double gnasherPower, int gnasherPosition) {

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
        if (gnasherPower != 0) {
            GnasherPosSetup(gnasherPosition);
            GnashMoter.setPower(gnasherPower);
        }

        while ((targetDegrees > currentDegrees || ExtMotor.isBusy() || LiftMotors.isBusy())
                && !_linearOpMode.isStopRequested()) {
            currentDegrees = ExpansionHubGyro.getCurrentDegrees();

            if (drivePower != 0 && targetDegrees <= currentDegrees) Drive.stop();

            if (liftPower != 0 && !LiftMotors.isBusy()) {
                LiftMotors.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                LiftMotors.stop();
            }

            if (gnasherPower != 0 && !GnashMoter.isBusy()) {
                GnashMoter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                GnashMoter.stop();
            }

            if (isMoving && canRunExtender(extPosition) && ExtMotor.getPower() != extPower) ExtMotor.setPower(extPower);
            else if (extPower != 0 && !ExtMotor.isBusy())
            {
                ExtMotor.stop();
                isMoving = false;
            }


            _linearOpMode.telemetry.addLine("Gyro");
            _linearOpMode.telemetry.addData("deg", currentDegrees);
            _linearOpMode.telemetry.addData("Right Target", targetDegrees);
            driveTelemetry();
            liftTelemetry();
            extTelemery(extPosition);
            gnasherTelemery();
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
        _linearOpMode.telemetry.addData("is busy", Drive.isBusy());
        _linearOpMode.telemetry.addLine("");
        for (Map.Entry<String, Double> entry : powers.entrySet()) {
            _linearOpMode.telemetry.addData(entry.getKey(), entry.getValue());
        }
    }

    static void liftTelemetry() {
        Map<String, Integer> positions = LiftMotors.getCurrentPositions();

        _linearOpMode.telemetry.addLine("Lift");
        for (Map.Entry<String, Integer> entry : positions.entrySet()) {
            _linearOpMode.telemetry.addData(entry.getKey(), entry.getValue());
        }
        _linearOpMode.telemetry.addData("is busy", LiftMotors.isBusy());
    }

    static void extTelemery(int target) {
        int position = ExtMotor.getCurrentPosition();

        _linearOpMode.telemetry.addLine("Extension");
        _linearOpMode.telemetry.addData("enabled", canRunExtender(target));
        _linearOpMode.telemetry.addData("pos", position);
        _linearOpMode.telemetry.addData("is busy", ExtMotor.isBusy());
    }

    static void gnasherTelemery() {
        _linearOpMode.telemetry.addLine("Gnasher");
        _linearOpMode.telemetry.addData("pos", GnashMoter.getCurrentPosition());
        _linearOpMode.telemetry.addData("is busy", GnashMoter.isBusy());
    }
}
