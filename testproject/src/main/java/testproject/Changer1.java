package testproject;

public class Changer1 {
    public void doChange(TargetEntity entity, int t) {
        entity.setTargetVar(42);
        System.out.println(t);
    }
}
