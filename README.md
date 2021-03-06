# 2019-Robot-Code

## Repository
Location: https://github.com/FRC-Team5811/2019-Robot-Code  
Team: 5811 (BONDS)  
Year: 2019  
This is the repository for all java robot code written for the First Robotics Competition.

### Committing
Commits should be made at least daily after any code changes are made.

Commit comments should identify what was accomplished since the last commit at a level where the viewer can understand at a mid to high level without looking at the code committed.  Use your own judgement about the level of detail to include.

Bad: Filled out the Roller system.
Better: Functions added to Roller class to controller roller motors.  Functions added: Func1, Func2, Func3.

### Branching
Rarely should code be committed directly to the master branch.  All development should happen in feature branches.  Feature branches should be created from the most recent version of the master.  Make sure to pull the master branch before creating a new feature branch.

#### Merging Feature Branches
1. When development of a feature branch is completed, the developer(s) should first pull the master branch into the feature branch.  This updates the feature branch with any changes that have been made to master since the feature branch was created.  
2. If there are any conflicts, resolve them before continuing.  Ask a mentor if you don't know how to do this.
3. Submit a pull request using the interface on github to merge the feature branch into the master branch.  Assign either your team leads or a mentor to review the pull request.
4. Reviewer: it is your job to make sure the code in the pull request follows coding best practices and has no major bugs.  If you find issues, add comments to the pull request explaining the problems.
5. Developer: Resolve any issues identified by your reviewer.  Commit any changes without changing branches.
6. Reviewer: When all issues have been resolved to your satisfaction, approve and merge the pull request.

## Coding Best Practices
Code should be formatted properly and easy to read.  Function and variable names should be descriptive.

### Encapsulation
https://stackify.com/oop-concept-for-beginners-what-is-encapsulation/

The fields of a class should not be directly accessible to outside classes.
Set all fields inside a class to private whenever possible.  If an outside class needs to manipulate or access the state of your class, they must do so through an accessor functions (getters & setters).  If no outside classes need to access a variable, do not create accessor functions.

When possible, functions should describe the action to be taken.  Rather than setting the values directly, allow the class to handle the low-level manipulations.  This abstraction hides complexity from other classes that don't need to know about the internal state of our class.

The example below demonstrates the ideas of encapsulation and abstraction.

```java
public class MyClass {
  private String name;
  private double speed;

  public MyClass(){
    name = "test";
    speed = 0;
  }

  public String getName(){
    return name;
  }

  public void setName(String newValue){
    name = newValue;
  }

  public void increaseSpeed(double increase){
    if(increase < 0)
      return;

    speed += increase;
    if(speed > 1){
      speed = 1;
    }
  }

  public void decreaseSpeed(double decrease){
    if(descrease < 0)
      return;

    speed -= decrease;
    if(speed < 0){
      speed = 0;
    }
  }

  public void stop(){
    speed = 0;
  }
}
```

Avoid changing the names and return types of accessor functions once implemented if at all possible.  These are how your class interacts with the rest of the program, so changing these may break the function of other classes.  If you need to change an accessor function, make sure to use the refactoring tools available in your IDE so other classes are updated accordingly. 

### Commenting
Functions should be commented following the javadoc standard.  This allows the function comment to be view at the point of reference.  Comments should describe what the function does without restating the function name.  Any parameters, returns, and possible errors should be described here.  Below is an example.

```java
/**
 * Increases the motor speed from its current speed to a maximum of 1.
 * 
 * @param increase The amount to increase the speed by.  Negative values are ignored.
 */
public void increaseSpeed(double increase);
```