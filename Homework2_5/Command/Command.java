package Homework2_5.Command;

import Homework2_5.Library;

public abstract class Command {
    protected Library library;

    public Command(Library library)
    {
        this.library = library;
    }
    public abstract void doCmd(String[] words) ;
}
