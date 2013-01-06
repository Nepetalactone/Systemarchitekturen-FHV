
import edu.wsu.KheperaSimulator.RobotController;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author green
 */
public class ex2t1 extends RobotController{

    
    private double[][] matrix;
    private double[] lastPosition;
    private int action = 0;
    private double backUpDistance = 2000;
    
    
    public ex2t1(){
        this.setWaitTime(5L);
        lastPosition = new double[]{-1,-1};
        matrix = new double[][]{
            { 20, 20, 5, 0, -20, -20, 0, 0},
            { -20, -20, 0, 5, 20, 20, 0, 0}
        };
    
        
    }
    
    @Override
    public void doWork() throws Exception {
        
        
        switch(action){
            case 0:
                if(seekBall()){
                    action = 1;
                }
                break;
            case 1:
                if(pushBallToEdge()){
                    action = 2;
                }
                break;
            case 2:
                if(backUp()){
                    action = 0;
                }
                break;
        }
    }

    @Override
    public void close() throws Exception {
        
        
    }
    
    private boolean seekBall(){
        return true;
    }
    
    private boolean pushBallToEdge(){
        double l = 0, r = 0;
        double[] sensors = new double[8];

        for (int i = 0; i < 8; i++) {
            
            sensors[i] = (1200.0 - getDistanceValue(i)) / 1000.0;
            
            l += sensors[i] * matrix[0][i];
            r += sensors[i] * matrix[1][i];
        }
        
        this.setMotorSpeeds((int) Math.round(l), (int) Math.round(r)); 
        
        if(this.lastPosition[0] == this.getLeftWheelPosition() && this.lastPosition[1] == this.getRightWheelPosition()){
            return false;
        }
        this.lastPosition[0] = this.getLeftWheelPosition();
        this.lastPosition[1] = this.getRightWheelPosition();
        return true;
    }
    
    private boolean backUp(){
        this.setMotorSpeeds(-5, -5);
        if(Math.abs(this.lastPosition[0] - this.getLeftWheelPosition()) < backUpDistance && Math.abs(this.lastPosition[1] - this.getRightWheelPosition()) < backUpDistance){
            return false;
        }
        return true;
    }
}
