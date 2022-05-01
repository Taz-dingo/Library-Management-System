// package Homework2_5.Command;

// import Homework2_5.Library;

// public class CommandRemoveBooks extends Command{
//     public CommandRemoveBooks(Library library) {super(library);}

//     @Override
//     public void doCmd(String[] words) {
//         if (words.length >= 2)
//         {
//             if(library.removeBook(words))
//                 System.out.println("已成功移除！");
//             else
//                 System.out.println("没有这本书！");
//         }else
//             System.out.println("没听懂，请检查指令是否输入正确！");
//     }
// }
