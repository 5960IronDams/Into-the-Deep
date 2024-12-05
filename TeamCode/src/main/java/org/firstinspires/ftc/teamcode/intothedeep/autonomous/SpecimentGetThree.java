package org.firstinspires.ftc.teamcode.intothedeep.autonomous;

public class SpecimentGetThree {
    private static final double MIN_POWER = 0.5;

    public static void run() {
        /* ==== PLACE THE SPECIMEN ON THE HIGH CHAMBER ==== */
        firstMoveToHighChamber();

        /* ==== PUSH THE FIRST SAMPLE TO THE OBSERVATION ZONE ==== */
        firstSampleToObservationZone();


        /* ==== PLACE THE SECOND SPECIMEN ON THE HIGH CHAMBER ==== */
        firstSpecimentToHighChamber();

        secondSpecimentToHighChamber();

        /* ==== PARK IN THE OBSERVATION ZONE ==== */
        parkInObservationZone();
    }

    static void firstMoveToHighChamber() {
        // Move forward to the bar
        SubSystem.DriveAll(0.6, -1400, 0, 0, 0,0 ,1,2000);
        // Clip the specimen onto the bar
        SubSystem.DriveAll(0, 0, 0, 0,0 ,0,1,1000);
    }

    static void firstSampleToObservationZone() {
        // Strafe diagonal towards the wall
        SubSystem.StrafeAll(0.7, 0.1, 1750, 0, 0,0 ,0,1,0);
        // Move forward to get behind the first sample on floor
        SubSystem.DriveAll(0.7, -1700, 0, 0, 0,0 ,0,0);
        // Strafe towards the wall to get behind first sample on the floor
        SubSystem.StrafeAll(0.7, 525, 0, 0,0 ,0,1,0);
        // Drive backwards to push first sample to observation zone
        SubSystem.DriveAll(1, 1950, 0, 0, 0,0 ,0,0);
    }

    static void firstSpecimentToHighChamber() {
        // Back up to clear submersible
        SubSystem.DriveAll(MIN_POWER, 200, 0, 0, 0,0 ,0,0);
        // Move forward out of the observation zone
        SubSystem.DriveAll(1, -450, 0, 0, 0,0 ,0,0);
        // Turn to face the observation zone
        SubSystem.TurnAll(1, -150, 0, 0, 0, 0, 0, 0);
        // Strafe into the wall to align with the specimen
        SubSystem.StrafeAll(MIN_POWER, -1075, 0, 0,0 ,0,0,0);
        // Move forward into the observation zone and clip onto the specimen
        SubSystem.DriveAll(MIN_POWER, -975, 0, 0, 0,0 ,0,0);
        // Pick up the specimen hanging on the wall
        SubSystem.DriveAll(0, 0, 0, 0,0 ,0,0.4,400);
        // Strafe diagonal towards the submersible
        SubSystem.StrafeAll(MIN_POWER, 0.1, 2200, 0, 0,0 ,0,0,0);
        // Turn to face the submersible
        SubSystem.TurnAll(MIN_POWER, 175, 0, 0, 0, 0, 0, 0);
        // Strafe towards the submersible to align the specimen with the high chamber
        SubSystem.StrafeAll(MIN_POWER, -880, 0, 0,0 ,0,0.8,2000);
        // Drive forward towards the submersible to place the specimen above the high chamber
        SubSystem.DriveAll(MIN_POWER, -630, 0, 0, 0,0 ,0.8,2000);
        // Lower the speciment onto the high chamber
        SubSystem.DriveAll(0, 0, 0, 0,0 ,0,1,1000);
    }

    static void secondSpecimentToHighChamber() {
        // Back up to clear submersible
        SubSystem.DriveAll(MIN_POWER, 200, 0, 0, 0,0 ,0,0);
        // Strafe away from the submersible back to the observation zone
        SubSystem.StrafeAll(1, 0.5, 1750, 0, 0,0 ,0,1,0);
        // Turn to face the observation zone
        SubSystem.TurnAll(1, -150, 0, 0, 0, 0, 0, 0);
        // Strafe into the wall to align with the specimen
        SubSystem.StrafeAll(MIN_POWER, -975, 0, 0,0 ,0,0,0);
        // Move forward into the observation zone and clip onto the specimen
        SubSystem.DriveAll(MIN_POWER, -950, 0, 0, 0,0 ,0,0);
        // Pick up the specimen hanging on the wall
        SubSystem.DriveAll(0, 0, 0, 0,0 ,0,0.4,400);
        // Strafe diagonal towards the submersible
        SubSystem.StrafeAll(MIN_POWER, 0.1, 2200, 0, 0,0 ,0,0,0);
        // Turn to face the submersible
        SubSystem.TurnAll(MIN_POWER, 175, 0, 0, 0, 0, 0, 0);
        // Strafe towards the submersible to align the specimen with the high chamber
        SubSystem.StrafeAll(MIN_POWER, -920, 0, 0,0 ,0,0.8,2000);
        // Drive forward towards the submersible to place the specimen above the high chamber
        SubSystem.DriveAll(MIN_POWER, -600, 0, 0, 0,0 ,0.8,2000);
        // Lower the speciment onto the high chamber
        SubSystem.DriveAll(0, 0, 0, 0,0 ,0,1,1000);
    }

    static void parkInObservationZone() {
        // Strafe backwards diagonal towards the observation zone
        SubSystem.StrafeAll(1, 0.3, 2200, 0, 0,0 ,0,1,0);
        // Back into the observation zone
        SubSystem.DriveAll(1, 400, 0, 0, 0,0 ,0,0);
    }
}
