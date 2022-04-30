package Homework2_5.Command;

import Homework2_5.Library;

public class CommandRegister extends Command {
    public CommandRegister(Library library) {super(library);}

    @Override
    public void doCmd(String[] words)
    {
        if(words.length >=4 ){
            library.register(words);
        }
        else
            System.out.println("没听懂，请检查指令是否输入正确！");
    }
}
