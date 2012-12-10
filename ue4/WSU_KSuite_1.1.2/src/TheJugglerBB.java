
import edu.wsu.KheperaSimulator.RobotController;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author green
 */
public class TheJugglerBB extends RobotController {
    
    public TheJugglerBB() {
        this.setWaitTime(5L);
    }
    
    @Override
    public void doWork() throws Exception {
        if(this.getDistanceValue(2) > 400 && this.getDistanceValue(3) > 400){
            this.setMotorSpeeds(5, 5);
        }else if(this.getDistanceValue(2) > 400){
            this.setMotorSpeeds(-5, 5);
        }else if(this.getDistanceValue(3) > 400){
            this.setMotorSpeeds(5, -5);
        }else if(this.getDistanceValue(0) > 100|| this.getDistanceValue(1) > 100){
            this.setMotorSpeeds(-5, 5);
        }else if(this.getDistanceValue(4) > 100|| this.getDistanceValue(5) > 100){
            this.setMotorSpeeds(5, -5);
        }else{
            this.setMotorSpeeds(5, 5);
        }
    }

    @Override
    public void close() throws Exception {
    }
    
}
