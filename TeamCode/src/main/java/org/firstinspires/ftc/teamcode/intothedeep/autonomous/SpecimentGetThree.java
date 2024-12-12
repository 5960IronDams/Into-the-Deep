package org.firstinspires.ftc.teamcode.intothedeep.autonomous;

import android.util.Log;

import org.firstinspires.ftc.teamcode.core.Drive;

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
        Log.d("5960", "firstMoveToHighChamber");
        // Move forward to the bar
        Log.d("5960", "1. strafe");
        SubSystem.StrafeAll(0.4, -0.2, -1850, 0, 0,0 ,0,1,2000);
        // Clip the specimen onto the bar
        Log.d("5960", "2. drive");
        SubSystem.DriveAll(0, 0, 0, 0,0 ,0,1,1000, 650);
    }

    static void firstSampleToObservationZone() {
        Log.d("5960", "firstSampleToObservationZone");
        // Strafe diagonal towards the wall
        Log.d("5960", "1. strafe: Strafe diagonal towards the wall");
        SubSystem.StrafeAll(0.7, 0.3, 1985, 0, 0,0 ,0,1,-50);
        // Move forward to get behind the first sample on floor
        Log.d("5960", "2. drive: Move forward to get behind the first sample on floor");
        SubSystem.DriveAll(0.6, -1570, 0, 0, 0,0 ,0,0);
        // Strafe towards the wall to get behind first sample on the floor
        Log.d("5960", "3. strafe: Strafe towards the wall to get behind first sample on the floor");
        SubSystem.StrafeAll(0.7, 525, 0, 0,0 ,0,0,0);
        // Drive backwards to push first sample to observation zone
        Log.d("5960", "4. drive: Drive backwards to push first sample to observation zone");
        SubSystem.DriveAll(1, 1950, 0, 0, 0,0 ,0,0);
    }

    static void firstSpecimentToHighChamber() {
        Log.d("5960", "firstSpecimentToHighChamber");
        // Back up to clear submersible
        Log.d("5960", "1. drive: Back up to clear submersible");
        SubSystem.DriveAll(MIN_POWER, 200, 0, 0, 0,0 ,0,0);
        // Move forward out of the observation zone
        Log.d("5960", "2. drive: Move forward out of the observation zone");
        SubSystem.DriveAll(1, -485, 0, 0, 0,0 ,0,0);
        // Turn to face the observation zone
        Log.d("5960", "3. turn: Turn to face the observation zone");
        SubSystem.TurnAll(1, -150, 0, 0, 0, 0, 0, 0);
        // Strafe into the wall to align with the specimen
        Log.d("5960", "4. strafe: Strafe into the wall to align with the specimen");
        SubSystem.StrafeAll(0.8, -1075, 0, 0,0 ,0,0,0);
        // Move forward into the observation zone and clip onto the specimen
        Log.d("5960", "5. drive: Move forward into the observation zone and clip onto the specimen");
        SubSystem.DriveAll(0.8, -975, 0, 0, 0,0 ,0,0, 1000);
//        // Pick up the specimen hanging on the wall
        Log.d("5960", "6. drive: Pick up the specimen hanging on the wall");
        SubSystem.DriveAll(0, 0, 0, 0,0 ,0,0.4,400);
//        // Strafe diagonal towards the submersible
        Log.d("5960", "7. strafe: Strafe diagonal towards the submersible");
       SubSystem.StrafeAll(MIN_POWER, 0.1, 2200, 0, 0,0 ,0,0,0);
//        // Turn to face the submersible
        Log.d("5960", "8. turn: Turn to face the submersible");
        SubSystem.TurnAll(0.7, 164, 0, 0, 0, 0, 0, 0);
//        // Strafe towards the submersible to align the specimen with the high chamber
        Log.d("5960", "9. strafe: Strafe towards the submersible to align the specimen with the high chamber");
        SubSystem.StrafeAll(MIN_POWER, -880, 0, 0,0 ,0,0.8,2000);
//        // Drive forward towards the submersible to place the specimen above the high chamber
        Drive.setPositionTolerance(200);
        Log.d("5960", "10. drive: Drive forward towards the submersible to place the specimen above the high chamber");
        SubSystem.DriveAll(MIN_POWER, -630, 0, 0, 0,0 ,0.8,2000, 1000);
        Drive.setPositionTolerance(100);
//        // Lower the speciment onto the high chamber
        Log.d("5960", "11. drive: Lower the speciment onto the high chamber");
        SubSystem.DriveAll(0, 0, 0, 0,0 ,0,1,1000);
    }

    static void secondSpecimentToHighChamber() {
        Log.d("5960", "secondSpecimentToHighChamber");
        // Back up to clear submersible
        Log.d("5960", "1. drive: Back up to clear submersible");
        SubSystem.DriveAll(MIN_POWER, 400, 0, 0, 0,0 ,0,0);
        // Strafe away from the submersible back to the observation zone
        Log.d("5960", "2.  strafe: Strafe away from the submersible back to the observation zone");
        SubSystem.StrafeAll(1, 0.6, 1750, 0, 0,0 ,0,1,-50);
        // Turn to face the observation zone
        Log.d("5960", "3. turn: Turn to face the observation zone");
        SubSystem.TurnAll(1, -150, 0, 0, 0, 0, 0, 0);
        // Strafe into the wall to align with the specimen
        Log.d("5960", "4. strafe: Strafe into the wall to align with the specimen");
        SubSystem.StrafeAll(0.8, -925, 0, 0,0 ,0,0,0);
        // Move forward into the observation zone and clip onto the specimen
        Drive.setPositionTolerance(200);
        Log.d("5960", "5. drive - adjust: Move forward into the observation zone and clip onto the specimen");
        SubSystem.DriveAll(0.8, -900, 0, 0, 0,0 ,0,0);
        Drive.setPositionTolerance(100);
        // Pick up the specimen hanging on the wall
        Log.d("5960", "6. drive: Pick up the specimen hanging on the wall");
        SubSystem.DriveAll(0, 0, 0, 0,0 ,0,0.4,400);
        // Strafe diagonal towards the submersible
        Log.d("5960", "7. strafe: Strafe diagonal towards the submersible");
        SubSystem.StrafeAll(MIN_POWER, 0.1, 2200, 0, 0,0 ,0,0,0);
        // Turn to face the submersible
        Log.d("5960", "8. turn: Turn to face the submersible");
        SubSystem.TurnAll(0.7, 164, 0, 0, 0, 0, 0, 0);
        // Strafe towards the submersible to align the specimen with the high chamber
        Log.d("5960", "9. strafe: Strafe towards the submersible to align the specimen with the high chamber");
        SubSystem.StrafeAll(MIN_POWER, -1100, 0, 0,0 ,0,0.8,2000);
        // Drive forward towards the submersible to place the specimen above the high chamber
        Drive.setPositionTolerance(200);
        Log.d("5960", "10. drive - adjust: Drive forward towards the submersible to place the specimen above the high chamber");
        SubSystem.DriveAll(0.8, -600, 0, 0, 0,0 ,0.8,2000, 1000);
        Drive.setPositionTolerance(100);
        // Lower the specimen onto the high chamber
        Log.d("5960", "11. drive: Lower the specimen onto the high chamber");
        SubSystem.DriveAll(0, 0, 0, 0,0 ,0,1,1000);
    }

    static void parkInObservationZone() {
        Log.d("5960", "parkInObservationZone");
        // Strafe backwards diagonal towards the observation zone
        Log.d("5960", "1. strafe: Strafe backwards diagonal towards the observation zone");
        SubSystem.StrafeAll(1, 0.1, 2200, 0, 0,0 ,0,1,-20);
        // Back into the observation zone
        Log.d("5960", "2. drive: Back into the observation zone");
        SubSystem.DriveAll(1, 400, 0, 0, 0,0 ,0,0);
    }
}
