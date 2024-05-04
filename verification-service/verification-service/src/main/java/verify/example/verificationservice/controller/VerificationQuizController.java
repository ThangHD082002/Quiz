package verify.example.verificationservice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import verify.example.verificationservice.model.Question;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/verification")
public class VerificationQuizController {

    @GetMapping("/helo")
    public String getMethodName() {
        return "helo verify";
    }

    @PostMapping("/check-excel")
    public ResponseEntity<String> checkExcelFile(@RequestParam("file") MultipartFile file) {
        // Kiểm tra xem file có phải là file Excel hay không
        if (isExcelFile(file)) {
            return ResponseEntity.ok("Excel file format is valid");
        } else {
            return ResponseEntity.badRequest().body("File is not an Excel file");
        }
    }

    @PostMapping("/check-excel-data")
    public ResponseEntity<String> checkExcelData(@RequestParam("file") MultipartFile file) {
        // Kiểm tra dữ liệu trong file Excel có hợp lệ hay không
        if (isExcelDataValid(file)) {
            return ResponseEntity.ok("Excel data is valid");
        } else {
            return ResponseEntity.badRequest().body("Invalid data in Excel file");
        }
    }

    @PostMapping("/upload-excel")
    public ResponseEntity<List<Question>> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Tạo workbook từ file Excel
            Workbook workbook = new XSSFWorkbook(file.getInputStream());

            // Lấy sheet đầu tiên trong workbook
            Sheet sheet = workbook.getSheetAt(0);

            // Khởi tạo danh sách câu hỏi
            List<Question> questionList = new ArrayList<>();

            // Lặp qua từng dòng trong sheet, bắt đầu từ dòng thứ 1 (0-index)
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                // Bỏ qua dòng đầu tiên nếu muốn bỏ qua header
                if (row.getRowNum() == 0) {
                    continue;
                }

                // Đọc giá trị từ các ô
                String title = row.getCell(0).getStringCellValue();
                String option1 = row.getCell(1).getStringCellValue();
                String option2 = row.getCell(2).getStringCellValue();
                String option3 = row.getCell(3).getStringCellValue();
                String option4 = row.getCell(4).getStringCellValue();
                int rightAnswer = (int) row.getCell(5).getNumericCellValue();

                // Tạo câu hỏi mới và thêm vào danh sách
                Question question = new Question(title, option1, option2, option3, option4, rightAnswer);
                questionList.add(question);
            }

            // Xử lý các thao tác với danh sách câu hỏi ở đây, ví dụ:
            // - Lưu vào cơ sở dữ liệu
            // - In ra console
            // - Trả về danh sách câu hỏi qua API

            return ResponseEntity.ok(questionList);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private boolean isExcelFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName != null && !fileName.isEmpty()) {
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (fileExtension.equalsIgnoreCase("xls") || fileExtension.equalsIgnoreCase("xlsx")) {
                return true;
            }
        }
        return false;
    }

    private boolean isExcelDataValid(MultipartFile file) {
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên, bạn có thể thay đổi nếu cần

            // Dòng đầu tiên thường là dòng chứa tiêu đề của các cột
            Row headerRow = sheet.getRow(0);
            int totalColumns = headerRow.getLastCellNum();

            // Duyệt qua từng dòng từ dòng thứ 2 trở đi để kiểm tra dữ liệu
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row != null) { // Kiểm tra xem dòng có rỗng không
                    for (int colNum = 0; colNum < totalColumns; colNum++) {
                        Cell cell = row.getCell(colNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        // Kiểm tra nếu ô trong cột là rỗng
                        if (cell.getCellType() == CellType.BLANK) {
                            // Nếu có ô nào rỗng, có thể trả về false ngay tại đây
                            return false;
                        }
                    }
                } else {
                    // Nếu dòng là null, có thể dừng kiểm tra tại đây, vì không cần kiểm tra các
                    // dòng tiếp theo
                    return false;
                }
            }
            return true; // Nếu không có cột nào rỗng, trả về true
        } catch (IOException e) {
            e.printStackTrace();
            // Xử lý nếu có lỗi khi đọc file Excel
            return false;
        }
    }
}