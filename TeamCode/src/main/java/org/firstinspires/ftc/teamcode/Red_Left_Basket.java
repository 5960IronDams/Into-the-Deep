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

        SubSystem.DriveAll(0.4, -400, 0, 0,0 ,0,0,0);
        Intake.close();
        sleep(300);


        Log.i("5960", runtime.milliseconds() / 1000.0 +  ": End");
//        sleep(30000);
    }
}
