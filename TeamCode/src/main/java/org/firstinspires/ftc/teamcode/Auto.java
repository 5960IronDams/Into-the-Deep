package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.intothedeep.autonomous.SubSystem;

@Autonomous(name = "Basic")
public class Auto extends LinearOpMode {
    @Override
    public void runOpMode() {
        SubSystem.initialize(this);

        waitForStart();

        SubSystem.DriveAll(0.4, 1000, 0.6, -500, 0, 0);
//        SubSystem.TurnAll(0.4, 90, 0, 1, 0, 1);
//        SubSystem.StrafeAll(0.4, 250, 0, 1, 0, 1);
//        SubSystem.StrafeAll(0.4, 0.4, 0, 0, 1,0 ,1);

        sleep(30000);
    }
}
