package org.firstinspires.ftc.teamcode.intothedeep;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.player.Mecanum;
import org.firstinspires.ftc.teamcode.intothedeep.core.GnashMoter;
import org.firstinspires.ftc.teamcode.intothedeep.core.LiftMotors;
import org.firstinspires.ftc.teamcode.intothedeep.player.Acorn;
import org.firstinspires.ftc.teamcode.intothedeep.player.ClipperThingamabobber;
import org.firstinspires.ftc.teamcode.intothedeep.player.Extender;
import org.firstinspires.ftc.teamcode.intothedeep.player.Gnasher;
import org.firstinspires.ftc.teamcode.intothedeep.player.Intake;
import org.firstinspires.ftc.teamcode.intothedeep.player.Latcher;
import org.firstinspires.ftc.teamcode.intothedeep.player.Lift;

@TeleOp(name="Driver OP")
public class Player extends LinearOpMode {

    @Override
    public void runOpMode() {

        boolean useAcorn = true;

        Mecanum.initialize(this);
        Acorn.initialize(this);
        Intake.initialize(this);
        Extender.initialize(this, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Lift.initialize(this, DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LiftMotors.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Latcher.initialize(this);
        Gnasher.initialize(this, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ClipperThingamabobber.initialize(this, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Latcher.close();
        Intake.open();
        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Intake", Intake.isClosed());

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
                /* gamepad2.left_bumper - Open
                 * gamepad2.right_bumber - Closed */
                Intake.run();

                /* gamepad2.right_stick_y
                 * gamepad2.x - Governor  */
                Extender.run();

                /* gamepad2.left_stick_y  */
                Lift.run();
            }

            /* gamepad1.left_trigger - Govenor
             * gamepad1.right_stick_y,
             * gamepad1.right_stick_x,
             * gamepad1.left_stick_x - Turn */
            Mecanum.drive();

            Latcher.run();

            /* gamepad2.left_trigger Up
             * gamepad2.right_trigger Down */
            Gnasher.run();
            telemetry.addLine("Gnasher");
            telemetry.addData("pos", GnashMoter.getCurrentPosition());

            /* gamepad2.dpadup - up to hook
             * gamepad2.dpaddown - down to pull up */
            ClipperThingamabobber.run();

            telemetry.update();
        }
    }
}
