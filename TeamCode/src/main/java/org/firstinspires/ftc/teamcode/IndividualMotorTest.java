package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.Drive;
import org.firstinspires.ftc.teamcode.core.autonomous.ControlHubGyro;
import org.firstinspires.ftc.teamcode.core.autonomous.ExpansionHubGyro;
import org.firstinspires.ftc.teamcode.core.player.Mecanum;
import org.firstinspires.ftc.teamcode.intothedeep.core.ClipperThingamabobberMoter;
import org.firstinspires.ftc.teamcode.intothedeep.core.ExtMotor;
import org.firstinspires.ftc.teamcode.intothedeep.core.GnashMoter;
import org.firstinspires.ftc.teamcode.intothedeep.core.LiftMotors;
import org.firstinspires.ftc.teamcode.intothedeep.player.ClipperThingamabobber;
import org.firstinspires.ftc.teamcode.intothedeep.player.Extender;
import org.firstinspires.ftc.teamcode.intothedeep.player.Gnasher;
import org.firstinspires.ftc.teamcode.intothedeep.player.Intake;
import org.firstinspires.ftc.teamcode.intothedeep.player.Latcher;
import org.firstinspires.ftc.teamcode.intothedeep.player.Lift;

import java.util.Map;

@TeleOp(name="IndividualMotorTest")
public class IndividualMotorTest extends LinearOpMode {

    @Override
    public void runOpMode() {

        Mecanum.initialize(this);
        Drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Intake.initialize(this);


        waitForStart();

        while (opModeIsActive()) {

            Intake.run(gamepad1.right_stick_y);
            telemetry.addData("Claw Pos", Intake.getPosition());

            if (gamepad1.a) Drive.setRearLeftPower(1);
            else if (gamepad2.a) Drive.setRearLeftPower(-1);
            else if (gamepad1.b) Drive.setRearRightPower(1);
            else if (gamepad2.b) Drive.setRearRightPower(-1);
            else if (gamepad1.x) Drive.setFrontLeftPower(1);
            else if (gamepad2.x) Drive.setFrontLeftPower(-1);
            else if (gamepad1.y) Drive.setFrontRightPower(1);
            else if (gamepad2.y) Drive.setFrontRightPower(-1);
            else Drive.stop();

            telemetry.addLine("Drive Current");
            Map<String, Double> driveCurrents = Drive.getCurrents();
            telemetry.addData("flc", driveCurrents.get("flc"));
            telemetry.addData("frc", driveCurrents.get("frc"));
            telemetry.addData("rlc", driveCurrents.get("rlc"));
            telemetry.addData("rrc", driveCurrents.get("rrc"));


            telemetry.addLine("Drive Encoders");
            telemetry.addData("gov", Mecanum.getActiveGovenor());
            Map<String, Integer> drivePositions = Drive.getCurrentPositions();
            telemetry.addData("flp", drivePositions.get("flp"));
            telemetry.addData("frp", drivePositions.get("frp"));
            telemetry.addData("rlp", drivePositions.get("rlp"));
            telemetry.addData("rrp", drivePositions.get("rrp"));

            telemetry.update();
        }
    }
}
