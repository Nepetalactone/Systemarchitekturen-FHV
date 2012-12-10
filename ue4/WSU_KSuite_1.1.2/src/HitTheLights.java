
import edu.wsu.KheperaSimulator.RobotController;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author green
 */
public class HitTheLights extends RobotController {

    public HitTheLights() {
        this.setWaitTime(5L);
    }

    @Override
    public void doWork() throws Exception {

        if (this.getLightValue(2) < 400 || this.getLightValue(3) < 400) {
            if(this.getLightValue(2) < this.getLightValue(3)){
                this.setMotorSpeeds(2, 5);
            }else{
                this.setMotorSpeeds(5, 2);
            }
        }else if (getDistanceValue(2) > 50) {
            setMotorSpeeds(2, -5);
        } else if (getDistanceValue(3) > 50) {
            setMotorSpeeds(-5, 2);
        } else if (getDistanceValue(1) > 50) {
            setMotorSpeeds(5, -5);
        } else if (getDistanceValue(4) > 50) {
            setMotorSpeeds(-5, 5);
        } else if (getDistanceValue(0) > 50) {
            setMotorSpeeds(5, -5);
        } else if (getDistanceValue(5) > 50) {
            setMotorSpeeds(-5, 5);
        } else {
            this.setMotorSpeeds(5, 5);
        }
    }

    @Override
    public void close() throws Exception {
    }
}
