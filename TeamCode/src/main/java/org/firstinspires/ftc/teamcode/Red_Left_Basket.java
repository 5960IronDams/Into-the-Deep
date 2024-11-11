package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.intothedeep.autonomous.SubSystem;
import org.firstinspires.ftc.teamcode.intothedeep.player.Intake;
import org.firstinspires.ftc.teamcode.intothedeep.player.Latcher;

@Autonomous(name = "red_left_basket")
public class Red_Left_Basket extends LinearOpMode {
    @Override
    public void runOpMode() {
        SubSystem.initialize(this);
        Intake.initialize(this);
        Intake.open();
        Latcher.initialize(this);
        Latcher.close();
        waitForStart();

        ElapsedTime runtime = new ElapsedTime();
        runtime.reset();

        // Move diagonal toward the submersible
        Log.i("5960", runtime.milliseconds() +  ": Move diagonal toward the submersible.");
        SubSystem.StrafeAll(0.1, -0.4, -2200, 0, 0,0 ,0,1,2000);
        Log.i("5960", runtime.milliseconds() +  ": Move diagonal away from the submersible.");
        SubSystem.DriveAll(0, 0, 0, 0,0 ,0,1,1000);
        SubSystem.DriveAll(0.4, 400, 0, 0,0 ,0,1,1000);
        Log.i("5960", runtime.milliseconds() +  ": Strafe towards the elements on the floor.");
        SubSystem.StrafeAll(-0.4, 2550, 0, 0,0 ,0,1,0);

        SubSystem.DriveAll(0.4, -335, 0, 0,0 , 0,0,0);
        Intake.close();
        sleep(300);

        SubSystem.TurnAll(0.4, 143, 1, -3350, 0, 0, 0, 0);
        SubSystem.DriveAll(0.4, -1150, 1, -3350,1 ,-850,0,0);
        Intake.open();
        sleep(300);

        SubSystem.TurnAll(0.4, -143, 0, 0, 1, -10, 0, 0);
        SubSystem.DriveAll(0.4, -1130, 1, 0,0 ,0,0,0);

//        SubSystem.TurnAll(0.4, -141, 0, 0, 0, 0, 0, 0);
//        SubSystem.DriveAll(0.4, -185, 0.7, -819,0 ,0,0,0);

        sleep(1000);
        Intake.close();
        sleep(300);

        SubSystem.TurnAll(0.4, 165, 1, -3350, 1, 0, 0, 0);
//        SubSystem.DriveAll(0, 0, 0, 0,1 ,-850,0,0);
        SubSystem.DriveAll(0.4, -1130, 0, 0,1 ,-850,0,0);
        Intake.open();
        sleep(300);

        SubSystem.TurnAll(0.4, -165, 0, 0, 0, 0, 0, 0);
        SubSystem.DriveAll(0.7, -2000, 1, 0,1 ,1,0,0);
        SubSystem.StrafeAll(-0.7, 250, 0, 0,0 ,0,0,0);
        SubSystem.DriveAll(0.7, 2000, 0, 0,0 ,0,0,0);
//        SubSystem.DriveAll(0.4, 200, 0, 0,0 ,0,0,0);
    }
}
