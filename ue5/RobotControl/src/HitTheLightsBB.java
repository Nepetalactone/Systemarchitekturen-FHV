
import edu.wsu.KheperaSimulator.RobotController;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author green
 */
public class HitTheLightsBB extends RobotController {

    public HitTheLightsBB() {
        this.setWaitTime(5L);
    }

    @Override
    public void doWork() throws Exception {

        
        if(this.getLightValue(2) < 400 || this.getLightValue(3) < 400){
            this.setMotorSpeeds(5, 5);
        }else if(this.getLightValue(0) < 400 || this.getLightValue(1) < 400){
            this.setMotorSpeeds(-5, 5);
        }else if(this.getLightValue(4) < 400 || this.getLightValue(5) < 400){
            this.setMotorSpeeds(5, -5);
        }else if(this.getLightValue(6) < 400 || this.getLightValue(7) < 400){
            this.setMotorSpeeds(-5, -5);
        }else{
            this.setMotorSpeeds(5, 5);
        }
    }

    @Override
    public void close() throws Exception {
    }
}
