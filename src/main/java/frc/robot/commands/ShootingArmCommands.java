  package frc.robot.commands;

  import edu.wpi.first.wpilibj2.command.Command;
  import edu.wpi.first.wpilibj2.command.Commands;
  import frc.robot.subsystems.Arm;
  import frc.robot.subsystems.Shooter;
  import frc.robot.subsystems.Arm.ArmPosition;
  import frc.robot.subsystems.Shooter.ShootSpeed;

  public class ShootingArmCommands {

      private Shooter shooter;

      private Arm arm;

      public ShootingArmCommands(Shooter shooter, Arm intake) {
        this.shooter = shooter;
        this.shooter = shooter;
      }

      public Command Intake() {
          return Commands.sequence(arm.moveArmToPosition(ArmPosition.Intake), shooter.intake());
      }

      public Command Rest() {
          return Commands.parallel(arm.moveArmToPosition(ArmPosition.Rest), shooter.shoot(ShootSpeed.Stop));
      }

      public Command ShootHigh() {
        return Commands.sequence(arm.moveArmToPosition(ArmPosition.High), shooter.shoot(ShootSpeed.High));
      } 

      public Command ShootMid() {
        return Commands.sequence(arm.moveArmToPosition(ArmPosition.Mid), shooter.shoot(ShootSpeed.Mid));
      }

      public Command ShootLow() {
        return Commands.sequence(arm.moveArmToPosition(ArmPosition.Low), shooter.shoot(ShootSpeed.Low));
      }

      public Command Cannon() {
        return Commands.sequence(arm.moveArmToPosition(ArmPosition.Cannon), shooter.shoot(ShootSpeed.Cannon));
      }

  }