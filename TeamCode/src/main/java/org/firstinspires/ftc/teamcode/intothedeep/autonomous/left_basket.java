

package org.firstinspires.ftc.teamcode.intothedeep.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.intothedeep.player.Intake;
import org.firstinspires.ftc.teamcode.intothedeep.player.Latcher;

@Autonomous(name = "left_basket")
public class left_basket extends LinearOpMode {

    final double minPower = 0.3;

    void initialize() {
        SubSystem.initialize(this, 60);
        Intake.initialize(this);
        Intake.close();
        Latcher.initialize(this);
        Latcher.close();
        telemetry.addLine("READY!!");
        telemetry.update();
    }

    @Override
    public void runOpMode() {
        initialize();
        boolean runToHighChamber = false;
        while (opModeInInit()) {
          if (gamepad1.b || gamepad2.b) runToHighChamber = false;
          else if (gamepad1.x || gamepad2.x) runToHighChamber = false;

          telemetry.addLine("READY!!");
          if (runToHighChamber) telemetry.addLine("Take blue specimen to high chamber.");
          else telemetry.addLine("Take yellow sample to high basket.");
          telemetry.update();
        }

//        if (runToHighChamber) {
            /* ==== PLACE THE SPECIMEN ON THE HIGH CHAMBER ==== */
//            specimenToHighChamber();

            /* ==== MOVE TO PICK UP FIRST FLOOR SAMPLE === */
            //pickupFirstSample();
//        } else {
            SampleToBasket.run(this, minPower);

//           pickupFirstSample2();
//        }

        /* ==== PLACE THE FIRST SAMPLE IN THE HIGH BASKET ==== */
        //firstSampleToHighBasket();

        /* ==== MOVE TO PICK UP SECOND FLOOR SAMPLE === */
        //pickupSecondSample();

        /* ==== PLACE THE SECOND SAMPLE IN THE HIGH BASKET ==== */
        //secondSampleToHighBasket();

        /* ==== PUSH THE THIRD SAMPLE INTO THE NET ZONE ==== */
        //thirdSampleToNetZone();

//        sleep(30000);
    }

    void specimenToHighChamber() {
        Intake.open();
        // Strafe diagonal and lift the specimen above the high chamber on the submersible
        SubSystem.StrafeAll(0.1, -0.4, -2100, 0, 0,0 ,0,1,2000);
        // Bring the specimen down placing it on the high chamber
        SubSystem.DriveAll(0, 0, 0, 0,0 ,0,1,1000);
    }

    void pickupFirstSample() {
        // Back away from the submersible
        SubSystem.DriveAll(Utility.MIN_MOTOR_POW, 390, 0, 0,0 ,0,0,0);
        // Strafe towards the net zone, baskets
        SubSystem.StrafeAll(-Utility.MIN_MOTOR_POW, 2540, 0, 0,0 ,0,1,270);
        // Move forward to align the claw with the first sample
        SubSystem.DriveAll(Utility.MIN_MOTOR_POW, -300, 0, 0,0 , 0,0,0);
        // Close the claw to grab the first floor sample
        Intake.close();
        sleep(Utility.CLOSE_CLAW_DELAY);
        // Slowly list to close onto the first sample
        SubSystem.DriveAll(0, 0, 0.3, 200,0, 0,0,0);
    }








    void thirdSampleToNetZone() {
        SubSystem.DriveAll(Utility.MIN_MOTOR_POW, 1900, 0, 0,1 ,0,0,0);
        // Turn to face the third sample
        SubSystem.StrafeAll(0.5, 900, 0, 0,0 ,0,0,0);
       // SubSystem.TurnAll(Utility.MIN_MOTOR_POW, -75, 1, 0, 1, 0, 0, 0);
        // Move forward past the third sample
        SubSystem.DriveAll(0.5, -1900, 1, 0,1 ,1,0,0);
       // Intake.close();
        //sleep(Utility.CLOSE_CLAW_DELAY);
        // Strafe towards the wall to get behind third sample
        //SubSystem.StrafeAll(-0.7, 400, 0, 0,0 ,0,0,0);
        //SubSystem.DriveAll(0.2, 250, 0, 0,0 ,0,0,0);
        //SubSystem.StrafeAll(-0.7, -700, 0, 0,0 ,0,0,0);
        // Move backwards to push the third sample into the net zone.
        //SubSystem.DriveAll(0.7, -1500, 1, 0,0 ,0,0,0);
    }
}
