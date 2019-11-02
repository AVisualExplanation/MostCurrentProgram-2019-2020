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

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.Locale;

@TeleOp(name="lowercaseFrankTeleOp", group="TeleOp")
public class lowercaseFrankTeleOp extends OpMode {
    boolean Frankmode = false;
    private HardwarePushbot lowercaseFrank = new HardwarePushbot();
    Orientation angles;


    @Override
    public void init() {
        lowercaseFrank.init(hardwareMap);
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        lowercaseFrank.imu.initialize(parameters);
        lowercaseFrank.leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lowercaseFrank.rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        composeTelemetry();
    }

    @Override
    public void loop() {
        updateDrive();
        telemetry.update();
    }


    private void updateDrive() {
            lowercaseFrank.rightDrive.setPower(-(gamepad1.right_stick_y) * .9);
            lowercaseFrank.leftDrive.setPower(-(gamepad1.left_stick_y) * .9);
    }

    void composeTelemetry() {

        // At the beginning of each telemetry update, grab a bunch of data
        // from the IMU that we will then display in separate lines.
        telemetry.addAction(new Runnable() { @Override public void run()
        {
            // Acquiring the angles is relatively expensive; we don't want
            // to do that in each of the three items that need that info, as that's
            // three times the necessary expense.
            angles   = lowercaseFrank.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        }
        });

        telemetry.addLine()
                .addData("Z angle", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.firstAngle);
                    }
                })
                .addData("Y angle", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.secondAngle);
                    }
                })
                .addData("X angle", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.thirdAngle);
                    }
                });

    }

    String formatAngle( double angle) {
        return formatDegrees(angle);
    }

    String formatDegrees(double degrees){
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
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

/* private synchronized void updateSpin() {
        if (gamepad1.x==true) {
            lowercaseFrank.rightDrive.setPower(.75);
            lowercaseFrank.leftDrive.setPower(-.75);
            try {
                wait(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (gamepad1.y==true){
            lowercaseFrank.rightDrive.setPower(1);
            lowercaseFrank.leftDrive.setPower(-1);
            try {
                wait(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (gamepad1.a==true) {
            lowercaseFrank.rightDrive.setPower(.5);
            lowercaseFrank.leftDrive.setPower(-.5);
            try {
                wait(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (gamepad1.b==true) {
            lowercaseFrank.rightDrive.setPower(.25);
            lowercaseFrank.leftDrive.setPower(-.25);
            try {
                wait(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
*/