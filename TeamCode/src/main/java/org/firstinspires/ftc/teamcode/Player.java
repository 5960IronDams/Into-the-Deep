package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.Drive;
import org.firstinspires.ftc.teamcode.core.player.Mecanum;
import org.firstinspires.ftc.teamcode.intothedeep.core.ExtMotor;
import org.firstinspires.ftc.teamcode.intothedeep.core.GnashMoter;
import org.firstinspires.ftc.teamcode.intothedeep.core.LiftMotors;
import org.firstinspires.ftc.teamcode.intothedeep.player.Extender;
import org.firstinspires.ftc.teamcode.intothedeep.player.Gnasher;
import org.firstinspires.ftc.teamcode.intothedeep.player.Intake;
import org.firstinspires.ftc.teamcode.intothedeep.player.Lift;

import java.util.Map;

@TeleOp(name="Driver OP")
public class Player extends LinearOpMode {

    @Override
    public void runOpMode() {

        Mecanum.initialize(this);
        Intake.initialize(this);
        Extender.initialize(this, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Lift.initialize(this, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Gnasher.initialize(this, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        waitForStart();

        while (opModeIsActive()) {
            /* gamepad1.left_trigger - Govenor
             * gamepad1.right_stick_y,
             * gamepad1.right_stick_x,
             * gamepad1.left_stick_x - Turn */
            Mecanum.drive();

            telemetry.addLine("Drive");
            Map<String, Double> motorPowers = Drive.getPowers();
            telemetry.addData("gov", Mecanum.getActiveGovenor());
            telemetry.addData("flp", motorPowers.get("flp"));
            telemetry.addData("frp", motorPowers.get("frp"));
            telemetry.addData("rlp", motorPowers.get("rlp"));
            telemetry.addData("rrp", motorPowers.get("rrp"));

            /* gamepad2.left_bumper - Out
             * gamepad2.right_bumber - In */
            Intake.run();

            telemetry.addLine("Intake");
            telemetry.addData("pos", Intake.getPosition());

            /* gamepad2.right_stick_y
             * gamepad2.right_trigger - Governor  */
            Extender.run();
//HI
            telemetry.addLine("Extender");
            telemetry.addData("gov", Extender.getActiveGovenor());
            telemetry.addData("pow", ExtMotor.getPower());

            Gnasher.run();

            telemetry.addLine("The Ghastly Gnasher");
//            telemetry.addData("gov", Extender.getActiveGovenor());
            telemetry.addData("pow", GnashMoter.getPower());

            /* gamepad2.left_stick_y
             * gamepad2.left_trigger - Govenor */
            Lift.run();

            telemetry.addLine("Lift");
            telemetry.addData("gov", Lift.getActiveGovenor());
            Map<String, Double> powers = LiftMotors.getPowers();
//            telemetry.addData("right", powers.get("right"));
            telemetry.addData("left", powers.get("left"));

            telemetry.update();
        }
    }
}
