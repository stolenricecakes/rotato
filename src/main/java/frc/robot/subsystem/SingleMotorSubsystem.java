package frc.robot.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SingleMotorSubsystem extends SubsystemBase {

    private CANSparkMax motor;
    private SparkMaxPIDController pidController;
    private RelativeEncoder encoder;

    public SingleMotorSubsystem() {
        this.motor = new CANSparkMax(14, MotorType.kBrushless);
        this.pidController = motor.getPIDController();
        this.encoder = motor.getEncoder();

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
        encoder.setPositionConversionFactor(Math.PI * 2.0 / 4096.0);
    }

    public void rotateTo(double radians) {
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
