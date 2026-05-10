package testproject;

public class MainEntry {
    public void entry() {
        TargetEntity entity = new TargetEntity();
        
        Changer1 c1 = new Changer1();
        c1.doChange(entity);
        
        Changer2 c2 = new Changer2();
        c2.doChange(entity);
        
        NonChanger1 nc1 = new NonChanger1();
        nc1.doRead(entity);
        
        NonChanger2 nc2 = new NonChanger2();
        nc2.doSomethingElse(entity);
    }
}
