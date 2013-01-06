
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

    
    private double[][] seekerMatrix;
    private double[][] pusherMatrix;
    private double[][] frenchMatrix;
    
    private double[] lastPosition;
    private int action = 0;
    private double backUpDistance = 1500;
    
    private boolean isTestedForWall = false;
    
    
    public ex2t1(){
        this.setWaitTime(5L);
        lastPosition = new double[]{-1,-1};
        
        seekerMatrix = new double[][]{
            { 5, 5, 5, 0, -5, -5, 0, 0},
            { -5, -5, 0, 5, 5, 5, 0, 0}
        };
        pusherMatrix = new double[][]{
            { 20, 20, 5, 0, -20, -20, 0, 0},
            { -20, -20, 0, 5, 20, 20, 0, 0}
        };
        frenchMatrix = new double[][]{
            { 0, 0, -5, -5,  0,  0, 0, 0},
            { 0,  0, -2, -2, 0, 0, 0, 0}
        };
        
    }
    
    @Override
    public void simStart(){
        super.simStart();
        this.setMotorSpeeds(5, 5);
    }
    
    @Override
    public void doWork() throws Exception {
        
        
        switch(action){
            case 0:
                System.out.println("seek");
                if(seekBall()){
                    action = 1;
                }
                break;
            case 1:
                System.out.println("push");
                if(pushBallToEdge()){
                    action = 2;
                }
                break;
            case 2:
                System.out.println("back");
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
        int[] speed = getSpeed(seekerMatrix);
        this.setMotorSpeeds(speed[0],speed[1]);

        if ((getDistanceValue(2) > 1000) || (getDistanceValue(3) > 1000)){
            return true;
        }
        return false;
    }
    
    private boolean pushBallToEdge(){
        
        int[] speed = getSpeed(pusherMatrix);
        this.setMotorSpeeds(speed[0],speed[1]); 
        
        if(this.lastPosition[0] == this.getLeftWheelPosition() && this.lastPosition[1] == this.getRightWheelPosition()){
            return true;
        }
        this.lastPosition[0] = this.getLeftWheelPosition();
        this.lastPosition[1] = this.getRightWheelPosition();
        return false;
    }
    
    private boolean backUp(){
        
        int[] speed = getSpeed(frenchMatrix);
        this.setMotorSpeeds(speed[0],speed[1]); 
        
        if(Math.abs(this.lastPosition[0] - this.getLeftWheelPosition()) < backUpDistance && Math.abs(this.lastPosition[1] - this.getRightWheelPosition()) < backUpDistance){
            return false;
        }
        return true;
    }
    
    private int[] getSpeed(double[][] matrix){
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
