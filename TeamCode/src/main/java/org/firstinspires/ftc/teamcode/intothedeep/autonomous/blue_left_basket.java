

package org.firstinspires.ftc.teamcode.intothedeep.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.intothedeep.player.Intake;
import org.firstinspires.ftc.teamcode.intothedeep.player.Latcher;

@Autonomous(name = "blue_left_basket")
public class blue_left_basket extends LinearOpMode {
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
        boolean runToHighChamber = true;
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

        /* ==== PLACE THE FIRST SAMPLE IN THE HIGH BASKET ==== */
        firstSampleToHighBasket();

        /* ==== MOVE TO PICK UP SECOND FLOOR SAMPLE === */
        pickupSecondSample();

        /* ==== PLACE THE SECOND SAMPLE IN THE HIGH BASKET ==== */
        secondSampleToHighBasket();

        /* ==== PUSH THE THIRD SAMPLE INTO THE NET ZONE ==== */
        thirdSampleToNetZone();
//
//        /* ==== PARK IN THE ASCENT ZONE ==== */
////        parkInAscentZone();
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
        SubSystem.DriveAll(Utilitiy.MIN_MOTOR_POW, 390, 0, 0,0 ,0,0,0);
        // Strafe towards the net zone, baskets
        SubSystem.StrafeAll(-Utilitiy.MIN_MOTOR_POW, 2540, 0, 0,0 ,0,1,270);
        // Move forward to align the claw with the first sample
        SubSystem.DriveAll(Utilitiy.MIN_MOTOR_POW, -300, 0, 0,0 , 0,0,0);
        // Close the claw to grab the first floor sample
        Intake.close();
        sleep(Utilitiy.CLOSE_CLAW_DELAY);
        // Slowly list to close onto the first sample
        SubSystem.DriveAll(0, 0, 0.3, 200,0, 0,0,0);
    }

    void pickupFirstSample2() {
        // Turn to face the second second floor sample
        SubSystem.TurnAll(Utilitiy.MIN_MOTOR_POW,-93, 0, 0, 1, -10, 0, 0);
        // Strafe towards the net zone, baskets
        SubSystem.StrafeAll(Utilitiy.MIN_MOTOR_POW, -590, 1, 0,1 ,-100,1,270);
        // Move forward to align the claw with the second sample
        SubSystem.DriveAll(Utilitiy.MIN_MOTOR_POW, -1125, 1, 0,0 ,0,0,0);
        // Close the claw to grab the second floor sample
        Intake.close();
        sleep(Utilitiy.CLOSE_CLAW_DELAY);
        // Slowly list to close onto the first sample
        SubSystem.DriveAll(0, 0, 0.3, 200,0, 0,0,0);
    }


    void sampleToHighBasket() {
        SubSystem.DriveAll(0, 0, 1, Utilitiy.MID_BASKET_HEIGHT,0, 0,1,270);
        // move over to net zone and lift sample up to high basket
        SubSystem.DriveAll(Utilitiy.MIN_MOTOR_POW, -175, 1, Utilitiy.MID_BASKET_HEIGHT,1, Utilitiy.EXTENDER_REACH,0,0);
        SubSystem.TurnAll(Utilitiy.MIN_MOTOR_POW, 8, 0, 0, 0, 0, 0, 0);
        sleep(150);
        Intake.open();
        sleep(Utilitiy.OPEN_CLAW_DELAY);

    }

    void firstSampleToHighBasket() {
        // Turn to face the net zone and lift the first sample into position
        SubSystem.TurnAll(Utilitiy.MIN_MOTOR_POW, 146, 1, Utilitiy.MID_BASKET_HEIGHT, 0, 0, 0, 0);
        // Move forward into the net zone and extend the first sample above the high basket
        SubSystem.DriveAll(Utilitiy.MIN_MOTOR_POW, -345, 1, Utilitiy.MID_BASKET_HEIGHT,1, Utilitiy.EXTENDER_REACH,0,0);
        // Open the claw dropping the first sample into the high basket
        Intake.open();
        sleep(Utilitiy.OPEN_CLAW_DELAY);
    }

    void pickupSecondSample() {
        // Turn to face the second second floor sample
        SubSystem.TurnAll(Utilitiy.MIN_MOTOR_POW, -145, 0, 0, 1, -10, 0, 0);
        // Move forward to align the claw with the second sample
        // Strafe towards the net zone, baskets
        SubSystem.StrafeAll(Utilitiy.MIN_MOTOR_POW, -350, 1, 0,1 ,-10,1,270);
        SubSystem.DriveAll(Utilitiy.MIN_MOTOR_POW, -345, 0, 0,0, 0,0,0);
        // Close the claw to grab the second floor sample
        Intake.close();
        sleep(Utilitiy.CLOSE_CLAW_DELAY);
        // Slowly list to close onto the first sample
        SubSystem.DriveAll(0, 0, 0.3, 200,0, 0,0,0);
    }

    void secondSampleToHighBasket() {
        // lift the second sample into position
        SubSystem.DriveAll(0, 0, 1, Utilitiy.HIGH_BASKET_HEIGHT,0 ,0,0,0);
        // Turn to face the net zone
        SubSystem.TurnAll(Utilitiy.MIN_MOTOR_POW, 155, 0, 0, 1, 0, 0, 0);
        // Move forward into the net zone and extend the second  sample above the high basket
        SubSystem.DriveAll(Utilitiy.MIN_MOTOR_POW, -200, 1, Utilitiy.MID_BASKET_HEIGHT,0.7 ,Utilitiy.EXTENDER_REACH,0,0);
        // Open the claw dropping the first sample into the high basket
        Intake.open();
        sleep(Utilitiy.OPEN_CLAW_DELAY);
    }

    void parkInAscentZone() {
        // Turn to face the third sample
        SubSystem.TurnAll(Utilitiy.MIN_MOTOR_POW, -192, 0, 0, 1, Utilitiy.EXTENDER_REACH, 0, 0);
        // Move forward past the third sample
        SubSystem.DriveAll(0.7, -2200, 0.4, -2075,1 ,1,1,0);
        // Strafe into ascent zone
//        SubSystem.StrafeAll(-0.5, -400, 0, 0,0 ,0,0,0);
        // Turn to touch the middle bar on the submersible
        SubSystem.TurnAll(0.7, -45, 0, 0, 1, 1, 0, 0);
        // Drive forward into submersible
        SubSystem.DriveAll(0.5, -225, 0, 0,0 ,0,0,0);
    }

    void thirdSampleToNetZone() {
        // Turn to face the third sample
        SubSystem.TurnAll(Utilitiy.MIN_MOTOR_POW, 10, 0, 0, 1, 0, 0, 0);
        // Move forward past the third sample
        SubSystem.DriveAll(0.5, 500, 1, 0,1 ,1,0,0);
        // Strafe towards the wall to get behind third sample
        SubSystem.StrafeAll(0.7, 250, 0, 0,0 ,0,0,0);
        SubSystem.StrafeAll(-0.7, -350, 0, 0,0 ,0,0,0);
        // Move backwards to push the third sample into the net zone.
        SubSystem.DriveAll(0.7, -1000, 0, 0,0 ,0,0,0);
    }
}
