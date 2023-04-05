// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.led.*;
import com.ctre.phoenix.led.CANdle.LEDStripType;
import com.ctre.phoenix.led.CANdle.VBatOutputMode;
import com.ctre.phoenix.led.ColorFlowAnimation.Direction;
import com.ctre.phoenix.led.LarsonAnimation.BounceMode;
import com.ctre.phoenix.led.TwinkleAnimation.TwinklePercent;
import com.ctre.phoenix.led.TwinkleOffAnimation.TwinkleOffPercent;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CANdleSubsystem extends SubsystemBase {
  CANdle candle = new CANdle(40, Constants.CANBUS);
  Animation purpleFade = new LarsonAnimation(157, 3, 252, 20, .4, 20, LarsonAnimation.BounceMode.Front, 3);

  private Animation m_toAnimate = null;
  private final int LedCount = 107;
  private Colors currentColor; 
  public enum Colors {
    Red,
    Blue,
    Purple,
    Yellow,
    Green
  } 

  public enum AnimationTypes {
    ColorFlow,
    Larson,
    SingleFade,
    Twinkle,
    TwinkleOff,
    SetAll
  }

private AnimationTypes m_currentAnimation;
  /** Creates a new CANdleSubsystem. */
  public CANdleSubsystem() {
    CANdleConfiguration configAll = new CANdleConfiguration();
    configAll.statusLedOffWhenActive = true;
    configAll.disableWhenLOS = false;
    configAll.stripType = LEDStripType.GRB;
    configAll.brightnessScalar = 0.1;
    configAll.vBatOutputMode = VBatOutputMode.Modulated;
    
    candle.configAllSettings(configAll, 100);
  }

  @Override
  public void periodic() {
  // candle.setLEDs(0,255,0);
  
// candle.animate(new FireAnimation(.4,.2,LedCount,8,.5));
    // if (m_toAnimate == null) {
      
    //   setLEDColor(currentColor);
    // } else {
    //   candle.animate(m_toAnimate);
    // }
    candle.animate(new TwinkleOffAnimation(157, 3, 252, 0, 0.2, LedCount, TwinkleOffPercent.Percent100));
    // candle.animate(purpleFade);
  }

  private void setLEDColor(Colors color) {
    switch (color) {
      case Red: 
        candle.setLEDs(255, 0, 0);
        break;
      case Blue: 
        candle.setLEDs(0, 0, 255);
        break;
      case Green: 
        candle.setLEDs(0, 255, 0);
        break;
      case Purple: 
        candle.setLEDs(157, 3, 252);
        break;
      case Yellow: 
        candle.setLEDs(255, 255, 0);
        break;
      default:
        candle.setLEDs(0, 0, 0);
    }
  }

  public void setSolidColor(Colors color) {
    changeAnimation(AnimationTypes.SetAll);
    currentColor = color;
  }

  public void setLEDToTeamColor() {
    if (DriverStation.getAlliance() == Alliance.Blue) {
      setSolidColor(Colors.Blue);
    } else {
      setSolidColor(Colors.Red);
    } 
  }

  public void flashGreen() {
    m_toAnimate = new StrobeAnimation(0, 255, 0, 0, 98.0 / 256, LedCount);
  }

  public void flashPurple() {
    m_toAnimate = new StrobeAnimation(157, 3, 252, 0, 98.0 / 256, LedCount);
  }

  public void flashYellow() {
    m_toAnimate = new StrobeAnimation(255, 255, 0, 0, 98.0 / 256, LedCount);
  }

  public void changeAnimation(AnimationTypes toChange) {
    m_currentAnimation = toChange;
    
    switch(toChange)
    {
        case ColorFlow:
            m_toAnimate = new ColorFlowAnimation(157, 3, 252, 0, 0.7, LedCount, Direction.Forward);
            break;
        case Larson:
            m_toAnimate = new LarsonAnimation(157, 3, 252, 0, 1, LedCount, BounceMode.Front, 3);
            break;
        case SingleFade:
            m_toAnimate = new SingleFadeAnimation(157, 3, 252, 0, 0.5, LedCount);
            break;
        case Twinkle:
            m_toAnimate = new TwinkleAnimation(157, 3, 252, 0, 0.4, LedCount, TwinklePercent.Percent6);
            break;
        case TwinkleOff:
            m_toAnimate = new TwinkleOffAnimation(157, 3, 252, 0, 0.8, LedCount, TwinkleOffPercent.Percent100);
            break;
        case SetAll:
            m_toAnimate = null;
            break;
    }
    System.out.println("Changed to " + m_currentAnimation.toString());
}
}
