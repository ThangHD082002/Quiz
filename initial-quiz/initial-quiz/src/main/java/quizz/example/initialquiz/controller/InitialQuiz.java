package quizz.example.initialquiz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import quizz.example.initialquiz.request.Question;
import quizz.example.initialquiz.service.NotificationExcelService;
import quizz.example.initialquiz.service.UploadQuizExcelService;
import quizz.example.initialquiz.service.VerificationExcelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/api/v1/initial-quiz")
public class InitialQuiz {

    @Autowired
    private final VerificationExcelService verificationExcelService;

    private NotificationExcelService notificationExcelService = null;

    @Autowired
    public InitialQuiz(VerificationExcelService verificationExcelService, NotificationExcelService notificationExcelService) {
        this.verificationExcelService = verificationExcelService;
        this.notificationExcelService = notificationExcelService;
    }

    // @GetMapping("/call-quiz")
    // public ResponseEntity<String> callQuizService() {
    //     return uploadQuizExcelService.callQuizService();
    // }

    @GetMapping("/say")
    public String hello(){
        return "helo quiz";
    }

    // @PostMapping("/upload-excel")
    // public ResponseEntity<List<Question>> uploadExcelFile(@RequestParam("file") MultipartFile file) {
    //     return uploadQuizExcelService.uploadExcelFile(file);
    // }


    // @PostMapping("/upload-excel")
    // public ResponseEntity<List<Question>> uploadExcelFile(@RequestParam("file") MultipartFile file) {
    //     // ResponseEntity<String> excelFileVerification = verificationExcelService.checkExcelFile(file);
    //     // if (excelFileVerification.getStatusCode() == HttpStatus.OK) {
    //     //     notificationExcelService.sendSuccessNotification("File is Excel");
    //     //     ResponseEntity<String> excelDataVerification = verificationExcelService.checkExcelData(file);
    //     //     if (excelDataVerification.getStatusCode() == HttpStatus.OK) {
    //     //         notificationExcelService.sendSuccessNotification("Valid data in Excel file");
    //     //     } else {
    //     //         notificationExcelService.sendFailNotification("Invalid data in Excel file");
    //     //         return ResponseEntity.badRequest().build();
    //     //     }
    //     //     notificationExcelService.sendSuccessNotification("Create quiz success");
    //         return uploadQuizExcelService.uploadExcelFile(file);
    //     // } else {
    //     //     notificationExcelService.sendFailNotification("File is not an Excel file");
    //     //     return ResponseEntity.badRequest().build();
    //     // }
    // }




    // @PostMapping("/upload-excel")
    // public ResponseEntity<List<Question>> uploadExcelFile(@RequestParam("file") MultipartFile file) throws IOException {
    //     ResponseEntity<String> excelFileVerification = verificationExcelService.checkExcelFile(file);
    //     if (excelFileVerification.getStatusCode() == HttpStatus.OK) {
    //         notificationExcelService.sendSuccessNotification("File is Excel");
    //         ResponseEntity<String> excelDataVerification = verificationExcelService.checkExcelData(file);
    //         System.out.println("ERRORRRRRRRRR : " + excelDataVerification.getStatusCode());
    //         if (excelDataVerification.getStatusCode() == HttpStatus.OK) {
    //             notificationExcelService.sendSuccessNotification("Valid data in Excel file");
    //         } else {
    //             notificationExcelService.sendFailNotification("Invalid data in Excel file");
    //             return ResponseEntity.badRequest().build();
    //         }
    //         notificationExcelService.sendSuccessNotification("Create quiz success");
    //         return verificationExcelService.uploadExcelFile(file);
    //     } else {
    //         notificationExcelService.sendFailNotification("File is not an Excel file");
    //         return ResponseEntity.badRequest().build();
    //     }
    // }

    CompletableFuture<ResponseEntity<List<Question>>> resultFuture = new CompletableFuture<>();

    Runnable sendSuccessQuiz = () -> {
        notificationExcelService.sendSuccessNotification("Create quiz success");
    };

