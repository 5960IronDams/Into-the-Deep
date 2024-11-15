package org.firstinspires.ftc.teamcode.intothedeep.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.intothedeep.player.Intake;
import org.firstinspires.ftc.teamcode.intothedeep.player.Latcher;

@Autonomous(name = "RED_left_basket")
public class RED_left_basket extends LinearOpMode {
    void initialize() {
        SubSystem.initialize(this);
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
        parkInAcentZone();
    }

    void specimenToHighChamber() {
        // Strafe diagonal and lift the specimen above the high chamber on the submersible
        SubSystem.StrafeAll(0.1, -0.4, -2100, 0, 0,0 ,0,1,2000);
        // Bring the specimen down placing it on the high chamber
        SubSystem.DriveAll(0, 0, 0, 0,0 ,0,1,1000);
    }

    void pickupFirstSample() {
        // Back away from the submersible
        SubSystem.DriveAll(Utilitiy.MIN_MOTOR_POW, 400, 0, 0,0 ,0,1,1000);
        // Strafe towards the net zone, baskets
        SubSystem.StrafeAll(-Utilitiy.MIN_MOTOR_POW, 2525, 0, 0,0 ,0,1,0);
        // Move forward to align the claw with the first sample
        SubSystem.DriveAll(Utilitiy.MIN_MOTOR_POW, -275, 0, 0,0 , 0,0,0);
        // Close the claw to grab the first floor sample
        Intake.close();
        sleep(Utilitiy.CLOSE_CLAW_DELAY);
        // Slowly list to close onto the first sample
        SubSystem.DriveAll(0, 0, 0.3, 200,0, 0,0,0);
    }

    void firstSampleToHighBasket() {
        // Turn to face the net zone and lift the first sample into position
        SubSystem.TurnAll(Utilitiy.MIN_MOTOR_POW, 145, 1, -3350, 0, 0, 0, 0);
        // Move forward into the net zone and extend the first sample above the high basket
        SubSystem.DriveAll(Utilitiy.MIN_MOTOR_POW, -960, 1, -3350,1 ,-850,0,0);
        // Open the claw dropping the first sample into the high basket
        Intake.open();
        sleep(Utilitiy.OPEN_CLAW_DELAY);
    }

    void pickupSecondSample() {
        // Turn to face the second second floor sample
        SubSystem.TurnAll(Utilitiy.MIN_MOTOR_POW, -139, 0, 0, 1, -10, 0, 0);
        // Move forward to align the claw with the second sample
        SubSystem.DriveAll(Utilitiy.MIN_MOTOR_POW, -720, 1, 0,0 ,0,0,0);
        // Close the claw to grab the second floor sample
        Intake.close();
        sleep(Utilitiy.CLOSE_CLAW_DELAY);
        // Slowly list to close onto the first sample
        SubSystem.DriveAll(0, 0, 0.3, 200,0, 0,0,0);
    }

    void secondSampleToHighBasket() {
        // lift the second sample into position
        SubSystem.DriveAll(0, 0, 1, -3350,0 ,0,0,0);
        // Turn to face the net zone
        SubSystem.TurnAll(Utilitiy.MIN_MOTOR_POW, 159, 1, -3350, 1, 0, 0, 0);
        // Move forward into the net zone and extend the second  sample above the high basket
        SubSystem.DriveAll(Utilitiy.MIN_MOTOR_POW, -750, 0, 0,1 ,-850,0,0);
        // Open the claw dropping the first sample into the high basket
        Intake.open();
        sleep(Utilitiy.OPEN_CLAW_DELAY);
    }

    void parkInAcentZone() {
        // Turn to face the third sample
        SubSystem.TurnAll(Utilitiy.MIN_MOTOR_POW, -210, 0, 0, 0, 0, 0, 0);
        // Move forward past the third sample
        SubSystem.DriveAll(0.5, -2000, 1, 0,1 ,1,0,0);
        // Strafe into ascent zone
//        SubSystem.StrafeAll(-0.7, -2000, 0, 0,0 ,0,0,0);
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
