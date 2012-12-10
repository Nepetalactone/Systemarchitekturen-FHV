/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.wsu.KheperaSimulator.RobotController;

/**
 *
 * @author green
 */
public class FollowThatWallBB extends RobotController {

    boolean wallFound = false;

    public FollowThatWallBB() {
        this.setWaitTime(5L);
    }

    @Override
    public void doWork() throws Exception {

        if(!wallFound){
            for(int i = 0; i < 7; i++){
                if(getDistanceValue(i) > 1000){
                    wallFound = true;
                }
            }
            this.setMotorSpeeds(5, 5);
        }else{
            if(this.getDistanceValue(2) > 50 || this.getDistanceValue(3) > 50){
                this.setMotorSpeeds(5,-5);
            }else if(this.getDistanceValue(0) < 5 || this.getDistanceValue(1) < 5){
                this.setMotorSpeeds(2,5);
            }else if(this.getDistanceValue(0) > 50 || this.getDistanceValue(1) > 50){
                this.setMotorSpeeds(5,2);
            }else{
                this.setMotorSpeeds(5,5);
            }
        }

    }

    @Override
    public void close() throws Exception {
    }
}
