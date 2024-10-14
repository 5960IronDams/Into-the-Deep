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

//       SubSystem.DriveAll(0.4, -1000, 0.6, -950, 0.2, -350);
//        SubSystem.TurnAll(0.4, 90, 0, 1, 0, 1);
//        SubSystem.StrafeAll(0.4, -1000, 0.6, -950, 0.2, -350);
      SubSystem.StrafeAll(0.4, 0, -1000, 0, 0,0.2 ,-350);

        sleep(30000);
    }
}
