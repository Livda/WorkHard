public class Cat {
    private ISoundBehaviour sound;

    public void makeSound(){

    }

    public void setSoundBehaviour(ISoundBehaviour newSound){

    }
}

public interface ISoundBehaviour {
    public void makeSound();
}

public class MeowSound implements ISoundBehaviour {
    public void makeSound(){

    }
}

public class RoarSound implements ISoundBehaviour {
    public void makeSound(){

    }
}
