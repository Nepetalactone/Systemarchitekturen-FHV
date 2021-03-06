 
import edu.wsu.KheperaSimulator.RobotController;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author team nepetalactone
 */
public class ex2t2 extends RobotController {
 
    private double[][] seekerMatrix;
    private double[][] pusherMatrix;
    private double[][] frenchMatrix;
    private double[] lastPosition;
    private int action = 0;
    private double backUpDistance = 1500;
 
    public ex2t2() {
        this.setWaitTime(5L);
        lastPosition = new double[]{-1, -1};
 
        seekerMatrix = new double[][]{
            {5, 5, 5, 0, -5, -5, 0, 0},
            {-5, -5, 0, 5, 5, 5, 0, 0}
        };
        pusherMatrix = new double[][]{
            {10, 10, 5, 0, -10, -10, 0, 0},
            {-10, -10, 0, 5, 10, 10, 0, 0}
        };
        frenchMatrix = new double[][]{
            {0, 0, -5, -5, 0, 0, 0, 0},
            {0, 0, -2, -2, 0, 0, 0, 0}
        };
 
    }
 
    @Override
    public void simStart() {
        super.simStart();
        this.setMotorSpeeds(5, 5);
    }
 
    @Override
    public void doWork() throws Exception {
        switch (action) {
            case 0:
                System.out.println("seek");
                if (seekBall()) {
                    action = 1;
                }
                break;
            case 1:
                System.out.println("push");
                int option = pushBallToLightsource();
 
                if (option == 0) {
                    action = 2;
                } else if (option == 1) {
                    action = 2; //do ghört eigentlich action = 3 ine, rotate fehlt no
                } else {
                    action = 1;
                }
                break;
            case 2:
                System.out.println("back");
                if (backUp()) {
                    action = 0;
                }
                break;
            case 3:
                System.out.println("rotate");
                if (rotate()) {
                    action = 1;
                }
                break;
        }
    }
 
    @Override
    public void close() throws Exception {
    }
 
    private boolean seekBall() {
        int[] speed = getSpeed(seekerMatrix);
        this.setMotorSpeeds(speed[0], speed[1]);
 
        if (getDistanceValue(2) > 1000 || (getDistanceValue(3) > 1000)) {
            return true;
        }
        return false;
    }
 
    private int pushBallToLightsource() {
 
        int[] speed = getSpeedToLight(pusherMatrix);
        this.setMotorSpeeds(speed[0], speed[1]);
 
        //Roboter ist an einer Lichtquelle - Ziel erreicht
        if (getLightValue(2) < 100 || getLightValue(3) < 100) {
            return 0;
        }
        
        if ((getDistanceValue(0) > 1000) || (getDistanceValue(1) > 1000) || (getDistanceValue(4) > 1000) || (getDistanceValue(5) > 1000)) {
            return 2;
        }
        
        //Roboter steckt in einer Wand
        if (this.lastPosition[0] == this.getLeftWheelPosition() && this.lastPosition[1] == this.getRightWheelPosition()) {
            return 1;
        }
        this.lastPosition[0] = this.getLeftWheelPosition();
        this.lastPosition[1] = this.getRightWheelPosition();
        return 2;
    }
 
    private boolean backUp() {
 
        int[] speed = getSpeed(frenchMatrix);
        this.setMotorSpeeds(speed[0], speed[1]);
 
        if (this.getDistanceValue(7) > 1000 || this.getDistanceValue(6) > 1000) {
            return true;
        }
 
        if (Math.abs(this.lastPosition[0] - this.getLeftWheelPosition()) < backUpDistance && Math.abs(this.lastPosition[1] - this.getRightWheelPosition()) < backUpDistance) {
            return false;
        }
        return true;
    }
 
    private boolean rotate() {
        this.setMotorSpeeds(-2, -2);
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
Logger.getLogger(ex2t2.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setMotorSpeeds(5, -5);
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
Logger.getLogger(ex2t2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
 
    private int[] getSpeedToLight(double[][] matrix) {
        double l = 0, r = 0;
        double[] lightSensors = new double[8];
 
        for (int i = 0; i < 8; i++){
            lightSensors[i] = (getLightValue(i))/500.0; //400
 
            l+= lightSensors[i] * matrix[0][i];
            r+= lightSensors[i] * matrix[1][i];
        }
        return new int[]{(int) Math.round(l), (int) Math.round(r)};
    }
 
    private int[] getSpeed(double[][] matrix) {
        double l = 0, r = 0;
        double[] distSensors = new double[8];
 
        for (int i = 0; i < 8; i++) {
            distSensors[i] = (1200.0 - getDistanceValue(i)) / 1000.0;
 
            l += distSensors[i] * matrix[0][i];
            r += distSensors[i] * matrix[1][i];
        }
 
        return new int[]{(int) Math.round(l), (int) Math.round(r)};
    }
}
 