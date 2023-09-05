package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystem.SingleMotorSubsystem;

public class FollowJoystickCommand extends CommandBase{

    private SingleMotorSubsystem singleMotorSubsystem;
    private CommandXboxController controller;

    public FollowJoystickCommand(SingleMotorSubsystem singleMotorSubsystem, CommandXboxController controller) {
        this.singleMotorSubsystem = singleMotorSubsystem;
        this.controller = controller;

        addRequirements(singleMotorSubsystem);
    }

    @Override
    public void execute() {
        double angle = determineAngle(controller.getLeftX(), controller.getLeftY());
        singleMotorSubsystem.rotateTo(angle);
    }

    public double determineAngle(double leftX, double leftY) {
        // cos(angle) = x value.   so - solve for angle.
        double angle = Math.acos(leftX);

        // remember - invert access.   if y < 1 that means y = forward, top of circle. 
        if (leftY > 0 ) {
            angle = (Math.PI - angle) + Math.PI; // we're in the bottom half of the unit circle.
        }
        return angle;
    }

    @Override
    public void end(boolean interrupted) {
        singleMotorSubsystem.stop();
    }

}
