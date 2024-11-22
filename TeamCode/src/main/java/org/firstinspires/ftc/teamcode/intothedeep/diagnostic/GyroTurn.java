package org.firstinspires.ftc.teamcode.intothedeep.diagnostic;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.intothedeep.autonomous.SubSystem;
import org.firstinspires.ftc.teamcode.intothedeep.player.Intake;
import org.firstinspires.ftc.teamcode.intothedeep.player.Latcher;

@Disabled
@TeleOp(name="GyroTurn")
public class GyroTurn extends LinearOpMode {

    @Override
    public void runOpMode() {
        SubSystem.initialize(this, 100);
        Intake.initialize(this);
        Intake.close();
        Latcher.initialize(this);
        Latcher.close();

        waitForStart();

        SubSystem.TurnAll(0.4, -180, 0,0,0,0,0,0);
        sleep(1000);
        SubSystem.TurnAll(0.4, 180, 0,0,0,0,0,0);
sleep(30000);
//        double drivePower = 0.4;
//        if (drivePower != 0) {
//            Drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//            Drive.setPower(-drivePower, drivePower, -drivePower, drivePower);
//        }
//
//        double currentDegrees = 0;
//
//        while ((170 > currentDegrees)
//                && !isStopRequested()) {
//            currentDegrees = Gyro.getCurrentDegrees();
//
//
//            telemetry.addData("currentDegrees", currentDegrees);
//            telemetry.update();
//        }
//
//        Gyro.resetOffsetAngle();
//        currentDegrees = 0;
//
//        while ((170 > currentDegrees)
//                && !isStopRequested()) {
//            currentDegrees = Gyro.getCurrentDegrees();
//
//
//            telemetry.addData("currentDegrees", currentDegrees);
//            telemetry.update();
//        }
    }
}
