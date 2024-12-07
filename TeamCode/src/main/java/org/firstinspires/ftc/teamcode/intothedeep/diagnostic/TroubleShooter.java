package org.firstinspires.ftc.teamcode.intothedeep.diagnostic;

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
import org.firstinspires.ftc.teamcode.intothedeep.player.Acorn;
import org.firstinspires.ftc.teamcode.intothedeep.player.ClipperThingamabobber;
import org.firstinspires.ftc.teamcode.intothedeep.player.Extender;
import org.firstinspires.ftc.teamcode.intothedeep.player.Gnasher;
import org.firstinspires.ftc.teamcode.intothedeep.player.Intake;
import org.firstinspires.ftc.teamcode.intothedeep.player.Latcher;
import org.firstinspires.ftc.teamcode.intothedeep.player.Lift;

import java.util.Map;

@TeleOp(name="TroubleShooter")
public class TroubleShooter extends LinearOpMode {

    @Override
    public void runOpMode() {

        Mecanum.initialize(this);
        Drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Extender.initialize(this, DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ExtMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Gnasher.initialize(this, DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        GnashMoter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        ClipperThingamabobber.initialize(this, DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ClipperThingamabobberMoter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Lift.initialize(this, DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LiftMotors.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Intake.initialize(this);
        Acorn.initialize(this);
        ControlHubGyro.initialization(hardwareMap);
        ExpansionHubGyro.initialization(hardwareMap);
        Latcher.initialize(this);

        Latcher.close();
        Intake.open();
        waitForStart();

        boolean useAcorn = true;

        while (opModeIsActive()) {
            Mecanum.drive();
            telemetry.addLine("Drive Current");
            Map<String, Double> driveCurrents = Drive.getCurrents();
            telemetry.addData("flc", driveCurrents.get("flc"));
            telemetry.addData("frc", driveCurrents.get("frc"));
            telemetry.addData("rlc", driveCurrents.get("rlc"));
            telemetry.addData("rrc", driveCurrents.get("rrc"));

            Map<String, Double> drivePowers = Drive.getPowers();
            telemetry.addData("flp", drivePowers.get("flp"));
            telemetry.addData("frp", drivePowers.get("frp"));
            telemetry.addData("rlp", drivePowers.get("rlp"));
            telemetry.addData("rrp", drivePowers.get("rrp"));


            telemetry.addLine("Drive Encoders");
            telemetry.addData("gov", Mecanum.getActiveGovenor());
            Map<String, Integer> drivePositions = Drive.getCurrentPositions();
            telemetry.addData("flp", drivePositions.get("flp"));
            telemetry.addData("frp", drivePositions.get("flp"));
            telemetry.addData("rlp", drivePositions.get("flp"));
            telemetry.addData("rrp", drivePositions.get("flp"));

            if(gamepad2.x){
                useAcorn = !useAcorn;
                sleep(300);
                if (!useAcorn)
                    Acorn.runReset = true;
            }
            if(useAcorn){
                Acorn.run();
                if (gamepad2.b) {
                    Acorn.runReset = true;
                    sleep(300);
                }
                Acorn.reset();
            }
            else {
                Acorn.reset();
                telemetry.addLine("Acorn");
                telemetry.addData("extPow", Acorn.getExtPower());
                telemetry.addData("wristPos", Acorn.getWristPosition());
                telemetry.addData("isReset", Acorn.isReset());

                Lift.run();
                telemetry.addLine("Lift");
                telemetry.addData("IsTouching", LiftMotors.isTouching());
                telemetry.addData("Pos", LiftMotors.getCurrentPosition());

                Extender.run();
                telemetry.addLine("Extender Encoders");
                telemetry.addData("gov", Extender.getActiveGovenor());
                telemetry.addData("pos", ExtMotor.getCurrentPosition());

                Intake.run();
                telemetry.addLine("Claw");
                telemetry.addData("pos", Intake.getPosition());
                telemetry.addData("closed", Intake.isClosed());
            }

            Gnasher.run();
            telemetry.addLine("The Ghastly Gnasher");
            telemetry.addData("pos", GnashMoter.getCurrentPosition());

            ClipperThingamabobber.run();
            telemetry.addLine("Clipper Thingamabobber");
            telemetry.addData("pos", ClipperThingamabobberMoter.getCurrentPosition());

            telemetry.addLine("CH Gyro Angle");
            telemetry.addData("degree", ControlHubGyro.getCurrentDegrees());

            telemetry.addLine("EH Gyro Angle");
            telemetry.addData("degree", ExpansionHubGyro.getCurrentDegrees());

            telemetry.update();
        }
    }
}
