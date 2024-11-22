package org.firstinspires.ftc.teamcode.intothedeep.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.intothedeep.player.Intake;
import org.firstinspires.ftc.teamcode.intothedeep.player.Latcher;

@Autonomous(name = "RIGHT_SPECIMEN")
public class RIGHT_SPECIMEN extends LinearOpMode {
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
        boolean getThree = true;
        initialize();
        while(opModeInInit()) {
            if(gamepad1.x || gamepad2.x) getThree = true;
            else if(gamepad1.b || gamepad2.b) getThree = false;
            telemetry.addLine(getThree ? "GET_THREE": "get_two");
            telemetry.update();
        }

        if(getThree){
            getThirdSpecimen();
        } else{
            getTwoSpecimen();
        }


    }
void getTwoSpecimen(){
    /* ==== PLACE THE SPECIMEN ON THE HIGH CHAMBER ==== */
    firstMoveToHighChamber(Utility.MIN_MOTOR_POW);

    /* ==== PUSH THE FIRST SAMPLE TO THE OBSERVATION ZONE ==== */
    firstSampleToObservationZone();

    /* ==== PUSH THE SECOND SAMPLE TO THE OBSERVATION ZONE ==== */
    secondSampleToObservationZone();

    /* ==== PLACE THE SECOND SPECIMEN ON THE HIGH CHAMBER ==== */
    secondSpecimentToHighChamber();

    /* ==== PARK IN THE OBSERVATION ZONE ==== */
    parkInObservationZone();
}
void getThirdSpecimen(){
    /* ==== PLACE THE SPECIMEN ON THE HIGH CHAMBER ==== */
    firstMoveToHighChamber(0.6);

    /* ==== PUSH THE FIRST SAMPLE TO THE OBSERVATION ZONE ==== */
    firstSampleToObservationZone();


    /* ==== PLACE THE SECOND SPECIMEN ON THE HIGH CHAMBER ==== */
    firstSpecimentToHighChamber();

    secondSpecimentToHighChamber();

    /* ==== PARK IN THE OBSERVATION ZONE ==== */
    parkInObservationZone();
}
    void firstMoveToHighChamber(double power) {
        // Move forward to the bar
        SubSystem.DriveAll(power, -1400, 0, 0, 0,0 ,1,2000);
        // Clip the specimen onto the bar
        SubSystem.DriveAll(0, 0, 0, 0,0 ,0,1,1000);
    }

    void firstSampleToObservationZone() {
        // Strafe diagonal towards the wall
        SubSystem.StrafeAll(Utility.MIN_MOTOR_POW, 0.1, 1750, 0, 0,0 ,0,1,0);
        // Move forward to get behind the first sample on floor
        SubSystem.DriveAll(Utility.MIN_MOTOR_POW, -1550, 0, 0, 0,0 ,0,0);
        // Strafe towards the wall to get behind first sample on the floor
        SubSystem.StrafeAll(Utility.MIN_MOTOR_POW, 550, 0, 0,0 ,0,1,0);
        // Drive backwards to push first sample to observation zone
        SubSystem.DriveAll(1, 1800, 0, 0, 0,0 ,0,0);
    }

    void secondSampleToObservationZone() {
        // Move forward to get behind the second sample on floor
        SubSystem.DriveAll(0.5, -1900, 0, 0, 0,0 ,0,0);
        // Strafe towards the wall to get behind second sample on the floor
        SubSystem.StrafeAll(0.5, 450, 0, 0,0 ,0,1,0);
        // Drive backwards to push first sample to observation zone
        SubSystem.DriveAll(0.5, 1800, 0, 0, 0,0 ,0,0);
    }
    void firstSpecimentToHighChamber() {
        // Move forward out of the observation zone
        SubSystem.DriveAll(1, -450, 0, 0, 0,0 ,0,0);
        // Turn to face the observation zone
        SubSystem.TurnAll(1, -150, 0, 0, 0, 0, 0, 0);
        // Strafe into the wall to align with the specimen
        SubSystem.StrafeAll(1, -1080, 0, 0,0 ,0,0,0);
        // Move forward into the observation zone and clip onto the specimen
        SubSystem.DriveAll(1, -1050, 0, 0, 0,0 ,0,0);
        // Pick up the specimen hanging on the wall
        SubSystem.DriveAll(0, 0, 0, 0,0 ,0,0.4,400);
        // Strafe diagonal towards the submersible
        SubSystem.StrafeAll(Utility.MIN_MOTOR_POW, 0.1, 2200, 0, 0,0 ,0,0,0);
        // Turn to face the submersible
        SubSystem.TurnAll(Utility.MIN_MOTOR_POW, 175, 0, 0, 0, 0, 0, 0);
        // Strafe towards the submersible to align the specimen with the high chamber
        SubSystem.StrafeAll(Utility.MIN_MOTOR_POW, -980, 0, 0,0 ,0,0.8,2000);
        // Drive forward towards the submersible to place the specimen above the high chamber
        SubSystem.DriveAll(Utility.MIN_MOTOR_POW, -600, 0, 0, 0,0 ,0.8,2000);
        // Lower the speciment onto the high chamber
        SubSystem.DriveAll(0, 0, 0, 0,0 ,0,1,1000);
    }

    void secondSpecimentToHighChamber() {
        SubSystem.StrafeAll(1, 0.5, 1750, 0, 0,0 ,0,1,0);
        // Turn to face the observation zone
        SubSystem.TurnAll(1, -150, 0, 0, 0, 0, 0, 0);
        // Strafe into the wall to align with the specimen
        SubSystem.StrafeAll(1, -1225, 0, 0,0 ,0,0,0);
        // Move forward into the observation zone and clip onto the specimen
        SubSystem.DriveAll(1, -1070, 0, 0, 0,0 ,0,0);
        // Pick up the specimen hanging on the wall
        SubSystem.DriveAll(0, 0, 0, 0,0 ,0,0.4,400);
        // Strafe diagonal towards the submersible
        SubSystem.StrafeAll(Utility.MIN_MOTOR_POW, 0.1, 2200, 0, 0,0 ,0,0,0);
        // Turn to face the submersible
        SubSystem.TurnAll(Utility.MIN_MOTOR_POW, 175, 0, 0, 0, 0, 0, 0);
        // Strafe towards the submersible to align the specimen with the high chamber
        SubSystem.StrafeAll(Utility.MIN_MOTOR_POW, -880, 0, 0,0 ,0,0.8,2000);
        // Drive forward towards the submersible to place the specimen above the high chamber
        SubSystem.DriveAll(Utility.MIN_MOTOR_POW, -600, 0, 0, 0,0 ,0.8,2000);
        // Lower the speciment onto the high chamber
        SubSystem.DriveAll(0, 0, 0, 0,0 ,0,1,1000);
    }

    void parkInObservationZone() {
        // Strafe backwards diagonal towards the observation zone
        SubSystem.StrafeAll(1, 0.3, 2200, 0, 0,0 ,0,1,0);
        // Back into the observation zone
        SubSystem.DriveAll(1, 300, 0, 0, 0,0 ,0,0);
    }
}
