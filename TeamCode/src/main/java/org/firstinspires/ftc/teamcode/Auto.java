package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.intothedeep.autonomous.SubSystem;
import org.firstinspires.ftc.teamcode.intothedeep.player.Intake;

@Autonomous(name = "Basic")
public class Auto extends LinearOpMode {
    @Override
    public void runOpMode() {
        SubSystem.initialize(this);
        Intake.initialize(this);

        Intake.close();
        waitForStart();

        //move to place element on bar
        SubSystem.StrafeAll(0.4, -0.1, -1600, -0.5, -1060,0 ,0);
        Intake.open();
//       SubSystem.DriveAll(0.4, -1000, 0.6, -950, 0.2, -350);
//       SubSystem.TurnAll(0.4, 90, 0, 0, 0, 0);
//        SubSystem.StrafeAll(0.4, -1000, 0.6, -950, 0.2, -350);
       //SubSystem.StrafeAll(0.4, 0, -1000, 0, 0,0.2 ,-350);

        sleep(30000);
    }
}
