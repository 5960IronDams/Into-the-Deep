package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.intothedeep.autonomous.SubSystem;
import org.firstinspires.ftc.teamcode.intothedeep.player.Intake;
import org.firstinspires.ftc.teamcode.intothedeep.player.Latcher;

@Autonomous(name = "RED_RIGHT_SPECIMEN_PUSH")
public class Red_Right_Specimen_Push extends LinearOpMode {
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

        // Move forward to the bar
        Log.i("5960", "Moving towards the submersible and lifting gnasher.");
        SubSystem.DriveAll(0.4, -1400, 0, 0, 0,0 ,0.8,2000);
        // Clip the specimen onto the bar
        Log.i("5960", runtime.milliseconds() +  ": Bringing gnasher down to clip specimen.");
        SubSystem.DriveAll(0, 0, 0, 0,0 ,0,1,1000);

        // Strafe towards the wall
        Log.i("5960", runtime.milliseconds() +  ": Strafe diagonal to get robot in between the submersible and the elements on ground.");
        SubSystem.StrafeAll(0.4, 0.1, 1750, 0, 0,0 ,0,1,0);
        // Move forward to get behind the elements

        Log.i("5960", runtime.milliseconds() +  ": Move forward to get behind the elements on the floor.");
        SubSystem.DriveAll(0.6, -1650, 0, 0, 0,0 ,0,0);

        SubSystem.StrafeAll(0.6, 400, 0, 0,0 ,0,1,0);

        SubSystem.DriveAll(0.6, 2000, 0, 0, 0,0 ,0,0);

        SubSystem.DriveAll(0.6, -1900, 0, 0, 0,0 ,0,0);

        SubSystem.StrafeAll(0.6, 425, 0, 0,0 ,0,1,0);

        SubSystem.DriveAll(0.6, 1800, 0, 0, 0,0 ,0,0);

//        SubSystem.DriveAll(0.7, -1900, 0, 0, 0,0 ,0,0);
//
//        SubSystem.StrafeAll(0.8, 350, 0, 0,0 ,0,1,0);
//
//        SubSystem.DriveAll(0.8, 1800, 0, 0, 0,0 ,0,0);



        SubSystem.DriveAll(0.6, -300, 0, 0, 0,0 ,0,0);

        Log.i("5960", runtime.milliseconds() +  ": Turning to face the specimen hanging on the wall.");
        SubSystem.TurnAll(0.4, -172, 0, 0, 0, 0, 0, 0);
        // Strafe to the wall
        Log.i("5960", runtime.milliseconds() +  ": Strafing into wall to align with specimen.");
        SubSystem.StrafeAll(0.8, -800, 0, 0,0 ,0,0,0);


        // Move forward to pick up the specimen
        Log.i("5960", runtime.milliseconds() +  ": Moving forward into wall to pick up specimen.");
        SubSystem.DriveAll(0.6, -1000, 0, 0, 0,0 ,0,0);
        // Pick up the specimen
        Log.i("5960", runtime.milliseconds() +  ": Lifting the gnasher to pick up the specimen.");
        SubSystem.DriveAll(0, 0, 0, 0,0 ,0,0.4,400);


        // Move diagonal towards the bars
        Log.i("5960", runtime.milliseconds() +  ": Strafing diagonally away from the wall and towards submersible.");
        SubSystem.StrafeAll(0.4, 0.1, 2200, 0, 0,0 ,0,0,0);
        // Turn to face the bar again
        Log.i("5960", runtime.milliseconds() +  ": Turning to face the submersible.");
        SubSystem.TurnAll(0.4, 175, 0, 0, 0, 0, 0, 0);
        // Strace to the front of the bar
        Log.i("5960", runtime.milliseconds() +  ": Strafe in front of submersible to align the specimen with the bar.");
        SubSystem.StrafeAll(0.4, -880, 0, 0,0 ,0,0.8,2000);
        // Drive into the bar
        Log.i("5960", runtime.milliseconds() +  ": Move closer to the bar to align submersible over the bar.");
        SubSystem.DriveAll(0.4, -550, 0, 0, 0,0 ,0.8,2000);
        // Clip the specimen onto the bar
        Log.i("5960", runtime.milliseconds() +  ": Bringing gnasher down to clip specimen.");
        SubSystem.DriveAll(0, 0, 0, 0,0 ,0,1,1000);


        // Move diagonal toward the wall and park
        Log.i("5960", runtime.milliseconds() +  ": Strafing diagonally away from submersible and toward wall.");
        SubSystem.StrafeAll(1, 0.3, 2200, 0, 0,0 ,0,1,0);

        SubSystem.DriveAll(0.4, 200, 0, 0, 0,0 ,0,0);
//        sleep(30000);
    }
}
