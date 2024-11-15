package org.firstinspires.ftc.teamcode.intothedeep.diagnostic;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@TeleOp(name="IndividualMotorTest")
public class IndividualMotorTest extends LinearOpMode {

    @Override
    public void runOpMode() {

//        Mecanum.initialize(this);
//        Drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        Drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//        Intake.initialize(this);

        DcMotor _mFrontLeft = hardwareMap.get(DcMotor.class, "fl");
        DcMotor _mFrontRight = hardwareMap.get(DcMotor.class, "fr");
        DcMotor _mRearLeft = hardwareMap.get(DcMotor.class, "rl");
        DcMotor _mRearRight = hardwareMap.get(DcMotor.class, "rr");

        _mFrontLeft.setDirection(DcMotor.Direction.FORWARD);
        _mRearLeft.setDirection(DcMotor.Direction.FORWARD);
        _mFrontRight.setDirection(DcMotor.Direction.REVERSE);
        _mRearRight.setDirection(DcMotor.Direction.REVERSE);

        _mFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        _mRearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        _mFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        _mRearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        _mFrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        _mRearLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        _mFrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        _mRearRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);



        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.b) {
                _mFrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                _mRearLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                _mFrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                _mRearRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            } else if (gamepad1.x) {
                _mFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                _mRearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                _mFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                _mRearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }

            telemetry.addData("Mode", _mFrontLeft.getMode().name());
            telemetry.update();

            _mFrontLeft.setPower(gamepad1.right_stick_y);
            _mFrontRight.setPower(gamepad1.right_stick_y);
            _mRearLeft.setPower(gamepad1.right_stick_y);
            _mRearRight.setPower(gamepad1.right_stick_y);

//            Intake.run(gamepad1.right_stick_y);
//            telemetry.addData("Claw Pos", Intake.getPosition());
//
//            if (gamepad1.a) Drive.setRearLeftPower(1);
//            else if (gamepad2.a) Drive.setRearLeftPower(-1);
//            else if (gamepad1.b) Drive.setRearRightPower(1);
//            else if (gamepad2.b) Drive.setRearRightPower(-1);
//            else if (gamepad1.x) Drive.setFrontLeftPower(1);
//            else if (gamepad2.x) Drive.setFrontLeftPower(-1);
//            else if (gamepad1.y) Drive.setFrontRightPower(1);
//            else if (gamepad2.y) Drive.setFrontRightPower(-1);
//            else Drive.stop();
//
//            telemetry.addLine("Drive Current");
//            Map<String, Double> driveCurrents = Drive.getCurrents();
//            telemetry.addData("flc", driveCurrents.get("flc"));
//            telemetry.addData("frc", driveCurrents.get("frc"));
//            telemetry.addData("rlc", driveCurrents.get("rlc"));
//            telemetry.addData("rrc", driveCurrents.get("rrc"));
//
//
//            telemetry.addLine("Drive Encoders");
//            telemetry.addData("gov", Mecanum.getActiveGovenor());
//            Map<String, Integer> drivePositions = Drive.getCurrentPositions();
//            telemetry.addData("flp", drivePositions.get("flp"));
//            telemetry.addData("frp", drivePositions.get("frp"));
//            telemetry.addData("rlp", drivePositions.get("rlp"));
//            telemetry.addData("rrp", drivePositions.get("rrp"));

            telemetry.update();
        }
    }
}
