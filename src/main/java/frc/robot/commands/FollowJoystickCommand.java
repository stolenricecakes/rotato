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
        // cos(angle) = x value.   so - solve for angle.
        double angle = Math.toRadians(Math.acos(controller.getLeftX()));

        // remember - invert access.   if y < 1 that means y = forward, top of circle. 
        if (controller.getLeftY() > 0 ) {
            angle = (Math.PI - angle) + Math.PI; // we're in the bottom half of the unit circle.
        }
        singleMotorSubsystem.rotateTo(angle);
    }

    @Override
    public void end(boolean interrupted) {
        singleMotorSubsystem.stop();
    }

}
