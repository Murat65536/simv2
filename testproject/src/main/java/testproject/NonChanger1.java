package testproject;

public class NonChanger1 {
    public void doRead(TargetEntity entity) {
        int x = entity.targetVar;
        System.out.println(x);
    }
}
