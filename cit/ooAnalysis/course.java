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

//Delegation is used when we want to use SOME of the methods of a class.

//See law of Demeter -> Only talk to your immediate friends
/*
1) can call his own methods
2) can call methods of all object we instantiate
3) can call methods of attributes
4) can call methods of parameters
*/

//A class should know few classes
