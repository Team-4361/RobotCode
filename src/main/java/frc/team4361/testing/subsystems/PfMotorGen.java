package frc.team4361.testing.subsystems;

import me.wobblyyyy.pathfinder.frc.EncodedMotor;
import me.wobblyyyy.pathfinder.frc.PfMotor;
import org.roxbotix.elibs2.motor.Direction;
import org.roxbotix.elibs2.motor.MotorConfig;
import org.roxbotix.elibs2.motor.Type;

public class PfMotorGen {
    public static MotorConfig getConfig(int id,
                                        int ea,
                                        int eb) {
        return new MotorConfig(
                id,
                1.0,
                1.0,
                -1.0,
                1.0,
                -1.0,
                1.0,
                0.0,
                0.0,
                Direction.FORWARDS,
                Type.SPARK_BRUSHLESS,
                ea,
                eb,
                0,
                1024
        );
    }

    public static EncodedMotor getEncodedMotor(int id,
                                               int a,
                                               int b,
                                               boolean inverted) {
        return new EncodedMotor(
                getConfig(id, a, b),
                a,
                b,
                inverted,
                1024
        );
    }

    public static PfMotor getMotor(int id,
                                   int ea,
                                   int eb) {
        return new PfMotor(new MotorConfig(
                id,
                1.0,
                1.0,
                -1.0,
                1.0,
                -1.0,
                1.0,
                0.0,
                0.0,
                Direction.FORWARDS,
                Type.SPARK_BRUSHLESS,
                ea,
                eb,
                0,
                1024
        ));
    }
}
