package org.firstinspires.ftc.teamcode.MostCurrentProgram;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.Locale;
/* Autonomous progam made by Tristan. This file is starting with @Autonomous simply
 stating this file as autonomous instead of teleop */



@Autonomous(name = "FrankOtterbot", group="Autonomous")
public class FrankOtterbot extends LinearOpMode {
    private HardwarePushbot lowercaseFrank = new HardwarePushbot();
    double DRIVE_POWER = .7;
    double TURN_SPEED = .4;
    private final double COUNTS_PER_MOTOR_REV_WHEELS = 2240;                                               //Different motors will spin at different rates even when the same amount of power is applied. These are identified as counts. For example, Tetrix motors have 1440 counts for every single rotation/revolution.
    private final double DRIVE_GEAR_REDUCTION = 1.0;                                                // This is < 1.0 if geared UP. This has to do with how the motors are connected to the wheels. If it is a direct connection then there is no gear up. But, if there are gears in between then the wheel will likely not rotate at the same rate as the motor. This accounts for that.
    private final double WHEEL_DIAMETER_INCHES = 3.54331;                                               // For figuring circumference
    private final double COUNTS_PER_INCH_WHEELS = (COUNTS_PER_MOTOR_REV_WHEELS * DRIVE_GEAR_REDUCTION) /          //Because the motor measures its rotations in "counts", this translates those counts by answering "how many counts should the motor go in order to move the wheel by one inch".
            (WHEEL_DIAMETER_INCHES * 3.1415);
    Orientation angles;

    /* Fetching/stating/getting the motors.
      Right motor is stated as "right_drive", and left motor is "left_drive"*/
    public void runOpMode() throws InterruptedException {
        lowercaseFrank.init(hardwareMap);
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        lowercaseFrank.imu.initialize(parameters);
        lowercaseFrank.leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lowercaseFrank.rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        composeTelemetry();

        waitForStart();

    }

    private void IMUDrive(double speed, double inches, double wangle) {
        IMURotate(wangle);

    }

    private void IMURotate(double wangle) {
        telemetry.update();
        angles = lowercaseFrank.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double crangle = angles.firstAngle;
        double nrotate = (180 - Math.abs(crangle)) + (180 - Math.abs(wangle));
        double trotate = Math.abs(wangle - crangle);
        
        if (wangle < crangle) {
            if (trotate <= nrotate) {
                while ((crangle < (wangle + 1)) ^ (crangle > (wangle - 1))) {
                    lowercaseFrank.leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    lowercaseFrank.rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    lowercaseFrank.rightDrive.setPower(TURN_SPEED);
                    lowercaseFrank.leftDrive.setPower(-TURN_SPEED);
                    telemetry.update();
                    angles = lowercaseFrank.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                    crangle = angles.firstAngle;
                }
            } else if (trotate > nrotate) {
                while ((crangle < (wangle + 1)) ^ (crangle > (wangle - 1))) {
                    lowercaseFrank.leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    lowercaseFrank.rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    lowercaseFrank.rightDrive.setPower(-TURN_SPEED);
                    lowercaseFrank.leftDrive.setPower(TURN_SPEED);
                    telemetry.update();
                    angles = lowercaseFrank.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                    crangle = angles.firstAngle;
                }
            }
        }
        else if (wangle > crangle) {
            if (trotate <= nrotate) {
                while ((crangle < (wangle + 1)) ^ (crangle > (wangle - 1))) {
                    lowercaseFrank.leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    lowercaseFrank.rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    lowercaseFrank.rightDrive.setPower(-TURN_SPEED);
                    lowercaseFrank.leftDrive.setPower(TURN_SPEED);
                    telemetry.update();
                    angles = lowercaseFrank.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                    crangle = angles.firstAngle;
                }
            } else if (trotate > nrotate) {
                while ((crangle < (wangle + 1)) ^ (crangle > (wangle - 1))) {
                    lowercaseFrank.leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    lowercaseFrank.rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    lowercaseFrank.rightDrive.setPower(TURN_SPEED);
                    lowercaseFrank.leftDrive.setPower(-TURN_SPEED);
                    telemetry.update();
                    angles = lowercaseFrank.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                    crangle = angles.firstAngle;
                }
            }
        }

        lowercaseFrank.rightDrive.setPower(0);
        lowercaseFrank.leftDrive.setPower(0);
        lowercaseFrank.leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lowercaseFrank.rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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
