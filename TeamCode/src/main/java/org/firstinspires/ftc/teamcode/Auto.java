package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.intothedeep.autonomous.SubSystem;
import org.firstinspires.ftc.teamcode.intothedeep.player.Intake;
import org.firstinspires.ftc.teamcode.intothedeep.player.Latcher;

@Autonomous(name = "Basic")
public class Auto extends LinearOpMode {
    @Override
    public void runOpMode() {
        SubSystem.initialize(this);
        Intake.initialize(this);

        Intake.close();
        Latcher.initialize(this);
        Latcher.close();
        waitForStart();

        SubSystem.DriveAll(0,0,0,0,0,0,0.4,250);

        //move to place element on bar
//        SubSystem.StrafeAll(0.4, -0.22, -1600, 0.5, -1060,0 ,0,0,0);
//        Intake.open();
//        sleep(500);
//        SubSystem.DriveAll(0.4, 250, 0.2, 0,0 ,0,0,0);
//        sleep(200);
//        SubSystem.StrafeAll(0.4, 1800, 0, 0,0 ,0,0,0);
//        sleep(200);
//        SubSystem.StrafeAll(0, 0, 0.5, -50,0.2 ,-25,0,0);
//       // SubSystem.TurnAll(0.4, -35, 0, 0, 0, 0);
//       SubSystem.DriveAll(0.4, -1000, 0.6, -950, 0.2, -350);
//       SubSystem.TurnAll(0.4, 90, 0, 0, 0, 0);
//        SubSystem.StrafeAll(0.4, -1000, 0.6, -950, 0.2, -350);
       //SubSystem.StrafeAll(0.4, 0, -1000, 0, 0,0.2 ,-350);

        sleep(30000);
    }
}
