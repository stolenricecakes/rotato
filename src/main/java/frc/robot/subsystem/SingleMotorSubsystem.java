package frc.robot.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxAnalogSensor;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAnalogSensor.Mode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SingleMotorSubsystem extends SubsystemBase {

    private CANSparkMax motor;
    private SparkMaxPIDController pidController;
    private SparkMaxAnalogSensor encoder;

    public SingleMotorSubsystem() {
        this.motor = new CANSparkMax(12, MotorType.kBrushless);
        this.pidController = motor.getPIDController();
        this.encoder = motor.getAnalog(Mode.kAbsolute);

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

        //encoder.setPosition(0);
        encoder.setPositionConversionFactor(Math.PI * 2.0 / 3.3);
        pidController.setFeedbackDevice(encoder);

    }

    public void rotateTo(double radians) {
        System.out.println("rotating to: " + radians);
        pidController.setReference(radians, ControlType.kPosition);
    };



    @Override
    public void periodic() {
        System.out.println("Encoder position: " + encoder.getPosition());
    }

    public void stop() {
        motor.set(0);
    }
    
    
}
