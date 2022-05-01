// package Homework2_5.Command;

// import Homework2_5.BookRecorder;
// import Homework2_5.Library;

// public class CommandReturnBook extends Command{
//     public CommandReturnBook(Library library) {
//         super(library);
//     }

//     @Override
//     public void doCmd(String[] words) {
//         if(words.length>=2) {
//             BookRecorder recorder = library.searchBookIndex(words);
//             if(words.length>=2) {
//                 if (recorder != null) {
//                     recorder.returnBook(words);
//                     System.out.println("借书读者：" + recorder.getReader().getName() + " 借书时间：" + recorder.getLendDate());
//                     System.out.println("还书成功！还书时间：" + recorder.getReturnDate());
//                 }
//                 else{
//                     System.out.println("馆内没有这个编号的书！");
//                 }
//             }
//         }
//         else {
//             System.out.println("没听懂，请检查指令是否输入正确！");
//         }
//     }
// }
