import swiftbot.*; //Java docs import
//import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

import javax.imageio.ImageIO;
//import java.util.Scanner;
public class Detect_Object {
	static SwiftBotAPI swiftBot; //Swiftbot API in order to use Swiftbot functionalities
	private static boolean ultraSound = false; //boolean for Curious' loop
	private static boolean soundUltra = false; //boolean for Scaredy's loop
	public static void main(String[] args) throws InterruptedException {
        swiftBot = new SwiftBotAPI(); //swiftBot: Name of Swiftbot API in this class
        System.out.println("TASK 5 - DETECT OBJECT"); //Outputs Task/Program Name
        System.out.println("Select between Curious Swiftbot, Scaredy Swiftbot and Dubious Swiftbot."); //Prompts user to select between the three available modes
        System.out.println("Please present the QR code when ready. If you are, press button Y."); //When user clicks Y on the Swiftbot, the QR code starts scanning
        swiftBot.enableButton(Button.Y, () -> {
            swiftBot.setButtonLight(Button.Y, true); //Y button light turns on, once the button is clicked
            try{
                System.out.println("Taking a capture in 5 seconds..");
                Thread.sleep(5000); //Gives user 5 seconds to adjust and find the QR code before decoding
                BufferedImage img = swiftBot.getQRImage(); //The Swiftbot takes a picture (doesn't save it)
                String decodedMessage = swiftBot.decodeQRImage(img); //Tries to find a string of text on the image
                if(decodedMessage.equals ("Curious Swiftbot") || decodedMessage.equals ("curious swiftbot") || decodedMessage.equals ("CURIOUS SWIFTBOT") || decodedMessage.equals ("Curious SwiftBot") || decodedMessage.equals ("Curious swiftbot") || decodedMessage.equals ("Curious Mode") || decodedMessage.equals ("curious mode") || decodedMessage.equals ("CURIOUS MODE") || decodedMessage.equals ("Curious mode") || decodedMessage.equals ("Curious Swiftbot Mode") || decodedMessage.equals ("Curious SwiftBot Mode") || decodedMessage.equals ("curious swiftbot mode") || decodedMessage.equals ("CURIOUS SWIFTBOT MODE") || decodedMessage.equals ("Curious swiftbot mode") || decodedMessage.equals ("Curious Swiftbot mode") || decodedMessage.equals ("Curious SwiftBot mode") || decodedMessage.equals("Curious") || decodedMessage.equals ("curious") || decodedMessage.equals ("CURIOUS")) {
                   System.out.println("SUCCESS: QR code has been found"); //19 combinations of the Curious Swiftbot mode, to give the user more ease of use
                   System.out.println("Selected Mode: " + decodedMessage);
                   swiftBot.disableButton(Button.Y); //Once the code is validated, the prompt for button Y is not needed
                   swiftBot.setButtonLight(Button.Y, false); //Turns of Y button light
                   curiousMode(); //Moves to method in class created for the Curious Swiftbot mode
                } 
                else if(decodedMessage.equals ("Scaredy Swiftbot") || decodedMessage.equals ("scaredy swiftbot") || decodedMessage.equals ("SCAREDY SWIFTBOT") || decodedMessage.equals ("Scaredy SwiftBot") || decodedMessage.equals ("Scaredy swiftbot") || decodedMessage.equals ("Scaredy Mode") || decodedMessage.equals ("scaredy mode") || decodedMessage.equals ("SCAREDY MODE") || decodedMessage.equals ("Scaredy mode") || decodedMessage.equals ("Scaredy Swiftbot Mode") || decodedMessage.equals ("Scaredy SwiftBot Mode") || decodedMessage.equals ("scaredy swiftbot mode") || decodedMessage.equals ("SCAREDY SWIFTBOT MODE") || decodedMessage.equals ("Scaredy swiftbot mode") || decodedMessage.equals ("Scaredy Swiftbot mode") || decodedMessage.equals ("Scaredy SwiftBot mode") || decodedMessage.equals("Scaredy") || decodedMessage.equals ("scaredy") || decodedMessage.equals ("SCAREDY")) {
                    System.out.println("SUCCESS: QR code has been found"); //19 combinations of the Scaredy Swiftbot mode, to give the user more ease of use
                    System.out.println("Selected Mode: " + decodedMessage);
                    swiftBot.disableButton(Button.Y); //Once the code is validated, the prompt for button Y is not needed
                    swiftBot.setButtonLight(Button.Y, false); //Turns of Y button light
                    scaredyMode(); //Moves to method in class created for the Scaredy Swiftbot mode
                 }
                 else if (decodedMessage.equals ("Dubious Swiftbot") || decodedMessage.equals ("dubious swiftbot") || decodedMessage.equals ("DUBIOUS SWIFTBOT") || decodedMessage.equals ("Dubious SwiftBot") || decodedMessage.equals ("Dubious swiftbot") || decodedMessage.equals ("Dubious Mode") || decodedMessage.equals ("dubious mode") || decodedMessage.equals ("DUBIOUS MODE") || decodedMessage.equals ("Dubious mode") || decodedMessage.equals ("Dubious Swiftbot Mode") || decodedMessage.equals ("Dubious SwiftBot Mode") || decodedMessage.equals ("dubious swiftbot mode") || decodedMessage.equals ("DUBIOUS SWIFTBOT MODE") || decodedMessage.equals ("Dubious swiftbot mode") || decodedMessage.equals ("Dubious Swiftbot mode") || decodedMessage.equals ("Dubious SwiftBot mode") || decodedMessage.equals("Dubious") || decodedMessage.equals ("dubious") || decodedMessage.equals ("DUBIOUS")) {
                    System.out.println("SUCCESS: QR code has been found"); //19 combinations of the Dubious Swiftbot mode, to give the user more ease of use
                    System.out.println("Selected Mode: " + decodedMessage);
                    System.out.println("This means the Swiftbot will randomly pick between Curious and Scaredy Swiftbot.");
                    swiftBot.disableButton(Button.Y); //Once the code is validated, the prompt for button Y is not needed
                    swiftBot.setButtonLight (Button.Y, false); //Turns of Y button light
                    int randomNumber = 1 + (int) (Math.random() * 2); //Generates a random number between 1 and 2
                    if (randomNumber == 1) {
                    	System.out.println("Curious Swiftbot has been selected!"); 
                    	curiousMode(); //Random number is 1, meaning Curious Swiftbot is chosen and activates the method on the respective class
                    }
                    else {
                    	System.out.println("Scaredy Swiftbot has been selected!");
                    	scaredyMode(); //Random number is 2, meaning Scaredy Swiftbot is chosen and activates the method on the respective class
                    }
                 }
                 else {
                    System.out.println("No QR Code was found. Try adjusting the distance of the SwiftBot's camera from the QR code, or try another."); //Prompts user to try again if code isn't recognised or isn't correct
                 }
             }catch(Exception e) { //Error handling necessary for timer and unexpected issues
                  System.out.println("ERROR: There isn't a valid QR code present, please try again.");
                  e.printStackTrace();
                  System.exit(5);
             }
         });
         swiftBot.enableButton(Button.X, () -> { //User can exit the program by clicking the X button on the Swiftbot
            	swiftBot.disableButton(Button.X); //Button disabled once clicked
            	swiftBot.setButtonLight(Button.X, true);  //Button light flashes on
            	System.out.println("Ciao!");  //Exit message
            	System.exit(0);  //Exits the program
         });
	}
	//Curious Swiftbot mode
	public static void curiousMode() {
		long startTimer = System.currentTimeMillis(); //Current time stored at the beginning of the session in milliseconds
        int[] blue = new int[] {0,255,0}; //blue rgb combination (g and b are inverted)
        int[] green = new int[] {0,0,255}; //green rgb combination
		AtomicInteger imageCount = new AtomicInteger(0); //Count for the images stored in log
		AtomicInteger objectCount = new AtomicInteger(0); //Count for the objects stored in log
		swiftBot.enableButton(Button.X, () -> { //Once the user click X button, the following code will go through
			swiftBot.setButtonLight(Button.X, true); //X button lights switches on
			ultraSound = true; //Stops Curious' loop
			swiftBot.stopMove();
			swiftBot.disableUnderlights();
			long finishTimer = System.currentTimeMillis(); //Timer ends
			System.out.println("Would you like to view the log from your session? If so, press Y. If not, press X again."); //Prompts the user for a response in order to display the session log or not
			swiftBot.enableButton(Button.Y, () -> { //Y button gets activated
				swiftBot.setButtonLight(Button.Y, true); //Y button light switches on
				swiftBot.stopMove();
				swiftBot.disableUnderlights();
				//Final log
				System.out.println("Curious Swiftbot"); //Current mode
				System.out.println("Duration: " + (finishTimer - startTimer)/1000 + " seconds"); //Time difference stored in milliseconds converted to seconds
				System.out.println("The number of objects encountered by Swiftbot were: " + objectCount); //Object count gets logged as an atomic integer rather a normal integer than wouldn't be final
				System.out.println("The number is images saved were: " + imageCount); //Image count gets logged as an atomic integer rather a normal integer than wouldn't be final
				System.out.println("Goodbye!"); //A friendly message for the user
				swiftBot.disableButton(Button.Y); //Button Y is disactivated as its job is done
				System.exit(0); //Exits the program
			});
			swiftBot.disableButton(Button.X); //Disables Button X before its new prompt
			swiftBot.enableButton(Button.X, () -> { //Button X is reactivated to exit the program without a log
				swiftBot.stopMove();
				swiftBot.disableUnderlights();
				System.out.println("We hope to see you again soon!"); //Gives the user a farewell message
				swiftBot.disableButton(Button.X); //Button X is disabled before exiting
				System.exit(0); //Exits the program
			});
		});
		while (!ultraSound) { //Starts loop, works until Button X is pressed
		double distance = swiftBot.useUltrasound(); //Distance in centimetres calculated by the ultrasound feature on Swiftbot
		if (distance > 16){ //Swiftbot outside Buffer Zone
			swiftBot.useUltrasound();
			swiftBot.fillUnderlights(blue); //Wandering Mode
			swiftBot.startMove(50,50); //Swiftbot goes towards the object
		}
		else if (distance < 14){ //Swiftbot inside Buffer Zone
			swiftBot.useUltrasound();
            swiftBot.fillUnderlights(green); //Green underlights are turned on
            swiftBot.startMove(-50,-50); //Swiftbot backs up until it reaches the Buffer Zone
		}
		else { //distance == 15, Swiftbot on Buffer Zone
			try {
				swiftBot.stopMove(); //Swiftbot stops moving
				objectCount.incrementAndGet(); //Object count is incremented for every object encountered
				System.out.println("Object encountered!");
				swiftBot.disableUnderlights(); //flashes underlights in green for a second
				Thread.sleep(200);
				swiftBot.fillUnderlights(green);
				Thread.sleep(200);
				swiftBot.disableUnderlights();
				Thread.sleep(200);
				swiftBot.fillUnderlights(green);
				Thread.sleep(200);
				swiftBot.disableUnderlights();
				Thread.sleep(200);
				swiftBot.fillUnderlights(green);
			try{
		        BufferedImage image = swiftBot.takeStill(ImageSize.SQUARE_720x720); //Swiftbot attempts to take an image of 720 by 720 pixels
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
			swiftBot.fillUnderlights(blue); //Switches underlights to Wandering mode colour
			Thread.sleep(5000); //Swiftbot waits for the object to move for five seconds
			Thread.sleep(250); //Waits another second while moving in a slightly different direction
			swiftBot.move(100,2,750);
			}
			catch (Exception e) { //Error handling for ultrasound fail
				e.printStackTrace();
	            System.out.println("ERROR: Ultrasound Unsuccessful");
			}
		}
		}
	}
	//Scaredy Swiftbot Mode
	public static void scaredyMode() {
		long startTimer = System.currentTimeMillis(); //Current time stored at the beginning of the session in milliseconds
		int[] red = new int[] {255,0,0}; //red rgb combination
        int[] blue = new int[] {0,255,0}; //blue rgb combination (g and b are inverted)
        AtomicInteger imageCount = new AtomicInteger(0); //Count for the images stored in log
		AtomicInteger objectCount = new AtomicInteger(0); //Count for the objects stored in log
		swiftBot.enableButton(Button.X, () -> { //Once the user click X button, the following code will go through
			swiftBot.setButtonLight(Button.X, true); //X button lights switches on
			soundUltra = true; //Stops Curious' loop
			swiftBot.stopMove();
			swiftBot.disableUnderlights();
			long finishTimer = System.currentTimeMillis(); //Timer ends
			System.out.println("Would you like to view the log from your session? If so, press Y. If not, press X again."); //Prompts the user for a response in order to display the session log or not
			swiftBot.enableButton(Button.Y, () -> { //Y button gets activated
				swiftBot.setButtonLight(Button.Y, true); //Y button light switches on
				swiftBot.stopMove();
				swiftBot.disableUnderlights();
				//Final log
				System.out.println("Scaredy Swiftbot"); //Current mode
				System.out.println("Duration: " + (finishTimer - startTimer)/1000 + " seconds"); //Time difference stored in milliseconds converted to seconds
				System.out.println("The number of objects encountered by Swiftbot were: " + objectCount); //Object count gets logged as an atomic integer rather a normal integer than wouldn't be final
				System.out.println("The number is images saved were: " + imageCount); //Image count gets logged as an atomic integer rather a normal integer than wouldn't be final
				System.out.println("Goodbye!"); //A friendly message for the user
				swiftBot.disableButton(Button.Y);  //Button Y is disactivated as its job is done
				System.exit(0); //Exits the program
			});
			swiftBot.disableButton(Button.X); //Disables Button X before its new prompt
			swiftBot.enableButton(Button.X, () -> { //Button X is reactivated to exit the program without a log
				swiftBot.stopMove();
				swiftBot.disableUnderlights();
				System.out.println("Hope to see you again soon!"); //Gives the user a farewell message
				swiftBot.disableButton(Button.X); //Button X is disabled before exiting
				System.exit(0); //Exits the program
			});
		});
       while (!soundUltra) { //Starts loop, works until Button X is pressed
    	   double distance = swiftBot.useUltrasound(); //Distance in centimetres calculated by the ultrasound feature on Swiftbot
    	   if (distance > 101) { //Swiftbot outside Buffer Zone
    		   swiftBot.useUltrasound();
			swiftBot.fillUnderlights(blue); //Wandering Mode
			swiftBot.startMove(40,40); //Swiftbot slowly goes towards the object
		}
		else if (distance < 99) { //Swiftbot inside Buffer Zone
			swiftBot.useUltrasound();
            swiftBot.fillUnderlights(red); //Red underlights are turned on
            swiftBot.startMove(-100,-100); //Swiftbot backs up until it reaches the buffer zone in a hurry
		}
		else { //distance == 100, Swiftbot on Buffer Zone
			try {
				swiftBot.stopMove();
				swiftBot.fillUnderlights(red);
				objectCount.incrementAndGet();
			try{
		        BufferedImage image = swiftBot.takeStill(ImageSize.SQUARE_720x720); //Swiftbot attempts to take an image of 720 by 720 pixels
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
			swiftBot.disableUnderlights(); //Swiftbot flashes underlights in red
			Thread.sleep(200);
			swiftBot.fillUnderlights(red);
			Thread.sleep(200);
			swiftBot.disableUnderlights();
			Thread.sleep(200);
			swiftBot.fillUnderlights(red);
			Thread.sleep(200);
			swiftBot.disableUnderlights();
			Thread.sleep(200);
			swiftBot.fillUnderlights(red);
			swiftBot.move(-100,-100, 1000); //Swiftbot backs up for a second
			swiftBot.fillUnderlights(blue); //Wandering mode lights
			swiftBot.move(100,1,1400); //Swiftbot turns around
			swiftBot.move(60, 60, 3000); //Swiftbot moves away for three seconds a bit quicker than usual
			}
			catch (Exception e) { //Error handling for ultrasound fail
				e.printStackTrace();
	            System.out.println("ERROR: Ultrasound Unsuccessful");
			}
		}
	}
}
}