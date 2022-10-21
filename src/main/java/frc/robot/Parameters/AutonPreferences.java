
package frc.robot.Parameters;


import com.igniterobotics.robotbase.preferences.DoublePreference;


public class AutonPreferences{

    //One ball auton

    //Angles
    public static DoublePreference autonFirstAngle = new DoublePreference("Auton Angle One", -90);
    public static DoublePreference autonSecondAngle = new DoublePreference("Auton Angle Two", 135);

    //Distances
    public static DoublePreference autonFirstDistance = new DoublePreference("Auton First Distance", 12);
    public static DoublePreference autonSecondDistance = new DoublePreference("Auton Second Distance", 20);

    //Two ball auton

    //Angles
    public static DoublePreference autonThirdAngle = new DoublePreference("Auton Third Angle", 20);
    public static DoublePreference autonFourthAngle = new DoublePreference("Auton Fourth Angle", -15);


    //Distances
    public static DoublePreference autonThirdDistance = new DoublePreference("Auton Third Distance", -30);
    public static DoublePreference autonFourthDistance = new DoublePreference("Auton Third Distance", 30);


    //Three ball auton

    //Spinner

}

