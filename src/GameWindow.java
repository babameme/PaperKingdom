import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Rectangle;
import simulations.GameObject;
import simulations.SimulationFrame;

import java.awt.*;

public class GameWindow extends SimulationFrame{
    public GameWindow(){
        super("Paper Kingdom", 10.0);
    }

    @Override
    protected void initializeWorld() {
        Rectangle rectShape = new Rectangle(10.0, 25.0);
        GameObject rectangle = new GameObject();
        rectangle.addFixture(rectShape);
        rectangle.setMass(MassType.INFINITE);
        rectangle.translate(-35.0, 17.5);
        rectangle.setColor(Color.red);
        this.world.addBody(rectangle);
        System.out.println("Add");

        rectShape = new Rectangle(40.0, 15.0);
        rectangle = new GameObject();
        rectangle.addFixture(rectShape);
        rectangle.setMass(MassType.INFINITE);
        rectangle.translate(-10,22.5);
        rectangle.setColor(Color.red);
        this.world.addBody(rectangle);

        rectShape = new Rectangle(30, 25);
        rectangle = new GameObject();
        rectangle.addFixture(rectShape);
        rectangle.setMass(MassType.INFINITE);
        rectangle.translate(25, 17.5);
        rectangle.setColor(Color.red);
        this.world.addBody(rectangle);

        rectShape = new Rectangle(10, 10);
        rectangle = new GameObject();
        rectangle.addFixture(rectShape);
        rectangle.setMass(MassType.INFINITE);
        rectangle.translate(15, 0);
        rectangle.setColor(Color.red);
        this.world.addBody(rectangle);

        rectShape = new Rectangle(40, 25);
        rectangle = new GameObject();
        rectangle.addFixture(rectShape);
        rectangle.setMass(MassType.INFINITE);
        rectangle.translate(-20, -17.5);
        rectangle.setColor(Color.red);
        this.world.addBody(rectangle);

        rectShape = new Rectangle(20, 10);
        rectangle = new GameObject();
        rectangle.addFixture(rectShape);
        rectangle.setMass(MassType.INFINITE);
        rectangle.translate(-10, 0);
        rectangle.setColor(Color.red);
        this.world.addBody(rectangle);

        rectShape = new Rectangle(40, 15);
        rectangle = new GameObject();
        rectangle.addFixture(rectShape);
        rectangle.setMass(MassType.INFINITE);
        rectangle.translate(20, -22.5);
        rectangle.setColor(Color.red);
        this.world.addBody(rectangle);

        rectShape = new Rectangle(10, 10);
        rectangle = new GameObject();
        rectangle.addFixture(rectShape);
        rectangle.setMass(MassType.INFINITE);
        rectangle.translate(35, -10);
        rectangle.setColor(Color.red);
        this.world.addBody(rectangle);
    }
}