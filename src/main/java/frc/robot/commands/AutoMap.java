package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import swervelib.SwerveDrive;

import java.util.HashMap;

public class AutoMap {
  public final HashMap<String, Command> eventMap = new HashMap<>();
  
  public AutoMap(ShootingArmCommands shootingArmCommands, SwerveSubsystem swerveDrive) {
    eventMap.put("Intake", shootingArmCommands.Intake());
    eventMap.put("ShootHigh", shootingArmCommands.ShootHigh());
    eventMap.put("ShootLow", shootingArmCommands.ShootLow());
    eventMap.put("ShootMid", shootingArmCommands.ShootMid());
    eventMap.put("Cannon", shootingArmCommands.Cannon());
    eventMap.put("Balance", swerveDrive.balanceRobot());
    eventMap.put("MoveOntoStation", swerveDrive.moveRevOntoChargeStation());
  }

  public HashMap<String, Command> getEventMap() {
    return eventMap;
  }
}