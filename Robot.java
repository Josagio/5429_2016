
package org.usfirst.frc.team5429.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    
	
	final String defaultAuto = "Default";
    final String RoughTerrainAuto = "RoughTerrainAuto";
    final String RampartsAuto = "RampartsAuto";
    final String MoatAuto = "MoatAuto";
    final String WallAuto = "WallAuto";
    final String PortcullisAuto = "PortcullisAuto";
    final String LowbarAuto = "LowbarAuto";
    
    //final String resetEncoder = "resetEncoder";
    //final String liveEncoder = "liveEncoder" ;
    
    String autoSelected;
    //String autoSelectedButton;
    SendableChooser chooser;
    SendableChooser button;
    CANTalon _frontLeftMotor = new CANTalon(0); 		/* device IDs here (1 of 2) */
	CANTalon _rearLeftMotor = new CANTalon(1);
	CANTalon _frontRightMotor = new CANTalon(2);
	CANTalon _rearRightMotor = new CANTalon(3);
	//CameraServer.StartAutomaticCapture();
	/* extra talons for six motor drives */
	CANTalon _intake = new CANTalon(15);
	CANTalon _tomohawk = new CANTalon(14);
	
	
	RobotDrive _drive = new RobotDrive(_frontLeftMotor, _rearLeftMotor, _frontRightMotor, _rearRightMotor);
	
	Joystick _joy = new Joystick(0);
	Joystick _joy2 = new Joystick(1);
	Joystick _xBoxController = new Joystick(2);
	CameraServer server = CameraServer.getInstance();
	//server.setQuality(50);
