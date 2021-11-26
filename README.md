# Workout: Home & Gym Workouts

## Project Description


- **What will the application do?**

The application is a workout app. The app will have a default workout
that the user can use, or
the user can select different exercises and build their own workouts.
When the user selects a workout, the app will sequentially provide each 
exercise.


- **Who will use it?**

The application may be used by *individuals who are interested in 
working* out to select a predetermined training regimen for the day, 
or by *more experienced individuals / gym trainers* who will construct a proper workout regimen. 

- **Why is this project of interest to you?**

This project is something that I've had in mind for a while, as I was unhappy
with the functionality of the workout app I was using. The idea came to me 
while being frustrated that the app I was using
did not allow me to alter the workouts or create my own.



## User Stories

- As a user, I want to be able to select a workout program from the workout list and *"play"* it
- As a user, I want to be able to add my own exercises to the exercise repository
- As a user, I want to be able to build my own workouts and add it to the workout repository
- As a user, I want to be able to see the number of completed workouts and reps
- As a user, I want to be able to see all the workouts available to me
- As a user, I want to be able to see all the exercises available to me
- As a user, I want to be able to save my workouts, exercises and personal stats to a file
- As a user, I want to be able to retrieve my workouts, exercises and personal stats from a file



## Phase 4: Task 2
### Representative log of events happenings after a series of actions:
Thu Nov 25 00:20:59 PST 2021
An exercise named Pull-ups was added to your exercise list.


Thu Nov 25 00:21:09 PST 2021
A workout named Hard Work Out was added to your workout list.


Thu Nov 25 00:21:29 PST 2021
4 reps were added to your stats.


Thu Nov 25 00:21:30 PST 2021
5 reps were added to your stats.


Thu Nov 25 00:21:30 PST 2021
4 reps were added to your stats.


Thu Nov 25 00:21:30 PST 2021
4 reps were added to your stats.


Thu Nov 25 00:21:30 PST 2021
4 reps were added to your stats.


Thu Nov 25 00:21:31 PST 2021
A workout was added to your stats.


Thu Nov 25 00:21:38 PST 2021
4 reps were added to your stats.


Thu Nov 25 00:21:39 PST 2021
4 reps were added to your stats.


Thu Nov 25 00:21:39 PST 2021
4 reps were added to your stats.


Thu Nov 25 00:21:39 PST 2021
5 reps were added to your stats.


Thu Nov 25 00:21:39 PST 2021
5 reps were added to your stats.


Thu Nov 25 00:21:39 PST 2021
5 reps were added to your stats.


Thu Nov 25 00:21:39 PST 2021
4 reps were added to your stats.


Thu Nov 25 00:21:40 PST 2021
4 reps were added to your stats.


Thu Nov 25 00:21:41 PST 2021
A workout was added to your stats.



Process finished with exit code 0

## Phase 4: Task 3
If I had more time to design the application, I would make the following changes:

- All of my Buttons that change the display use the same code, but use a different 
number to determine which section of the tabbed pane they will go to. I would make 
another abstract version of Button that extends Button and implements the code in 
the display buttons. Then those buttons would extend DisplayButton instead. 
- My WorkOutAppUI class has gotten extremely long and confusing. I would instead add a separate
class for each panel to simplify it. Each screen tab would then contain the methods 
it needs within it. Right now they are all included in the WorkOutAppUI class. 
- I would make Exercise an interface and insert two other classes that implement it: 
RepBased and TimeBased exercises. This would also add functionality to the app. 
- I would add additional default exercises and workouts, so the multiplicity of the 
associations would be higher between WorkOutAppUI and WorkOut and Exercise class.
This would make the app more engaging, as the user would be able to use it right
away, without needing to put in a lot of work and add exercises / workouts. 
- I would remove the LogPrinter class and make it a method in the WorkOutAppUI, 
since I am not planning on using it anywhere else. 