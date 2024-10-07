package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/* ==============================
 * Authors: Luke Delbridge, Leelu, Annika * ============================== */
public class Claw {
    //endregion

    //region Motor
    public Servo _mClaw;

    public final static double _ogPosition = 0.0;
    public final static double _clawMinimumRange = 0.20;
    public final static double _clawMaxRange = 0.7;

    public void initialize (HardwareMap hardwareMap) {
        _mClaw = hardwareMap.servo.get("claw");
        _mClaw.setPosition(_ogPosition);
    }
}
