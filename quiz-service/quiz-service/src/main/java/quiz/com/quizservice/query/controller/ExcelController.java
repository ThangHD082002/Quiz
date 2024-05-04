package quiz.com.quizservice.query.controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import quiz.com.quizservice.query.model.Question;


@RestController
@RequestMapping("/api/v1/quiz")
public class ExcelController {

    
    // @PostMapping("/upload-excel")
    // public ResponseEntity<List<Question>> uploadFile(@RequestParam("file") MultipartFile file) {
    //     try {
    //         // Tạo workbook từ file Excel
    //         Workbook workbook = new XSSFWorkbook(file.getInputStream());

    //         // Lấy sheet đầu tiên trong workbook
    //         Sheet sheet = workbook.getSheetAt(0);

    //         // Khởi tạo danh sách câu hỏi
    //         List<Question> questionList = new ArrayList<>();

    //         // Lặp qua từng dòng trong sheet, bắt đầu từ dòng thứ 1 (0-index)
    //         Iterator<Row> rowIterator = sheet.iterator();
    //         while (rowIterator.hasNext()) {
    //             Row row = rowIterator.next();

    //             // Bỏ qua dòng đầu tiên nếu muốn bỏ qua header
    //             if (row.getRowNum() == 0) {
    //                 continue;
    //             }

    //             // Đọc giá trị từ các ô
    //             String title = row.getCell(0).getStringCellValue();
    //             String option1 = row.getCell(1).getStringCellValue();
    //             String option2 = row.getCell(2).getStringCellValue();
    //             String option3 = row.getCell(3).getStringCellValue();
    //             String option4 = row.getCell(4).getStringCellValue();
    //             int rightAnswer = (int) row.getCell(5).getNumericCellValue();

    //             // Tạo câu hỏi mới và thêm vào danh sách
    //             Question question = new Question(title, option1, option2, option3, option4, rightAnswer);
    //             questionList.add(question);
    //         }

    //         // Xử lý các thao tác với danh sách câu hỏi ở đây, ví dụ:
    //         // - Lưu vào cơ sở dữ liệu
    //         // - In ra console
    //         // - Trả về danh sách câu hỏi qua API

    //         return ResponseEntity.ok(questionList);
    //     } catch (IOException e) {
    //         return ResponseEntity.badRequest().build();
    //     }
    // }
}
