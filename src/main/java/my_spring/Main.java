package my_spring;

/**
 * @author Evgeny Borisov
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        IRobot iRobot = (IRobot) ApplicationContext.getObject(IRobot.class);
        IRobot iRobot2 = (IRobot) ApplicationContext.getObject(IRobotImpl.class);
        IRobot iRobot3 = (IRobot) ApplicationContext.getObject(IRobot.class);

        iRobot.cleanRoom();
        iRobot2.cleanRoom();
    }
}
