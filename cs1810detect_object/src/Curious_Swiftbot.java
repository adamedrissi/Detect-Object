import swiftbot.*; //Java docs import
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;
public class Curious_Swiftbot { //Curious Swiftbot class
	static SwiftBotAPI swiftbot; //Swiftbot API in order to use Swiftbot functionalities
	private static boolean ultraSound = false; //boolean for Curious' loop
	//Curious Swiftbot Mode
	public static void curiousMode() {
		swiftbot = Detect_Object.swiftBot; //swiftbot: Name of Swiftbot API in this class
		long startTimer = System.currentTimeMillis(); //Current time stored at the beginning of the session in milliseconds
        int[] blue = new int[] {0,255,0}; //blue rgb combination (g and b are inverted)
        int[] green = new int[] {0,0,255}; //green rgb combination
		AtomicInteger imageCount = new AtomicInteger(0); //Count for the images stored in log
		AtomicInteger objectCount = new AtomicInteger(0); //Count for the objects stored in log
		Detect_Object.swiftBot.disableButton(Button.X);  //Disables button listeners from other classes
		swiftbot.enableButton(Button.X, () -> { //Once the user click X button, the following code will go through
			swiftbot.setButtonLight(Button.X, true); //X button lights switches on
			ultraSound = true; //Stops Curious' loop
			swiftbot.stopMove();
			swiftbot.disableUnderlights();
			long finishTimer = System.currentTimeMillis(); //Timer ends
			System.out.println("Would you like to view the log from your session? If so, press Y. If not, press X again."); //Prompts the user for a response in order to display the session log or not
			swiftbot.enableButton(Button.Y, () -> { //Y button gets activated
				swiftbot.setButtonLight(Button.Y, true); //Y button light switches on
				swiftbot.stopMove();
				swiftbot.disableUnderlights();
				//Final log
				System.out.println("Curious Swiftbot"); //Current mode
				System.out.println("Duration: " + (finishTimer - startTimer)/1000 + " seconds"); //Time difference stored in milliseconds converted to seconds
				System.out.println("The number of objects encountered by Swiftbot were: " + objectCount); //Object count gets logged as an atomic integer rather a normal integer than wouldn't be final
				System.out.println("The number is images saved were: " + imageCount); //Image count gets logged as an atomic integer rather a normal integer than wouldn't be final
				System.out.println("Goodbye!"); //A friendly message for the user
				swiftbot.disableButton(Button.Y); //Button Y is disactivated as its job is done
				System.exit(0); //Exits the program
			});
			swiftbot.disableButton(Button.X); //Disables Button X before its new prompt
			swiftbot.enableButton(Button.X, () -> { //Button X is reactivated to exit the program without a log
				swiftbot.stopMove();
				swiftbot.disableUnderlights();
				System.out.println("We hope to see you again soon!"); //Gives the user a farewell message
				swiftbot.disableButton(Button.X); //Button X is disabled before exiting
				System.exit(0); //Exits the program
			});
		});
		while (!ultraSound) { //Starts loop, works until Button X is pressed
		double distance = swiftbot.useUltrasound(); //Distance in centimetres calculated by the ultrasound feature on Swiftbot
		if (distance > 16){ //Swiftbot outside Buffer Zone
			swiftbot.useUltrasound();
			swiftbot.fillUnderlights(blue); //Wandering Mode
			swiftbot.startMove(50,50); //Swiftbot goes towards the object
		}
		else if (distance < 14){ //Swiftbot inside Buffer Zone
			swiftbot.useUltrasound();
            swiftbot.fillUnderlights(green); //Green underlights are turned on
            swiftbot.startMove(-50,-50); //Swiftbot backs up until it reaches the Buffer Zone
		}
		else { //distance == 15, Swiftbot on Buffer Zone
			try {
				swiftbot.stopMove(); //Swiftbot stops moving
				objectCount.incrementAndGet(); //Object count is incremented for every object encountered
				System.out.println("Object encountered!");
				swiftbot.disableUnderlights(); //flashes underlights in green for a second
				Thread.sleep(200);
				swiftbot.fillUnderlights(green);
				Thread.sleep(200);
				swiftbot.disableUnderlights();
				Thread.sleep(200);
				swiftbot.fillUnderlights(green);
				Thread.sleep(200);
				swiftbot.disableUnderlights();
				Thread.sleep(200);
				swiftbot.fillUnderlights(green);
			try{
		        BufferedImage image = swiftbot.takeStill(ImageSize.SQUARE_720x720); //Swiftbot attempts to take an image of 720 by 720 pixels
		        if(image == null){ //Picture isn't taken
		        	System.out.println("ERROR: Image is null");
		        	System.exit(5);
		            }
		            else{ //Picture is taken
		                ImageIO.write(image, "png", new File("/home/pi/curious.png")); //A new image is saved
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
			swiftbot.fillUnderlights(blue); //Switches underlights to Wandering mode colour
			Thread.sleep(5000); //Swiftbot waits for the object to move for five seconds
			Thread.sleep(250); //Waits another second while moving in a slightly different direction
			swiftbot.move(100,2,750);
			}
			catch (Exception e) { //Error handling for ultrasound fail
				e.printStackTrace();
	            System.out.println("ERROR: Ultrasound Unsuccessful");
			}
		}
		}
	}
}