package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.player.Mecanum;
import org.firstinspires.ftc.teamcode.intothedeep.player.Extender;
import org.firstinspires.ftc.teamcode.intothedeep.player.Intake;
import org.firstinspires.ftc.teamcode.intothedeep.player.Lift;

@TeleOp(name="Driver OP")
public class Player extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        Mecanum.initialize(this);
        Intake.initialize(this);
        Extender.initialize(this, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Lift.initialize(this, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        waitForStart();

        while (opModeIsActive()) {
            /* gamepad1.left_trigger - Govenor
             * gamepad1.right_stick_y,
             * gamepad1.right_stick_x,
             * gamepad1.left_stick_x - Turn */
            Mecanum.drive();

            /* gamepad2.left_bumper - Out
             * gamepad2.right_bumber - In */
            Intake.run();

            /* gamepad2.right_stick_y */
            Extender.run();

            /* gamepad2.left_stick_y */
            Lift.run();

            telemetry.update();
        }
    }
}
