// package Homework2_5.Command;

// import Homework2_5.BookRecorder;
// import Homework2_5.Library;

// public class CommandSearchBook extends Command{
//     public CommandSearchBook(Library library) {
//         super(library);
//     }

//     @Override
//     public void doCmd(String[] words)
//     {
//         if(words.length>=2) {
//             BookRecorder recorder = library.searchBook(words);
//             if (recorder != null) {
//                 System.out.println("查找完毕！");
//             } else System.out.println("找不到这本书！");    //没找到就输出"查无此人"
//         }else
//             System.out.println("没听懂，请检查指令是否输入正确！");
//     }
// }
