package org.firstinspires.ftc.teamcode.MostCurrentProgram;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
/* Autonomous progam made by Tristan. This file is starting with @Autonomous simply
 stating this file as autonomous instead of teleop */



@Autonomous(name = "FrankOtterbot", group="Autonomous")
public class FrankOtterbot extends LinearOpMode {
    private HardwarePushbot lowercaseFrank = new HardwarePushbot();
    double DRIVE_POWER = .7;
     /* Fetching/stating/getting the motors.
       Right motor is stated as "right_drive", and left motor is "left_drive"*/
     public void runOpMode() throws InterruptedException {
         lowercaseFrank.init(hardwareMap);
         waitForStart();

     }

    private double Rotatetime(double degrees, double power){
        return((degrees)/((1.3646*(Math.pow(10,7))*Math.pow(power,1.47891))-(1.3645*(Math.pow(10,7))*(Math.pow(power,1.47893)))));
    }
}
