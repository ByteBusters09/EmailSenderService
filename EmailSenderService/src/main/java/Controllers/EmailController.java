package Controllers;
import com.app.EmailSenderService.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ui.CustomResponse;
import java.io.File;

@RestController
@RequestMapping("api/v1/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping ("/send")
    public ResponseEntity<?> sendEmail(@RequestBody String to, String subject, String message) {
        emailService.sendEmail (to, subject, message);
        return ResponseEntity.ok (CustomResponse.builder().message ("Email sent successfully !!").status (HttpStatus.OK).success (true).build ());
    }

    @PostMapping ("/send/")
    public ResponseEntity<?> sendEmail(@RequestBody String[] to, String subject, String message) {
        emailService.sendEmail (to, subject, message);
        return ResponseEntity.ok (CustomResponse.builder().message ("Email sent successfully !!").status (HttpStatus.OK).success (true).build ());
    }

    @PostMapping ("/send/html/context")
    public ResponseEntity<CustomResponse> sendEmailWithHtmlContext(String to, String subject, String htmlContent) {
        emailService.sendEmailWithHtmlContext (to, subject, htmlContent);
        return ResponseEntity.ok (CustomResponse.builder().message ("Email sent successfully !!").status (HttpStatus.OK).success (true).build ());
    }

    @PostMapping ("/send/file/context")
    public ResponseEntity<CustomResponse> sendEmailWithFile(String to, String subject, String message, File file) {
        emailService.sendEmailWithFile (to, subject, message, file);
        return ResponseEntity.ok (CustomResponse.builder().message ("Email sent successfully !!").status (HttpStatus.OK).success (true).build ());
    }
}
