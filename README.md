MAIN OBJECTIVE OF THE ASSESSMENT

This assignment is used to assess your abilities to implement a computer program as a solution to a problem, 
and your ability to communicate your solution.  
 
This assignment contributes 100% to the grade in CS1810 Software Implementation. It is assessed by a 
member of the teaching team through a ‘VIVA’ event. In the VIVA, you will demonstrate and explain the 
computer program that you have implemented. 
 
DESCRIPTION OF THE ASSESSMENT

Each member of the group is expected to implement a Java program for one of the Swiftbot robot tasks 
described below. You are also asked to design an algorithm for the same task as part of Assignment 2. There 
are 9 tasks. Each team member should choose only one and no two members can choose the same task. For 
both assignments 2 and 3, you should work on the same task. 
 
The task descriptions include the required, core functionalities of the program. However, marks will also be 
awarded for additional functionalities that you have come up with. The additional functionalities should be 
useful and ingenuous and showcase your software development skills. The additional functionalities should 
not replace or conflict with the required functionalities of the task descriptions.

Task 5 – Detect Object 
 
The purpose of this program is to make the Swiftbot survey a space either by following or avoiding an object 
within a specified range. Before a robot is released to survey an area, each robot is assigned a specific role; 
‘Curious Swiftbot’ or ‘Scaredy Swiftbot’ or ‘Dubious Swiftbot’.   
 
When the program starts it should ask the user to specify a mode between ‘Curious Swiftbot’, ‘Scaredy 
Swiftbot’ or ‘Dubious Swiftbot’ by scanning a QR code.  
 
Then, the Swiftbot should start wandering around at a low speed until it encounters an object. When  
wandering around the underlights should be set to blue. 
 
If the QR code reads ‘Curious Swiftbot’ mode, the underlights should turn green, when it encounters an  
object. The Swiftbot should always maintain a gap (buffer zone) of 15 cm between the Swiftbot and the  
object. If the object is placed beyond the buffer zone, the Swiftbot should turn the underlights to green and  
start moving towards the object, and stop once it reaches the required gap from the object and turn off the 
lights. If the object is placed inside the buffer zone, Swiftbot should turn the underlights to green and move 
backwards to form the required gap, then stop and turn off the underlights. If the object is placed at the 
required gap, Swiftbot should blink the underglights in green and remain stationary. Once the robot 
completes a movement (moving backward/ forward/ remaining stationary) it should take an image of the 

object in front and save it. Then wait for 2 seconds and check whether the object has moved (backwards or 
forwards) from its last position and move to maintain the required gap. If the Swiftbot has not encountered 
an object for five seconds or the object has not moved for five seconds, it should wait for a second, and start 
moving again in a slightly different direction. 
 
If the QR code reads ‘Scaredy Swiftbot’ mode, when the Swiftbot encounters an object within 1 meter of its 
current position it should do the following: take an image of the object and save it, and then blink the 
underlights, back up and turn in the opposite direction and move away from it for three seconds. The 
underlights should be set to blue when the Swiftbot is wandering around, and it should change to red when 
the Swiftbot sees an object and is moving away from it. If the Swiftbot has not encountered an object for five 
seconds, it should wait for a second, and start moving again in a slightly different direction. The object should 
be placed in front of the Swiftbot and should move only backwards or forwards, from where it was originally 
placed. The object shouldn’t move sideways.  
 
If the QR code reads ‘Dubious Swiftbot’ mode, then the program should randomly choose and execute either 
the ‘Curious’ or the ‘Scaredy’ mode.  
 
The program stops when the user presses the ‘X’ button (on the Swiftbot).  
 
Before it terminates, it should ask the user whether they would like to view the log of the execution. If the 
user responds Yes by pressing the ‘Y’ button (n the Swiftbot), the program should display the following 
information:  
▪ The mode it ran (‘Scaredy Swiftbot’ or ‘Curious Swiftbot’).  
▪ The duration of the execution.  
▪ The number of times the Swiftbot encountered an object.  
▪ The number of images saved. 
 
If the user doesn’t want to view the log, the user should press button X (on the Swiftbot) again.  
 
This program should be implemented using a command-line user interface (input and output printed in the 
command prompt). Your program should make appropriate error checks and exception handling in order to 
ensure that the input values are within the valid range specified in the task description. Invalid input should 
be rejected, and the program should display appropriate error messages and ask the user for new input. The 
software should be designed in such a way that it enhances user experience (i.e. ease of use). 
 
This description includes the required, core functionalities of the program. However, marks will also be 
awarded for additional functionalities that you have come up with. The additional functionalities should be 
useful and ingenuous and showcase your software development skills. The additional functionalities should 
not replace or conflict with the required functionalities of the task.  
