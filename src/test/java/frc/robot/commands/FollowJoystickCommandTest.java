package frc.robot.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystem.SingleMotorSubsystem;

@ExtendWith(MockitoExtension.class)
public class FollowJoystickCommandTest {
    
    @Mock
    private SingleMotorSubsystem singleMotorSubsystem;

    @Mock
    private CommandXboxController controller;

    private FollowJoystickCommand followJoystickCommand;

    @BeforeEach
    public void init() {
        followJoystickCommand = new FollowJoystickCommand(singleMotorSubsystem, controller);
    }

    @Test
    public void testDetermineAngleUp() {
        double result = followJoystickCommand.determineAngle(0, -1);
        assertEquals(1.57, result, .01);
    }
    
    @Test
    public void testDetermineAngleLeft() {
        double result = followJoystickCommand.determineAngle(-1, 0);
        assertEquals(3.14, result, .01);
    }

    @Test
    public void testDetermineAngleDown() {
        double result = followJoystickCommand.determineAngle(0, 1);
        assertEquals(4.71, result, .01);
    }
    
    @Test
    public void testDetermineAngleRight() {
        double result = followJoystickCommand.determineAngle(1, 0);
        assertEquals(0, result, .01);
    }
}
