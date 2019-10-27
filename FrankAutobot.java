package org.firstinspires.ftc.teamcode.MostCurrentProgram;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
/* Autonomous progam made by Tristan. This file is starting with @Autonomous simply
 stating this file as autonomous instead of teleop */


@Autonomous(name = "FrankAutoBot")


public class FrankAutoBot extends OpMode {
     /* Fetching/stating/getting the motors.
       Right motor is stated as "right_drive", and left motor is "left_drive"*/



    public DcMotor getLeft_drive() {
        return left_drive; }

    public DcMotor getRight_drive() {
        return right_drive;
    }

}

     DcMotor left_drive = null;
     DcMotor right_drive = null;

    @Override public void main() throws InterruptedException {
        /* initialize motors */
        left_drive = hardwareMap.dcMotor.get("left_drive");
        right_drive = hardwareMap.dcMotor.get("right_drive");

        left_drive.setDirection(DcMotorSimple.Direction.REVERSE);

        // wait for game to start.

        wait();

        // Robot moving
        // The First number stands for power while the second number stands for time

        DriveForwardTime( DRIVE_POWER, 4000);
        TurnLeftTime(DRIVE_POWER, 500);
        DriveForwardTime(DRIVE_POWER, 4000);
        TurnRightTime(DRIVE_POWER, 500);
        DriveForwardTime(DRIVE_POWER,4000);
        StopDriving();




    }

    double DRIVE_POWER = 1.0;

    public void DriveForward(double power) {
        left_drive.setPower(power);
        right_drive.setPower(power);
    }

    public void DriveForwardTime(double power, long time) throws InterruptedException {
        DriveForward(power);
        Thread.sleep(time);
    }

    public void StopDriving() {
        DriveForward(0);
    }
    public void TurnLeft(double power) {
        left_drive.setPower(-power);
        right_drive.setPower(power);
    }

    public void TurnLeftTime(double power, long time) throws InterruptedException {
        TurnLeft(power);
        Thread.sleep(time);
    }

    public void TurnRight(double power) {
        TurnLeft(-power);
    }


    public void TurnRightTime(double power, long time) throws InterruptedException {
        TurnRight(power);
        Thread.sleep(time);
    }







}
