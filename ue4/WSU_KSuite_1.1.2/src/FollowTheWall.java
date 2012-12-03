/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import edu.wsu.KheperaSimulator.RobotController;

/**
 *
 * @author christoph
 */
public class FollowTheWall extends RobotController {
    
    public FollowTheWall() {
        this.setWaitTime(5L);
    }
    
    @Override
    public void doWork() throws Exception {
        int x=0;
        int y=0;

      
        if (this.getDistanceValue(3) > 200 || this.getDistanceValue(2) > 200) {
            x = -2; y = 5;
        } else if (this.getDistanceValue(4) < 10 && this.getDistanceValue(5) > 200 && this.getDistanceValue(5) < 600) {
            x = 5; y = -2; 
        } else {
            x = 5; y = 5;
        }
        setMotorSpeeds(x, y);
    }

    @Override
    public void close() throws Exception {
    }

    
    
}
