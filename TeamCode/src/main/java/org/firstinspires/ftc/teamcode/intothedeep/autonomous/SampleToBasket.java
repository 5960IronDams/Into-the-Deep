package org.firstinspires.ftc.teamcode.intothedeep.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.intothedeep.player.Intake;

public class SampleToBasket {

    public static void run(LinearOpMode linearOpMode, double minPower) {
        sampleToHighBasket(linearOpMode, minPower);
        pickupFirstFloorSample(linearOpMode, minPower);
        firstFloorSampleToHighBasket(linearOpMode, minPower);
        pickupFloorSecondSample(linearOpMode, minPower);
        secondFloorSampleToHighBasket(linearOpMode, minPower);
        parkInAscentZone(minPower);
    }

    static void sampleToHighBasket(LinearOpMode linearOpMode, double minPower) {
        SubSystem.DriveAll(0, 0, 1, Utility.AUTO_LIFT_HIGHBASKET,0, 0,1,500);
        // move over to net zone and lift sample up to high basket
        SubSystem.DriveAll(minPower, -735, 1, Utility.AUTO_LIFT_HIGHBASKET,1, -970,0,0);
        SubSystem.TurnAll(minPower, 16, 0, 0, 0, 0, 0, 0);
        linearOpMode.sleep(300);
        Intake.open();
        linearOpMode.sleep(Utility.OPEN_CLAW_DELAY);
    }

    static void pickupFirstFloorSample(LinearOpMode linearOpMode, double minPower) {
        // Turn to face the second second floor sample
        SubSystem.TurnAll(minPower,-38, 0, 0, 0, 0, 0, 0);
        SubSystem.TurnAll(minPower,-60, 0, 0, 1, -10, 0, 0);
        // Move forward to align the claw with the second sample
        SubSystem.DriveAll(minPower, -1060, 1, 2,0 ,0,0,0);
        // Close the claw to grab the second floor sample
        Intake.close();
        linearOpMode.sleep(Utility.CLOSE_CLAW_DELAY);
        // Slowly list to close onto the first sample
        SubSystem.DriveAll(0, 0, 0.3, 200,0, 0,0,0);
    }

    static void firstFloorSampleToHighBasket(LinearOpMode linearOpMode, double minPower) {
        // Turn to face the net zone and lift the first sample into position
        SubSystem.TurnAll(minPower, 145, 1, Utility.AUTO_LIFT_HIGHBASKET, 0, 0, 0, 0);
        // Move forward into the net zone and extend the first sample above the high basket
        SubSystem.DriveAll(minPower, -730, 1, Utility.AUTO_LIFT_HIGHBASKET,1, -970,0,0);
        // Open the claw dropping the first sample into the high basket
        Intake.open();
        linearOpMode.sleep(Utility.OPEN_CLAW_DELAY);
    }

    static void pickupFloorSecondSample(LinearOpMode linearOpMode, double minPower) {
        // Turn to face the second second floor sample
        SubSystem.TurnAll(minPower, -131, 0, 0, 1, -10, 0, 0);
        // Move forward to align the claw with the second sample
        SubSystem.DriveAll(minPower, -610, 1, 0,0, 0,0,0);
        // Close the claw to grab the second floor sample
        Intake.close();
        linearOpMode.sleep(Utility.CLOSE_CLAW_DELAY);
        // Slowly list to close onto the first sample
        SubSystem.DriveAll(0, 0, 0.3, 200,0, 0,0,0);
    }

    static void secondFloorSampleToHighBasket(LinearOpMode linearOpMode, double minPower) {
        // lift the second sample into position
        SubSystem.DriveAll(0, 0, 1, Utility.AUTO_LIFT_HIGHBASKET,0 ,0,0,0);
        // Turn to face the net zone
        SubSystem.TurnAll(minPower, 143, 0, 0, 1, 0, 0, 0);
        // Move forward into the net zone and extend the second  sample above the high basket
        SubSystem.DriveAll(minPower, -650, 1, Utility.AUTO_LIFT_HIGHBASKET,0.7 ,-970,0,0);
        // Open the claw dropping the first sample into the high basket
        Intake.open();
        linearOpMode.sleep(Utility.OPEN_CLAW_DELAY);
    }

    static void parkInAscentZone(double minPower) {
        // Drive forward into submersible
        // Strafe diagonal towards the submersible
        SubSystem.DriveAll(0.7, 125, 0, 0,0 ,0,0,0);
        SubSystem.DriveAll(0.7, 1300, 1, 0,1 ,0,1,0);
        SubSystem.StrafeAll(0.7, -1600, 0, 0,0 ,0,0,0);
    }
}
