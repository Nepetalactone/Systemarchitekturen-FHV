
import edu.wsu.KheperaSimulator.RobotController;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author green
 */
public class HitTheLights extends RobotController{

    private double[][] matrix;
    public HitTheLights(){
        this.setWaitTime(5L);
        
        matrix = new double[][]{
            {-5, -5, 5, 5, 5, 5, -4, -5},
            {5, 5, 5, 5, -5, -5, -5, -4}
        };
                           
    }
    
    @Override
    public void simStart(){
        super.simStart();
        this.setMotorSpeeds(5, 5);
    }
    
    @Override
    public void doWork() throws Exception {
        int[] speed = getSpeed();
        this.setMotorSpeeds(speed[0], speed[1]);
    }

    @Override
    public void close() throws Exception {
        
    }
    
    private int[] getSpeed(){
        double l = 0, r = 0;
        double[] sensors = new double[8];

        for (int i = 0; i < 8; i++) {
            
            sensors[i] = (getLightValue(i) - 500.0) / -500.0;
            
            l += sensors[i] * matrix[0][i];
            r += sensors[i] * matrix[1][i];
        }
        
        return new int[]{(int) Math.round(l), (int) Math.round(r)};
    }
    
}
