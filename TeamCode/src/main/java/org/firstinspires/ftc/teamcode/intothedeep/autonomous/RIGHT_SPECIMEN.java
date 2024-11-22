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
            SpecimentGetThree.run();
        } else{
            SpecimenGetTwo.run();
        }


    }
}
