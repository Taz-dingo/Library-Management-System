// package Homework2_5.Command;

// import Homework2_5.Library;

// public class CommandRemoveReaders extends Command {
//     public CommandRemoveReaders(Library library) {super(library);}

//     @Override
//     public void doCmd(String[] words) {
//         if (words.length >= 2)
//         {
//             if(library.removeReader(words))
//                 System.out.println("已成功移除！");
//             else
//                 System.out.println("查无此人！");
//         }else
//             System.out.println("没听懂，请检查指令是否输入正确！");
//     }
// }