    Runnable sendSuccessIsExcel = () -> {
        notificationExcelService.sendSuccessNotification("File is Excel");
    };

    Runnable sendSuccessValidExcel = () -> {
        notificationExcelService.sendSuccessNotification("Valid data in Excel file");
    };



    Runnable sendFailQuiz = () -> {
        notificationExcelService.sendFailNotification("Create quiz fail");
    };

    Runnable sendFailIsExcel = () -> {
        notificationExcelService.sendFailNotification("File is not an Excel file");
    };

    Runnable sendFailValidExcel= () -> {
        notificationExcelService.sendFailNotification("Invalid data in Excel file");
    };

    @PostMapping("/upload-excel")
    public ResponseEntity<List<Question>> uploadExcelFile(@RequestParam("file") MultipartFile file) throws IOException, InterruptedException, ExecutionException {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    try {
        ResponseEntity<String> excelFileVerification = verificationExcelService.checkExcelFile(file);
        if (excelFileVerification.getStatusCode() == HttpStatus.OK) {
            executor.schedule(sendSuccessIsExcel, 3, TimeUnit.SECONDS);
            ResponseEntity<String> excelDataVerification = verificationExcelService.checkExcelData(file);
            System.out.println("ERRORRRRRRRRR : " + excelDataVerification.getStatusCode());
            if (excelDataVerification.getStatusCode() == HttpStatus.OK) {
                // notificationExcelService.sendSuccessNotification("Valid data in Excel file");
                // notificationExcelService.sendSuccessNotification("Create quiz success");
                executor.schedule(sendSuccessValidExcel, 6, TimeUnit.SECONDS);
                executor.schedule(sendSuccessQuiz, 9, TimeUnit.SECONDS);
                Runnable uploadTask = () -> {
                    try {
                        ResponseEntity<List<Question>> response = verificationExcelService.uploadExcelFile(file);
                        resultFuture.complete(response);
                    } catch (Exception e) {
                        resultFuture.completeExceptionally(e);
                    }
                };
                executor.schedule(uploadTask, 12, TimeUnit.SECONDS);

                // Chờ kết quả của CompletableFuture
                return resultFuture.get(); // Blocking call to get the result
                // return verificationExcelService.uploadExcelFile(file);
            } else {
                // notificationExcelService.sendFailNotification("Invalid data in Excel file");
                executor.schedule(sendFailValidExcel, 6, TimeUnit.SECONDS);
                executor.schedule(sendFailQuiz , 9, TimeUnit.SECONDS);
                return ResponseEntity.badRequest().build();
            }
        } else {
            // executor.schedule(sendFailIsExcel  , 5, TimeUnit.SECONDS);
            // executor.schedule(sendFailValidExcel, 6, TimeUnit.SECONDS);
            // executor.schedule(sendFailQuiz , 9, TimeUnit.SECONDS);
            return ResponseEntity.badRequest().build();
        }
    } catch (HttpClientErrorException.BadRequest ex) {
        // Handle bad request exception
        // notificationExcelService.sendFailNotification("Invalid data in Excel file");
        executor.schedule(sendFailValidExcel, 6, TimeUnit.SECONDS);
        executor.schedule(sendFailQuiz , 9, TimeUnit.SECONDS);
        return ResponseEntity.badRequest().build();
    }
}

}































// @PostMapping("/upload-excel")
// public ResponseEntity<List<Question>> uploadFile() {
//     String filePath = "D:\\KI2_NAM_4\\Phat_trien_PMHDV\\initial-quiz\\initial-quiz\\src\\main\\java\\quizz\\example\\initialquiz\\xlxs\\QuestionImport.xlsx"; // Đường dẫn file Excel cố định

//     try (InputStream inputStream = new FileInputStream(filePath)) {
//         // Tạo workbook từ file Excel
//         Workbook workbook = new XSSFWorkbook(inputStream);

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
//         return (ResponseEntity<List<Question>>) ResponseEntity.badRequest();
//     }
// }

