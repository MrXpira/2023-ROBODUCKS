package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;

import java.util.HashMap;

public class AutoMap {
  public final HashMap<String, Command> eventMap = new HashMap<>();
  
  public AutoMap(ShootingArmCommands shootingArmCommands) {
    eventMap.put("ScoreLow", new PrintCommand("Passed Event 1!"));
    eventMap.put("Intake", shootingArmCommands.Intake());
    eventMap.put("ShootHigh", shootingArmCommands.ShootHigh());
    eventMap.put("ShootLow", shootingArmCommands.ShootLow());
    eventMap.put("ShootMid", shootingArmCommands.ShootMid());
  }

  public HashMap<String, Command> getEventMap() {
    return eventMap;
  }
}