//	stry.startAutomaticCapture();
	
	// Autonomous Variables
	int autoCounter = 0;
	int autoState = 0;
	
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("Rough Terrain", RoughTerrainAuto);
        chooser.addObject("Ramparts", RampartsAuto);
        chooser.addObject("Moat", MoatAuto);
        chooser.addObject("RockWall", WallAuto);
        chooser.addObject("Portcullis", PortcullisAuto);
        chooser.addObject("LowBar", LowbarAuto);
        
        SmartDashboard.putData("Auto choices", chooser);
        //_leftSlave.changeControlMode(TalonControlMode.Follower);
    	//_rightSlave.changeControlMode(TalonControlMode.Follower);
    	//_leftSlave.set(11); 							/* device IDs here (2 of 2) */
    	//_rightSlave.set(14);
        
      /**  button = new SendableChooser();
        button.addDefault("Default button", defaultAuto);
    	button.addObject("liveEncoder", liveEncoder);
    	button.addObject("resetEncoder", resetEncoder);
    	SmartDashboard.putData("Reset Encoder ", button);
    	*/
    	server.startAutomaticCapture();
    	/* the Talons on the left-side of my robot needs to drive reverse(red) to move robot forward.
    	 * Since _leftSlave just follows frontLeftMotor, no need to invert it anywhere. */
    	_drive.setInvertedMotor(MotorType.kFrontLeft, true);
    	_drive.setInvertedMotor(MotorType.kRearLeft, true);

    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoCounter = 0;
    	autoState = 0;
    	
    	autoSelected = (String) chooser.getSelected();
    	
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    public void Run_Rough_Terrain_Auto()
    {
    	switch(autoState)
    	{
    	case 0:
    		// Rough Terrain code 
    		_tomohawk.setEncPosition(1345);
    		_rearRightMotor.set(-.52);;
    		_rearLeftMotor.set(-.50);;
    		_frontRightMotor.set(-.50);;
    		_frontLeftMotor.set(-.50);;
    		
    		// got to state 2 when counter = 100
    		if (autoCounter >= 109.87)
    		{
    			
    			autoState = 1;
    			autoCounter = 0;
    			
    		}
    		break;
    		
    	case 1:
    		_rearRightMotor.set(.30);;  // breaking for after rough terrain
    		_rearLeftMotor.set(.30);;
    		_frontRightMotor.set(.30);;
    		_frontLeftMotor.set(.30);;
    		if(autoCounter >= 25)
    		{
    			autoState = 2;
    		    autoCounter = 0;
    		}
    			
    		   
    		break;
    	case 2:
    		_rearRightMotor.set(0);;
    		_rearLeftMotor.set(0);;
    		_frontRightMotor.set(0);;
    		_frontLeftMotor.set(0);;
    		break;
    		
    	}
    		
    
    
    	
    }

    public void Run_Lowbar_auto()
    {
    	switch(autoState)
    	{
    	case 0:
    		// Rough Terrain code 
    		_tomohawk.setEncPosition(1345);
    		_rearRightMotor.set(-.52);;
    		_rearLeftMotor.set(-.50);;
    		_frontRightMotor.set(-.50);;
    		_frontLeftMotor.set(-.50);;
    		_tomohawk.set(-.40);
    		
    		// got to state 2 when counter = 100
    		if (_tomohawk.getEncPosition() <= 730)
    		{
    	      _tomohawk.set(0);
    		}
    		if (autoCounter >= 109.87)
    		{
    			
    			autoState = 1;
    			autoCounter = 0;
    			
    		}
    		break;
    		
    	case 1:
    		_rearRightMotor.set(.30);;  // breaking for after rough terrain
    		_rearLeftMotor.set(.30);;
    		_frontRightMotor.set(.30);;
    		_frontLeftMotor.set(.30);;
    		if(autoCounter >= 25)
    		{
    			autoState = 2;
    		    autoCounter = 0;
    		}
    			
    		   
    		break;
    	case 2:
    		_rearRightMotor.set(0);;
    		_rearLeftMotor.set(0);;
    		_frontRightMotor.set(0);;
    		_frontLeftMotor.set(0);;
    		break;
    		
    	}
    		
    
    
    	
    }

    
    public void Run_Ramparts_Auto()
    {
    	switch(autoState)
    	{
    	case 0:
    		// drive toward ramparts
    		_tomohawk.setEncPosition(1345);
    		_rearRightMotor.set(-.72);;
    		_rearLeftMotor.set(-.70);;
    		_frontRightMotor.set(-.70);;
    		_frontLeftMotor.set(-.70);;
    		
    		// got to state 2 when counter = 100
    		if (autoCounter >= 98.7)
    		{
    			autoState = 1;
    			autoCounter = 0;
    		}
    		break;
    		
    	case 1:
    	
    		_rearRightMotor.set(.30);;
    		_rearLeftMotor.set(.30);;
    		_frontRightMotor.set(.30);;
    		_frontLeftMotor.set(.30);;
    		
    		// got to state 2 when counter = 100
    		if (autoCounter >= 70)
    		{
    			autoState = 2;
    			autoCounter = 0;
    		}
    		break;
    	case 2:
    		_rearRightMotor.set(.0);;
    		_rearLeftMotor.set(.0);;
    		_frontRightMotor.set(.00);;
    		_frontLeftMotor.set(.0);;
    		break;
    		
    		   }
    	}
    	
    public void Run_Moat_Auto()
    {
    	switch(autoState)
    	{
    	case 0:
    		// Rough Terrain code 
    		_tomohawk.setEncPosition(1345);
    		_rearRightMotor.set(-.72);;
    		_rearLeftMotor.set(-.70);;
    		_frontRightMotor.set(-.70);;
    		_frontLeftMotor.set(-.70);;
    		
    		// got to state 2 when counter = 100
    		if (autoCounter >= 98.7)
    		{
    			
    			autoState = 1;
    			autoCounter = 0;
    			
    		}
    		break;
    		
    	case 1:
    		_rearRightMotor.set(.40);;  // breaking for after rough terrain
    		_rearLeftMotor.set(.40);;
    		_frontRightMotor.set(.40);;
    		_frontLeftMotor.set(.40);;
    		if(autoCounter >= 30)
    		{
    			autoState = 2;
    		    autoCounter = 0;
    		}
    			
    		   
    		break;
    	case 2:
    		_rearRightMotor.set(0);;
    		_rearLeftMotor.set(0);;
    		_frontRightMotor.set(0);;
    		_frontLeftMotor.set(0);;
    		break;
    		
    	}
    		
    
   }
    
    public void Run_Wall_Auto()
    {
    	switch(autoState)
    	{
    	case 0:
    		// Rough Terrain code 
    		_tomohawk.setEncPosition(1345);
    		_rearRightMotor.set(-.72);;
    		_rearLeftMotor.set(-.70);;
    		_frontRightMotor.set(-.70);;
    		_frontLeftMotor.set(-.70);;
    		
    		// got to state 2 when counter = 100
    		if (autoCounter >= 125.5)
    		{
    			
    			autoState = 1;
    			autoCounter = 0;
    			
    		}
    		break;
    		
    	case 1:
    		_rearRightMotor.set(.30);;  // breaking for after rough terrain
    		_rearLeftMotor.set(.30);;
    		_frontRightMotor.set(.30);;
    		_frontLeftMotor.set(.30);;
    		if(autoCounter >= 25)
    		{
    			autoState = 2;
    		    autoCounter = 0;
    		}
    			
    		   
    		break;
    	case 2:
    		_rearRightMotor.set(0);;
    		_rearLeftMotor.set(0);;
    		_frontRightMotor.set(0);;
    		_frontLeftMotor.set(0);;
    		break;
    		
    	}
    		
    
   }
    
    public void Run_Portcullis_Auto()
    {
    	switch(autoState)
    	{
    	case 0:
    		// Rough Terrain code 
    		_tomohawk.setEncPosition(1345);
    		_rearRightMotor.set(-.52);;
    		_rearLeftMotor.set(-.50);;
    		_frontRightMotor.set(-.50);;
    		_frontLeftMotor.set(-.50);;
    		
    		// got to state 2 when counter = 100
    		if (autoCounter >= 55.9)
    		{
    			
    			autoState = 1;
    			autoCounter = 0;
    			
    		}
    		break;
    		
    	case 1:
    		_rearRightMotor.set(-.30);;  // breaking for after rough terrain
    		_rearLeftMotor.set(-.30);;
    		_frontRightMotor.set(-.30);;
    		_frontLeftMotor.set(-.30);;
    		_tomohawk.set(.55);
    		if(_tomohawk.getEncPosition() >= 500)
    		{
    		 _tomohawk.set(0);	
    		}
    		
    		if(autoCounter >= 55)
    		{
    			autoState = 2;
    		    autoCounter = 0;
    		}
    			
    		   
    		break;
    	case 2:
    		_rearRightMotor.set(-.42);;
    		_rearLeftMotor.set(-.40);;
    		_frontRightMotor.set(-.40);;
    		_frontLeftMotor.set(-.40);;
    		if(autoCounter >= 55)
    		{
    			autoState = 3;
    			autoCounter = 0;
    		}
    		break;
    	
    	case 3:
    		_rearRightMotor.set(0);
    		_rearLeftMotor.set(0);
    		_frontRightMotor.set(0);
    		_frontLeftMotor.set(0);
    		break;
    	}
    		
    
    
    	
    }
 
    
        	

    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
    	autoCounter++;
    	SmartDashboard.putNumber("tomohawkCount", _tomohawk.getEncPosition());
       	switch(autoSelected) {
       	case RoughTerrainAuto:
    		Run_Rough_Terrain_Auto();
    		break;
    		
     	case RampartsAuto:
    		Run_Ramparts_Auto();
        	break;
        	
     	case MoatAuto:
     		Run_Moat_Auto();
        	break;
        	
     	case WallAuto:
     		Run_Wall_Auto();
     		break;
     	
     	case PortcullisAuto:
     		Run_Portcullis_Auto();
     		break;
     		
     	case LowbarAuto:
     		Run_Lowbar_auto();
     		break;
       	}
    
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	double left = _joy.getRawAxis(1); // logitech gampad left X, positive is forward
    	double right = _joy2.getRawAxis(1); //logitech gampad right X, positive means turn right
    	_drive.tankDrive(left, -right);//(forward, turn);
    	//autoSelectedButton = (String) button.getSmartDashboardType();
    	SmartDashboard.putNumber("tomohawkCount", _tomohawk.getEncPosition());
        
    	if(_xBoxController.getRawAxis(2) == 1)
    	{
    		//intake
    		_intake.set(-1.0);
    		
    	}
    	else if(_xBoxController.getRawAxis(3) == 1)
    	{
    		//outake
          _intake.set(1.0);
          
    	}
    	else
    	{
    	 //turn off	
    		_intake.set(0);
    	}
    	
    	/**
    	 * This Method is used to control 
    	 * Mid-Evils Tomohawks
    	 */
    	if(_xBoxController.getRawButton(5) && _tomohawk.getEncPosition() <= 750){
    		//upward _tomohawk
    		_tomohawk.set(0.55);
    		
    	}
    	else if(_xBoxController.getRawButton(6) && _tomohawk.getEncPosition() >= 700)
    	{
    		//downward _tomohawk
    		_tomohawk.set(-0.40);
    	}
    	else
    	{
    		//turn off
    	  _tomohawk.set(0);	
    	}
    	
    	/**
    	 * this will be used to adjust encoder position at
    	 * any moment to fix the tomohawk at its highest position or
    	 * at its lowest position
    	 */
    	if(_xBoxController.getRawButton(7))
    	{
    	   _tomohawk.setEncPosition(-18);
    	}
    	else if(_xBoxController.getRawButton(8))
    	{
    	   _tomohawk.setEncPosition(1345);	
    	}
    	
    }
    	
    	
    	//LiveWindow.run();
    

    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
