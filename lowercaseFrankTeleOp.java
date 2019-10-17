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

@TeleOp(name="lowercaseFrankTeleOp", group="TeleOP")
class lowercaseFrankTeleOp extends OpMode {
    boolean Tankmode = false;
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
    public synchronized void updateDrive() {
        if (gamepad1.left_stick_button == true) {
            Tankmode = false;
        } else if (gamepad1.right_stick_button == true) {
            Tankmode = true;
        }
    }
        lowercaseFrank.rightDrive.setPower((-gamepad1.right_stick_y) * .7);
        lowercaseFrank.leftDrive.setPower((-gamepad1.left_stick_y) * .7);
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
