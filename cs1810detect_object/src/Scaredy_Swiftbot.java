import swiftbot.*; //Java docs import
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;
public class Scaredy_Swiftbot { //Scaredy Swiftbot class
	static SwiftBotAPI Swiftbot; //Swiftbot API in order to use Swiftbot functionalities
	private static boolean soundUltra = false; //boolean for Scaredy's loop
	//Scaredy Swiftbot Mode
	public static void scaredyMode() {
		Swiftbot = Detect_Object.swiftBot; //Swiftbot: Name of Swiftbot API in this class
		long startTimer = System.currentTimeMillis(); //Current time stored at the beginning of the session in milliseconds
		int[] red = new int[] {255,0,0}; //red rgb combination
        int[] blue = new int[] {0,255,0}; //blue rgb combination (g and b are inverted)
        AtomicInteger imageCount = new AtomicInteger(0); //Count for the images stored in log
		AtomicInteger objectCount = new AtomicInteger(0); //Count for the objects stored in log
		Detect_Object.swiftBot.disableButton(Button.X); //Disables button listeners from other classes
		Swiftbot.enableButton(Button.X, () -> { //Once the user click X button, the following code will go through
			Swiftbot.setButtonLight(Button.X, true); //X button lights switches on
			soundUltra = true; //Stops Curious' loop
			Swiftbot.stopMove();
			Swiftbot.disableUnderlights();
			long finishTimer = System.currentTimeMillis(); //Timer ends
			System.out.println("Would you like to view the log from your session? If so, press Y. If not, press X again."); //Prompts the user for a response in order to display the session log or not
			Swiftbot.enableButton(Button.Y, () -> { //Y button gets activated
				Swiftbot.setButtonLight(Button.Y, true); //Y button light switches on
				Swiftbot.stopMove();
				Swiftbot.disableUnderlights();
				//Final log
				System.out.println("Scaredy Swiftbot"); //Current mode
				System.out.println("Duration: " + (finishTimer - startTimer)/1000 + " seconds"); //Time difference stored in milliseconds converted to seconds
				System.out.println("The number of objects encountered by Swiftbot were: " + objectCount); //Object count gets logged as an atomic integer rather a normal integer than wouldn't be final
				System.out.println("The number is images saved were: " + imageCount); //Image count gets logged as an atomic integer rather a normal integer than wouldn't be final
				System.out.println("Goodbye!"); //A friendly message for the user
				Swiftbot.disableButton(Button.Y);  //Button Y is disactivated as its job is done
				System.exit(0); //Exits the program
			});
			Swiftbot.disableButton(Button.X); //Disables Button X before its new prompt
			Swiftbot.enableButton(Button.X, () -> { //Button X is reactivated to exit the program without a log
				Swiftbot.stopMove();
				Swiftbot.disableUnderlights();
				System.out.println("Hope to see you again soon!"); //Gives the user a farewell message
				Swiftbot.disableButton(Button.X); //Button X is disabled before exiting
				System.exit(0); //Exits the program
			});
		});
       while (!soundUltra) { //Starts loop, works until Button X is pressed
    	   double distance = Swiftbot.useUltrasound(); //Distance in centimetres calculated by the ultrasound feature on Swiftbot
    	   if (distance > 101) { //Swiftbot outside Buffer Zone
    		   Swiftbot.useUltrasound();
			Swiftbot.fillUnderlights(blue); //Wandering Mode
			Swiftbot.startMove(40,40); //Swiftbot slowly goes towards the object
		}
		else if (distance < 99) { //Swiftbot inside Buffer Zone
			Swiftbot.useUltrasound();
            Swiftbot.fillUnderlights(red); //Red underlights are turned on
            Swiftbot.startMove(-100,-100); //Swiftbot backs up until it reaches the buffer zone in a hurry
		}
		else { //distance == 100, Swiftbot on Buffer Zone
			try {
				Swiftbot.stopMove();
				Swiftbot.fillUnderlights(red);
				objectCount.incrementAndGet();
			try{
		        BufferedImage image = Swiftbot.takeStill(ImageSize.SQUARE_720x720); //Swiftbot attempts to take an image of 720 by 720 pixels
		        if(image == null){ //picture isn't taken
		        	System.out.println("ERROR: Image is null");
		            System.exit(5);
		            }
		            else{ //picture is taken
		            	ImageIO.write(image, "png", new File("/home/pi/scaredy.png")); //A new image is saved
		            	imageCount.incrementAndGet(); //Image count is incremented for every image taken
		                System.out.println("SUCCESS: Image saved"); //Outputs to inform the user of the picture's success
		                Thread.sleep(2000); //Waits for 2 seconds
		            }
		        }
		        catch (Exception e){ //Error handling for camera fail
		            System.out.println("\nCamera not enabled!");
		            System.out.println("Try running the following command: ");
		            System.out.println("sudo raspi-config nonint do_camera 0\n");
		            System.out.println("Then reboot using the following command: ");
		            System.out.println("sudo reboot\n");
		            System.exit(5);
		        }
			Swiftbot.disableUnderlights(); //Swiftbot flashes underlights in red
			Thread.sleep(200);
			Swiftbot.fillUnderlights(red);
			Thread.sleep(200);
			Swiftbot.disableUnderlights();
			Thread.sleep(200);
			Swiftbot.fillUnderlights(red);
			Thread.sleep(200);
			Swiftbot.disableUnderlights();
			Thread.sleep(200);
			Swiftbot.fillUnderlights(red);
			Swiftbot.move(-100,-100, 1000); //Swiftbot backs up for a second
			Swiftbot.fillUnderlights(blue); //Wandering mode lights
			Swiftbot.move(100,1,1400); //Swiftbot turns around
			Swiftbot.move(60, 60, 3000); //Swiftbot moves away for three seconds a bit quicker than usual
			}
			catch (Exception e) { //Error handling for ultrasound fail
				e.printStackTrace();
	            System.out.println("ERROR: Ultrasound Unsuccessful");
			}
		}
	}
}
}