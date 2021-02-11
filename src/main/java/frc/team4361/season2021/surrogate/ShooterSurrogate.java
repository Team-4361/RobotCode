package frc.team4361.season2021.surrogate;

import org.roxbotix.elibs2.robot.components.Encoder;
import org.roxbotix.elibs2.robot.components.TlMotor;
import frc.team4361.season2021.legacy.CoreShooter;

public class ShooterSurrogate {
    private final TlMotor flywheel;
    private final TlMotor actuator;
    private final Encoder flywheelEncoder;
    private final Encoder actuatorEncoder;
    private final double flywheelTpr;
    private final double actuatorTpr;
    private final double flywheelDiameter;
    private final CoreShooter coreShooter;

    public ShooterSurrogate(TlMotor flywheel,
                            TlMotor actuator,
                            Encoder flywheelEncoder,
                            Encoder actuatorEncoder,
                            double flywheelTpr,
                            double actuatorTpr,
                            double flywheelDiameter) {
        this.flywheel = flywheel;
        this.actuator = actuator;
        this.flywheelEncoder = flywheelEncoder;
        this.actuatorEncoder = actuatorEncoder;
        this.flywheelTpr = flywheelTpr;
        this.actuatorTpr = actuatorTpr;
        this.flywheelDiameter = flywheelDiameter;

        coreShooter = new CoreShooter(
                flywheel.getTalon(),
                actuator.getTalon(),
                flywheelEncoder.getEncoder(),
                actuatorEncoder.getEncoder(),
                flywheelTpr,
                actuatorTpr,
                flywheelDiameter
        );
    }

    public TlMotor getFlywheel() {
        return flywheel;
    }

    public TlMotor getActuator() {
        return actuator;
    }

    public Encoder getFlywheelEncoder() {
        return flywheelEncoder;
    }

    public Encoder getActuatorEncoder() {
        return actuatorEncoder;
    }

    public double getFlywheelTpr() {
        return flywheelTpr;
    }

    public double getActuatorTpr() {
        return actuatorTpr;
    }

    public double getFlywheelDiameter() {
        return flywheelDiameter;
    }

    public CoreShooter getCoreShooter() {
        return coreShooter;
    }
}
