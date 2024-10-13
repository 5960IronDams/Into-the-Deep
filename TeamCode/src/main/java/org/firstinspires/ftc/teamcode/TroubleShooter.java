package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.core.autonomous.Mecanum;

@TeleOp(name="TroubleShooter")
public class TroubleShooter extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        Mecanum.initialize(this);

        waitForStart();

        while (opModeIsActive()) {
            Mecanum.loggingAngles(90);
            sleep(750);
            telemetry.update();
        }
    }
}
