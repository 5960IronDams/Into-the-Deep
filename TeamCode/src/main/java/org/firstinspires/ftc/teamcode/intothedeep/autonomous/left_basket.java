

package org.firstinspires.ftc.teamcode.intothedeep.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.intothedeep.player.Intake;
import org.firstinspires.ftc.teamcode.intothedeep.player.Latcher;

@Autonomous(name = "left_basket")
public class left_basket extends LinearOpMode {
    void initialize() {
        SubSystem.initialize(this);
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
          if (gamepad1.b || gamepad2.b) {
            runToHighChamber = true;
          } else if (gamepad1.x || gamepad2.x) {
              runToHighChamber = false;
          }

          telemetry.addLine("READY!!");

          if (runToHighChamber) {
            telemetry.addLine("Take blue specimen to high chamber.");
          } else {
            telemetry.addLine("Take yellow sample to high basket.");
          }

          telemetry.update();
        }



        //waitForStart();

        if (runToHighChamber) {
            /* ==== PLACE THE SPECIMEN ON THE HIGH CHAMBER ==== */
            specimenToHighChamber();

            /* ==== MOVE TO PICK UP FIRST FLOOR SAMPLE === */
            pickupFirstSample();
        } else {
            sampleToHighBasket();

            pickupFirstSample2();
        }

//        /* ==== PLACE THE FIRST SAMPLE IN THE HIGH BASKET ==== */
        firstSampleToHighBasket();
//
//        /* ==== MOVE TO PICK UP SECOND FLOOR SAMPLE === */
        pickupSecondSample();
//
//        /* ==== PLACE THE SECOND SAMPLE IN THE HIGH BASKET ==== */
        secondSampleToHighBasket();
//
//        /* ==== PUSH THE THIRD SAMPLE INTO THE NET ZONE ==== */
        thirdSampleToNetZone();

        //sleep(30000);
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

    void pickupFirstSample2() {
        // Turn to face the second second floor sample
        SubSystem.TurnAll(Utility.MIN_MOTOR_POW,-96, 0, 0, 1, -10, 0, 0);
//        // Strafe towards the net zone, baskets
//        SubSystem.StrafeAll(Utility.MIN_MOTOR_POW, -270, 1, 0,1 ,-100,1,270);
//        // Move forward to align the claw with the second sample
        SubSystem.DriveAll(Utility.MIN_MOTOR_POW, -1065, 1, 0,0 ,0,0,0);
        // Close the claw to grab the second floor sample
        Intake.close();
        sleep(Utility.CLOSE_CLAW_DELAY);
//        // Slowly list to close onto the first sample
       SubSystem.DriveAll(0, 0, 0.3, 200,0, 0,0,0);
    }


    void sampleToHighBasket() {
        SubSystem.DriveAll(0, 0, 1, Utility.AUTO_LIFT_HIGHBASKET,0, 0,1,500);
        // move over to net zone and lift sample up to high basket
        SubSystem.DriveAll(Utility.MIN_MOTOR_POW, -715, 1, Utility.AUTO_LIFT_HIGHBASKET,1, -970,0,0);
        SubSystem.TurnAll(Utility.MIN_MOTOR_POW, 12, 0, 0, 0, 0, 0, 0);
        sleep(300);
        Intake.open();
        sleep(Utility.OPEN_CLAW_DELAY);

    }

    void firstSampleToHighBasket() {
        // Turn to face the net zone and lift the first sample into position
        SubSystem.TurnAll(Utility.MIN_MOTOR_POW, 140, 1, Utility.AUTO_LIFT_HIGHBASKET, 0, 0, 0, 0);
        // Move forward into the net zone and extend the first sample above the high basket
        SubSystem.DriveAll(Utility.MIN_MOTOR_POW, -700, 1, Utility.AUTO_LIFT_HIGHBASKET,1, -970,0,0);
        // Open the claw dropping the first sample into the high basket
        Intake.open();
        sleep(Utility.OPEN_CLAW_DELAY);
    }

    void pickupSecondSample() {
        // Turn to face the second second floor sample
        SubSystem.TurnAll(Utility.MIN_MOTOR_POW, -131, 0, 0, 1, -10, 0, 0);
        // Move forward to align the claw with the second sample
        // Strafe towards the net zone, baskets
        //SubSystem.StrafeAll(Utility.MIN_MOTOR_POW, -300, 1, 0,1 ,-10,1,270);
       SubSystem.DriveAll(Utility.MIN_MOTOR_POW, -610, 1, 0,0, 0,0,0);
        // Close the claw to grab the second floor sample
       Intake.close();
        sleep(Utility.CLOSE_CLAW_DELAY);
        // Slowly list to close onto the first sample
        SubSystem.DriveAll(0, 0, 0.3, 200,0, 0,0,0);
    }

    void secondSampleToHighBasket() {
        // lift the second sample into position
        SubSystem.DriveAll(0, 0, 1, Utility.AUTO_LIFT_HIGHBASKET,0 ,0,0,0);
        // Turn to face the net zone
        SubSystem.TurnAll(Utility.MIN_MOTOR_POW, 143, 0, 0, 1, 0, 0, 0);
        // Move forward into the net zone and extend the second  sample above the high basket
        SubSystem.DriveAll(Utility.MIN_MOTOR_POW, -610, 1, Utility.AUTO_LIFT_HIGHBASKET,0.7 ,-970,0,0);
        // Open the claw dropping the first sample into the high basket
        Intake.open();
       sleep(Utility.OPEN_CLAW_DELAY);
    }

//    void parkInAscentZone() {
//        // Turn to face the third sample
//        SubSystem.TurnAll(Utility.MIN_MOTOR_POW, -192, 0, 0, 1, Utility.EXTENDER_REACH, 0, 0);
//        // Move forward past the third sample
//        SubSystem.DriveAll(0.7, -2200, 0.4, -2075,1 ,1,1,0);
//        // Strafe into ascent zone
//        SubSystem.StrafeAll(-0.5, 400, 0, 0,0 ,0,0,0);
//        // Turn to touch the middle bar on the submersible
//        SubSystem.TurnAll(0.7, -45, 0, 0, 1, 1, 0, 0);
//        // Drive forward into submersible
//        SubSystem.DriveAll(0.5, -525, 0, 0,0 ,0,0,0);
//    }

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
