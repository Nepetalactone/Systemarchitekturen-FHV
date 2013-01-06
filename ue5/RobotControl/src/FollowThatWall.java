
import edu.wsu.KheperaSimulator.RobotController;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author green
 */
public class FollowThatWall extends RobotController{

    private double[][] matrix;
    public FollowThatWall(){
        this.setWaitTime(5L);
        
        matrix = new double[][]{
            
//           {-6, -6, 0, 9,  1,  1, 0, 0},
//           { 6,  6, 9, 2, -1, -1, 0, 0}
            
                //mit berechnung nr2 a kle bessa
                //{-7, -7, 0, 9,  2,  2, 0, 0},
                //{ 7,  7, 9, 4, -2, -2, 0, 0}
                
                //der fahrt guate kurfen um alles, aber dreht sich zersch 1mol im kreis..
            {-20, -20, 0, 25,  5,  5, 0, 0},
            { 20,  20, 25, 15, -5, -5, 0, 0}
            
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
            
            //original
            //sensors[i] = (1200.0 - getDistanceValue(i)) / 1000.0;
            
            //berechnung nr 2
            sensors[i] = (1023.0 - getDistanceValue(i)) / 1023.0;
            l += sensors[i] * matrix[0][i];
            r += sensors[i] * matrix[1][i];
        }
        return new int[]{(int) Math.round(l), (int) Math.round(r)};
    }
    
}
