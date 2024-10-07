package org.firstinspires.ftc.teamcode.core.player;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.core.Claw;

@TeleOp
public class ClawOpMode extends LinearOpMode {
    public static final double _ogPosition = Claw._ogPosition;
    double _clawPosition = _ogPosition;
    boolean _isMax = false;
    boolean _isMin = false;
    final double _clawSpeed = 0.01;
    @Override
    public void runOpMode() throws InterruptedException {
        while (opModeIsActive()) {

            if (_clawPosition >= Claw._clawMaxRange){
                _isMax = true;
            } else{
                _isMax = false;
            }

            if(_clawPosition >= Claw._clawMinimumRange){
                _isMin = true;
            } else{
                _isMin = false;
            }

            if (gamepad2.a && _isMax == false && _isMin == false){
                _clawPosition += _clawSpeed;
            } else if(!gamepad2.a || _isMax == true || _isMin == true){
                _clawPosition = _ogPosition;
            }
        }
    }
}
