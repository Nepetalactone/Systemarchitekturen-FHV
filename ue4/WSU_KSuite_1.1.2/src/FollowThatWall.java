
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
            {-5, -5, 15, 25,  20,  20, 0, 0},
            { 5,  5, 25, 0, -20, -20, 0, 0}
            
                //goht wenn ma koa hindernisse ibaut
//            {-10, -5, 5, 7,  5,  10, 0, 0},
//            { 10,  5, 7, 5, -5, -10, 0, 0}
                
                //der fahrt perfekte kurfen um alles, aber dreht sich zersch 1mol im kreis..
//            {-5, -5, 15, 25,  20,  20, 0, 0},
//            { 5,  5, 25, 0, -20, -20, 0, 0}
            
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
            
            sensors[i] = (1200.0 - getDistanceValue(i)) / 1000.0;
            l += sensors[i] * matrix[0][i];
            r += sensors[i] * matrix[1][i];
        }
        return new int[]{(int) Math.round(l), (int) Math.round(r)};
    }
    
}
