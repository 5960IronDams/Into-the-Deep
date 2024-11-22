package org.firstinspires.ftc.teamcode.intothedeep.autonomous.basement;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.intothedeep.autonomous.SubSystem;
import org.firstinspires.ftc.teamcode.intothedeep.autonomous.Utility;
import org.firstinspires.ftc.teamcode.intothedeep.player.Intake;
import org.firstinspires.ftc.teamcode.intothedeep.player.Latcher;
@Disabled
@Autonomous(name = "RED_left_basket")
public class RED_left_basket extends LinearOpMode {
    void initialize() {
        SubSystem.initialize(this, 100);
        Intake.initialize(this);
        Intake.open();
        Latcher.initialize(this);
        Latcher.close();
        telemetry.addLine("READY!!");
        telemetry.update();
    }

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();

        /* ==== PLACE THE SPECIMEN ON THE HIGH CHAMBER ==== */
        specimenToHighChamber();

        /* ==== MOVE TO PICK UP FIRST FLOOR SAMPLE === */
        pickupFirstSample();

        /* ==== PLACE THE FIRST SAMPLE IN THE HIGH BASKET ==== */
        firstSampleToHighBasket();

        /* ==== MOVE TO PICK UP SECOND FLOOR SAMPLE === */
        pickupSecondSample();

        /* ==== PLACE THE SECOND SAMPLE IN THE HIGH BASKET ==== */
        secondSampleToHighBasket();

        /* ==== PUSH THE THIRD SAMPLE INTO THE NET ZONE ==== */
//        thirdSampleToNetZone();

        /* ==== PARK IN THE ASCENT ZONE ==== */
        parkInAscentZone();
    }

    void specimenToHighChamber() {
        // Strafe diagonal and lift the specimen above the high chamber on the submersible
        SubSystem.StrafeAll(0.1, -0.4, -2100, 0, 0,0 ,0,1,2000);
        // Bring the specimen down placing it on the high chamber
        SubSystem.DriveAll(0, 0, 0, 0,0 ,0,1,1000);
    }

    void pickupFirstSample() {
        // Back away from the submersible
        SubSystem.DriveAll(Utility.MIN_MOTOR_POW, 400, 0, 0,0 ,0,0,0);
        // Strafe towards the net zone, baskets
        SubSystem.StrafeAll(-Utility.MIN_MOTOR_POW, 2555, 0, 0,0 ,0,1,270);
        // Move forward to align the claw with the first sample
        SubSystem.DriveAll(Utility.MIN_MOTOR_POW, -263, 0, 0,0 , 0,0,0);
        // Close the claw to grab the first floor sample
        Intake.close();
        sleep(Utility.CLOSE_CLAW_DELAY);
        // Slowly list to close onto the first sample
        SubSystem.DriveAll(0, 0, 0.3, 200,0, 0,0,0);
    }

    void firstSampleToHighBasket() {
        // Turn to face the net zone and lift the first sample into position
        SubSystem.TurnAll(Utility.MIN_MOTOR_POW, 146, 1, Utility.HIGH_BASKET_HEIGHT, 0, 0, 0, 0);
        // Move forward into the net zone and extend the first sample above the high basket
        SubSystem.DriveAll(Utility.MIN_MOTOR_POW, -1290, 1, Utility.HIGH_BASKET_HEIGHT,1, Utility.EXTENDER_REACH,0,0);
        // Open the claw dropping the first sample into the high basket
        Intake.open();
        sleep(Utility.OPEN_CLAW_DELAY);
    }

    void pickupSecondSample() {
        // Turn to face the second second floor sample
        SubSystem.TurnAll(Utility.MIN_MOTOR_POW, -142, 0, 0, 1, -10, 0, 0);
        // Move forward to align the claw with the second sample
        SubSystem.DriveAll(Utility.MIN_MOTOR_POW, -925, 1, 0,0 ,0,0,0);
        // Close the claw to grab the second floor sample
        Intake.close();
        sleep(Utility.CLOSE_CLAW_DELAY);
        // Slowly list to close onto the first sample
        SubSystem.DriveAll(0, 0, 0.3, 200,0, 0,0,0);
    }

    void secondSampleToHighBasket() {
        // lift the second sample into position
        SubSystem.DriveAll(0, 0, 1, Utility.HIGH_BASKET_HEIGHT,0 ,0,0,0);
        // Turn to face the net zone
        SubSystem.TurnAll(Utility.MIN_MOTOR_POW, 155, 1, Utility.HIGH_BASKET_HEIGHT, 1, 0, 0, 0);
        // Move forward into the net zone and extend the second  sample above the high basket
        SubSystem.DriveAll(Utility.MIN_MOTOR_POW, -900, 0, 0,1 , Utility.EXTENDER_REACH,0,0);
        // Open the claw dropping the first sample into the high basket
        Intake.open();
        sleep(Utility.OPEN_CLAW_DELAY);
    }

    void parkInAscentZone() {
        // Turn to face the third sample
        SubSystem.TurnAll(Utility.MIN_MOTOR_POW, -190, 0, 0, 1, 1, 0, 0);
        // Move forward past the third sample
        SubSystem.DriveAll(0.7, -2200, 1, -2035,0 ,0,1,0);
        // Strafe into ascent zone
//        SubSystem.StrafeAll(-0.5, -400, 0, 0,0 ,0,0,0);
        // Turn to touch the middle bar on the submersible
        SubSystem.TurnAll(0.7, -45, 0, 0, 1, 1, 0, 0);
        // Drive forward into submersible
        SubSystem.DriveAll(0.5, -225, 0, 0,0 ,0,0,0);
    }

//    void thirdSampleToNetZone() {
//        // Turn to face the third sample
//        SubSystem.TurnAll(Utilitiy.MIN_MOTOR_POW, -160, 0, 0, 0, 0, 0, 0);
//        // Move forward past the third sample
//        SubSystem.DriveAll(0.5, -2000, 1, 0,1 ,1,0,0);
//        // Strafe towards the wall to get behind third sample
//        SubSystem.StrafeAll(-0.7, 250, 0, 0,0 ,0,0,0);
//        // Move backwards to push the third sample into the net zone.
//        SubSystem.DriveAll(0.7, 2000, 0, 0,0 ,0,0,0);
//    }
}
