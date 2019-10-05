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

@TeleOp(name="Oppreborn TeleOp", group="TeleOP")
public class OpprebornTeleOp extends OpMode {
    private HardwarePushbot oppreborn = new HardwarePushbot();
    private final double DROP_SPEED = 0.6;                                                          //This was created as a precaution to ensure that the robot didn't drop down too quickly

    @Override
    public void init() {
        oppreborn.init(hardwareMap);
        oppreborn.leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        oppreborn.rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        oppreborn.liftnLower.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void loop() {
        updateDrive();
        updateHanger();
        updateStrafe();
        updateServo();
    }

    private synchronized void updateDrive() {
        oppreborn.leftDrive.setPower((-gamepad1.right_stick_y) * .7);
        oppreborn.rightDrive.setPower((-gamepad1.left_stick_y) * .7);
    }

    private synchronized void updateHanger() {
        if (gamepad2.left_trigger > 0 || gamepad2.right_trigger > 0) {
            oppreborn.liftnLower.setPower((-((gamepad2.left_trigger / 2) + (gamepad2.right_trigger / 2))) * .9);
        } else if (gamepad2.left_bumper && gamepad2.right_bumper) {
            oppreborn.liftnLower.setPower(DROP_SPEED);
        } else {
            oppreborn.liftnLower.setPower(0);
        }
    }

    private synchronized void updateStrafe() {
        oppreborn.midDrive.setPower(((gamepad1.right_stick_x / 2) + (gamepad1.left_stick_x / 2)) * .7);
    }


    private synchronized void updateServo() {
        double NewPosition;
        double CurrentPosition = oppreborn.mineralCollection.getPosition();
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
        oppreborn.mineralCollection.setPosition(Range.clip(NewPosition, 0, 1));

    }
}