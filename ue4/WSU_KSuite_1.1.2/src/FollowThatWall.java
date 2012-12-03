/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import edu.wsu.KheperaSimulator.RobotController;

/**
 *
 * @author green
 */
public class FollowThatWall extends RobotController{
    
    
    public FollowThatWall(){
        this.setWaitTime(5L);
    }

    @Override
    public void doWork() throws Exception {
        
        
        if(this.getDistanceValue(2) > 50 || this.getDistanceValue(3) > 50){
            this.setMotorSpeeds(5,-5);
        }else if(this.getDistanceValue(0) < 10 || this.getDistanceValue(1) < 10){
            this.setMotorSpeeds(2,5);
        }else if(this.getDistanceValue(0) > 50 || this.getDistanceValue(1) > 50){
            this.setMotorSpeeds(5,2);
        }else{
            this.setMotorSpeeds(5,5);
        }

    }

    @Override
    public void close() throws Exception {
        
    }
}
