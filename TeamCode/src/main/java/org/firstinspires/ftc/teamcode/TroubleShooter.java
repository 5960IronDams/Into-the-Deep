package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.Drive;
import org.firstinspires.ftc.teamcode.core.autonomous.Gyro;
import org.firstinspires.ftc.teamcode.core.player.Mecanum;
import org.firstinspires.ftc.teamcode.intothedeep.core.ExtMotor;
import org.firstinspires.ftc.teamcode.intothedeep.core.GnashMoter;
import org.firstinspires.ftc.teamcode.intothedeep.core.LiftMotors;
import org.firstinspires.ftc.teamcode.intothedeep.player.Extender;
import org.firstinspires.ftc.teamcode.intothedeep.player.Gnasher;
import org.firstinspires.ftc.teamcode.intothedeep.player.Intake;
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

        Lift.initialize(this, DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LiftMotors.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Intake.initialize(this);

        Gyro.initialization(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {

//            Drive.setRRPower(0.2);
//            Drive.setRLPower(0.2);
//            Drive.setFRPower(0.2);
//            Drive.setFLPower(0.2);

            Mecanum.drive();
            telemetry.addLine("Drive Encoders");
            telemetry.addData("gov", Mecanum.getActiveGovenor());
            Map<String, Integer> drivePositions = Drive.getCurrentPositions();
            telemetry.addData("flp", drivePositions.get("flp"));
            telemetry.addData("frp", drivePositions.get("flp"));
            telemetry.addData("rlp", drivePositions.get("flp"));
            telemetry.addData("rrp", drivePositions.get("flp"));

            Lift.run();
            telemetry.addLine("Lift Encoders");
            telemetry.addData("gov", Lift.getActiveGovenor());
            Map<String, Integer> liftPositions = LiftMotors.getCurrentPositions();
//            telemetry.addData("right", liftPositions.get("right"));
            telemetry.addData("left", liftPositions.get("left"));

            Extender.run();
            telemetry.addLine("Extender Encoders");
            telemetry.addData("gov", Extender.getActiveGovenor());
            telemetry.addData("pos", ExtMotor.getCurrentPosition());

            Gnasher.run();
            telemetry.addLine("The Ghastly Gnasher");
//            telemetry.addData("gov", Extender.getActiveGovenor());
            telemetry.addData("pos", GnashMoter.getCurrentPosition());

            telemetry.addLine("Gyro Angle");
            telemetry.addData("degree", Gyro.getCurrentDegrees());

            Intake.run();
            telemetry.addLine("Intake Position");
            telemetry.addData("pos", Intake.getPosition());

            telemetry.update();
        }
    }
}
