package frc.robot.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.SparkRelativeEncoder;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SingleMotorSubsystem extends SubsystemBase {

    private CANSparkMax motor;
    private SparkPIDController pidController;
    private SparkRelativeEncoder encoder;
    private GenericEntry currentRotation;
    private GenericEntry target;

    // this seeemd to kinda work a little, but was taking more than one rotation of the motor shaft to achieve the setpoint.

    public SingleMotorSubsystem() {
        this.motor = new CANSparkMax(20, MotorType.kBrushless);
        this.pidController = motor.getPIDController();
        this.encoder = (SparkRelativeEncoder) motor.getEncoder();

        setup();
    }

    private void setup() {
        pidController.setP(.1);
        pidController.setI(0);
        pidController.setD(.1);
        pidController.setOutputRange(-.25, .25);

        pidController.setPositionPIDWrappingEnabled(true);
        pidController.setPositionPIDWrappingMinInput(0);
        pidController.setPositionPIDWrappingMaxInput(2 * Math.PI);

        encoder.setPositionConversionFactor(( Math.PI * 2.0 ) / 42.0);
        pidController.setFeedbackDevice(encoder);

        currentRotation = Shuffleboard.getTab("rotato").add("current Rot", 0.0).getEntry();
        target = Shuffleboard.getTab("rotato").add("target", 0.0).getEntry();
    }

    public void rotateTo(double radians) {
        System.out.println("rotating to: " + radians);
        pidController.setReference(radians, ControlType.kPosition);
        target.setValue(radians);
    };



    @Override
    public void periodic() {
      //  System.out.println("Encoder position: " + encoder.getPosition());
        currentRotation.setValue(encoder.getPosition());
    }

    public void stop() {
        motor.set(0);
    }
    
    
}
