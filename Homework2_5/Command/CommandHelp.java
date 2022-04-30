package Homework2_5.Command;

import Homework2_5.Library;

public class CommandHelp extends Command {
    public CommandHelp(Library library) {super(library);}

    @Override
    public void doCmd(String[] words) {
        System.out.println("你可以进行的操作有：");
        System.out.println();
        System.out.println("“添加图书 (书名)”");
        System.out.println("“移除图书 (馆藏编号)”");
        System.out.println("“查找图书 (书名)”");
        System.out.println();
        System.out.println("”登记读者 (身份|选填:’学生/‘职工’) (姓名) (学号/工号)“");
        System.out.println("“移除读者 (学号/工号)”");
        System.out.println("“查找读者 (学号/工号)”");
        System.out.println();
        System.out.println("“借书 (书名)");
        System.out.println("“还书 (馆藏编号)”");
        System.out.println();
        System.out.println("注意：只有登记过的读者才可以借书哦！");
    }
}
