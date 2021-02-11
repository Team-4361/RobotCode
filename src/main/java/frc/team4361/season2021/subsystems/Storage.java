package frc.team4361.season2021.subsystems;

import org.roxbotix.elibs2.motor.*;
import org.roxbotix.elibs2.robot.components.MotorComponent;
import org.roxbotix.elibs2.subsystem.Subsystem;
import org.roxbotix.elibs2.subsystem.Subsystems;
import frc.team4361.config.Common;
import frc.team4361.config.season2021.M2021;

public class Storage implements Subsystem, MotorComponent, MotorCore {
    static {
        Subsystems.registerSubsystem(
                Common.STORAGE_MOTOR,
                new Storage()
        );
    }

    private static final MotorConfig motorConfig = new MotorConfig(
            (int) M2021.get(Common.STORAGE_MOTOR),
            1.0,
            1.0,
            -1.0,
            1.0,
            -1.0,
            1.0,
            0.0,
            0.0,
            Direction.FORWARDS,
            Type.TALON,
            -1,
            -1,
            0,
            0
    );

    private Motor motor;

    public Storage() {

    }

    @Override
    public void init() {
        motor = new Motor(motorConfig);

        motor.init();
    }

    @Override
    public String getName() {
        return null;
    }

    private void _set(double power) {
        motor.set(power);
    }

    private double _get() {
        return motor.get();
    }

    @Override
    public void setPower(double power) {
        _set(power);
    }

    @Override
    public double getPower() {
        return _get();
    }

    @Override
    public void set(double power) {
        _set(power);
    }

    @Override
    public double get() {
        return _get();
    }
}
