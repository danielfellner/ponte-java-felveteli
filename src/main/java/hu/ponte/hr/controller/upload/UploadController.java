package hu.ponte.hr.controller.upload;

import hu.ponte.hr.interfaces.ImageStoreServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;

@Component
@RequestMapping("api/file")
public class UploadController {

  private ImageStoreServiceInterface imageStoreService;

  @Autowired
  public UploadController(ImageStoreServiceInterface imageStoreService) {
    this.imageStoreService = imageStoreService;
  }

  @PostMapping("post")
  @ResponseBody
  public String handleFormUpload(@RequestParam("file") MultipartFile file)
      throws IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException,
          InvalidKeyException, SignatureException, NoSuchPaddingException, BadPaddingException,
          KeyStoreException, InvalidKeySpecException, IllegalBlockSizeException {
    this.imageStoreService.storeImage(file);
    return "ok";
  }
}
