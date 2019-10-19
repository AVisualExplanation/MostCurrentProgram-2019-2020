package org.firstinspires.ftc.teamcode.MostCurrentProgram;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@TeleOp(name="lowercaseFrankTeleOp", group="TeleOp")
public class lowercaseFrankTeleOp extends OpMode {
    boolean Frankmode = false;
    private HardwarePushbot lowercaseFrank = new HardwarePushbot();

    @Override
    public void init() {
        lowercaseFrank.init(hardwareMap);
        lowercaseFrank.leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lowercaseFrank.rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void loop() {
        updateDrive();
    }

    private synchronized void Updatespin() {
        if (gamepad1.x==true) {
            lowercaseFrank.rightDrive.setPower(.75);
            lowercaseFrank.leftDrive.setPower(-.75);
            try {
                wait(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (gamepad1.y==true){
            lowercaseFrank.rightDrive.setPower(1);
            lowercaseFrank.leftDrive.setPower(-1);
            try {
                wait(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (gamepad1.a==true) {
            lowercaseFrank.rightDrive.setPower(.5);
            lowercaseFrank.leftDrive.setPower(-.5);
            try {
                wait(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (gamepad1.b==true) {
            lowercaseFrank.rightDrive.setPower(.25);
            lowercaseFrank.leftDrive.setPower(-.25);
            try {
                wait(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        }


    public synchronized void updateDrive() {
        if (gamepad1.right_stick_button==true){
              Frankmode=true;
        }
        else if (gamepad1.left_stick_button==true){
            Frankmode=false;
        }
        if (Frankmode==true){
            lowercaseFrank.leftDrive.setPower((-(gamepad1.right_stick_y+gamepad1.left_stick_y)*.9)/2);
            lowercaseFrank.rightDrive.setPower((-(gamepad1.right_stick_y+gamepad1.left_stick_y)*.9)/2);
            if (gamepad1.dpad_left==true){
                lowercaseFrank.rightDrive.setPower(.8);
                lowercaseFrank.leftDrive.setPower(-.8);
            }
            if (gamepad1.dpad_right==true){
                lowercaseFrank.rightDrive.setPower(-.8);
                lowercaseFrank.leftDrive.setPower(.8);
            }
        }
        if (Frankmode==false) {
            lowercaseFrank.rightDrive.setPower((-gamepad1.right_stick_y) * .9);
            lowercaseFrank.leftDrive.setPower((-gamepad1.left_stick_y) * .9);
        }
    }

}

/* DO NOT DELETE REFERENCE HERE!!!!!
    private synchronized void updateServo() {
        double NewPosition;
        double CurrentPosition = lowercaseFrank.mineralCollection.getPosition();
        //if (CurrentPosition == 1.0 || CurrentPosition == 0.0){
       //     return;
       // }
        if (gamepad2.right_stick_button){
            NewPosition = CurrentPosition + .00125;
        } else if (gamepad2.left_stick_button) {
            NewPosition = CurrentPosition -.00125 ;
        }
        else {
            NewPosition = CurrentPosition;
        }
        lowercaseFrank.mineralCollection.setPosition(Range.clip(NewPosition, 0, 1));

    }*/
/* hello, this is a test change */ 