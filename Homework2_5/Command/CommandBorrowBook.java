// package Homework2_5.Command;

// import Homework2_5.BookRecorder;
// import Homework2_5.Library;
// import Homework2_5.Readers.Readers;

// public class CommandBorrowBook extends Command{
//     public CommandBorrowBook(Library library) {
//         super(library);
//     }

//     @Override
//     public void doCmd(String[] words)
//     {
//         if(words.length >= 3) {     //如果指令长度>=3
//             String[] changedWords = new String[words.length];
//             for (int i=0;i<words.length;i++)
//             {
//                 changedWords[i] = words[i];
//             }
//             changedWords[1] = changedWords[2];
//             Readers reader = library.searchReader(changedWords);
//             if (reader != null)     //如果在馆内找到<指定读者>的登记记录
//             {
//                 BookRecorder recorder = library.searchBook(words);
//                 if (reader != null) {
//                     if (recorder != null) {     //如果在馆内找到了<指定图书>
//                         if (recorder.getCondition().equals("在馆")) {
//                             recorder.borrowBook(changedWords);
//                             System.out.println("借书读者：" + reader.getName() + " 借书时间：" + recorder.getLendDate());
//                             System.out.println("借书成功！本书馆藏编号为：" + recorder.getBook().getIndexCode());
//                             System.out.println("还书时请使用馆藏编号~");

//                         } else if (recorder.getCondition().equals("已借出")) {
//                             System.out.println("馆里的这种书都被借走了噢");
//                         }
//                     } else //如果在馆内找不到<指定图书>
//                     {
//                         System.out.println("咱们这没这本书！");
//                     }
//                 }
//             } else {
//                 System.out.println("该读者尚未登记，无法借书！");
//             }
//         }
//         else{
//             System.out.println("没听懂，请检查指令是否输入正确！");
//         }
//     }
// }
