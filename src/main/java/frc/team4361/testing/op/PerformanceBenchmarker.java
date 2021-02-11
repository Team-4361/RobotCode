package frc.team4361.testing.op;

import org.roxbotix.elibs2.op.template.ThreadEnabledTemplate;
import org.roxbotix.elibs3.main.RobotMode;

import java.util.ArrayList;

@RobotMode(name = "Benchmark")
public class PerformanceBenchmarker extends ThreadEnabledTemplate {
    private static double num = 0.0;

    private ArrayList<Runnable> getRunnables(long in) {
        return new ArrayList<>() {{
            add(
                    () -> {
                        double a = Math.cos(Math.PI);
                        a *= in;
                        a *= Math.atan2(a, Math.asin(a * Math.PI));
                        a *= in;
                        a = Math.pow(a, Math.sin(a) * 2 * 1.75);
                        a *= in;
                        a /= (a * a) / (a / (a * Math.PI));
                        a += 8;
                        a *= in;
                        num = a;
                    }
            );
            add(
                    () -> {
                        double a = Math.cos(Math.PI);
                        a *= in;
                        a *= Math.atan2(a, Math.asin(a * Math.PI));
                        a = Math.pow(a, Math.sin(a) * 215 * 1.7414235);
                        a *= in;
                        a /= (a * a) / (a / (a * Math.PI));
                        a *= in;
                        a += 8;
                        num = a;
                    }
            );
            add(
                    () -> {
                        double a = Math.cos(Math.PI);
                        a *= in * in * in;
                        a *= Math.atan2(a, Math.asin(a * Math.PI));
                        a = Math.pow(a, Math.sin(a) * 1.319329541785 * 1.75);
                        a /= (a * a) / (a / (a * Math.PI));
                        a *= in;
                        a += 8;
                        num = a;
                    }
            );
            add(
                    () -> {
                        double a = Math.cos(Math.PI);
                        a *= in - a * in / a * in * in;
                        a *= Math.atan2(a, Math.asin(a * Math.PI));
                        a = Math.pow(a, Math.sin(a) * 2.5185185 * 1.75);
                        a /= (a * a) / (a / (a * Math.PI));
                        a += 8;
                        num = a;
                    }
            );
            add(
                    () -> {
                        double a = Math.cos(Math.PI);
                        a *= in;
                        a *= Math.atan2(a, Math.asin(a * Math.PI));
                        a *= in;
                        a = Math.pow(a, Math.sin(a) * 1.91923 * 1.75);
                        a *= in;
                        a /= (a * a) / (a / (a * Math.PI));
                        a *= in;
                        a += 8;
                        num = a;
                    }
            );
        }};
    }

    public PerformanceBenchmarker() {
        ArrayList<Runnable> allRunnables1 = getRunnables(System.currentTimeMillis());

        for (Runnable r : allRunnables1) {
            fRobotInit.add(r);
            fRobotPeriodic.add(r);
            fRobotRegular.add(r);

            fDisabledInit.add(r, r);
            fDisabledPeriodic.add(r, r);
            fDisabledRegular.add(r, r, r);

            fRobotInit.add(r);
            fRobotPeriodic.add(r);
            fRobotRegular.add(r);

            fDisabledInit.add(r, r);
            fDisabledPeriodic.add(r, r);
            fDisabledRegular.add(r, r, r);

            fRobotInit.add(r);
            fRobotPeriodic.add(r);
            fRobotRegular.add(r);

            fDisabledInit.add(r, r);
            fDisabledPeriodic.add(r, r);
            fDisabledRegular.add(r, r, r);

            fRobotInit.add(r);
            fRobotPeriodic.add(r);
            fRobotRegular.add(r);

            fDisabledInit.add(r, r);
            fDisabledPeriodic.add(r, r);
            fDisabledRegular.add(r, r, r);
        }

        ArrayList<Runnable> allRunnables2 = getRunnables(System.currentTimeMillis());

        for (Runnable r : allRunnables2) {
            fRobotInit.add(r);
            fRobotPeriodic.add(r);
            fRobotRegular.add(r);

            fDisabledInit.add(r, r);
            fDisabledPeriodic.add(r, r);
            fDisabledRegular.add(r, r, r);

            fRobotInit.add(r);
            fRobotPeriodic.add(r);
            fRobotRegular.add(r);

            fDisabledInit.add(r, r);
            fDisabledPeriodic.add(r, r);
            fDisabledRegular.add(r, r, r);

            fRobotInit.add(r);
            fRobotPeriodic.add(r);
            fRobotRegular.add(r);

            fDisabledInit.add(r, r);
            fDisabledPeriodic.add(r, r);
            fDisabledRegular.add(r, r, r);

            fRobotInit.add(r);
            fRobotPeriodic.add(r);
            fRobotRegular.add(r);

            fDisabledInit.add(r, r);
            fDisabledPeriodic.add(r, r);
            fDisabledRegular.add(r, r, r);
        }

        fRobotRegular.add(
                () -> {
                    num = System.currentTimeMillis() * Math.cos(num) *
                            Math.sin(Math.atan2(Math.cos(num), Math.sin(num))) *
                            Math.pow(System.currentTimeMillis(), Math.PI);
                }
        );
    }
}
