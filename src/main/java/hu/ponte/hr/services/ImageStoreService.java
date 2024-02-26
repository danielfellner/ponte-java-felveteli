package hu.ponte.hr.services;

import hu.ponte.hr.models.ImageEntity;
import hu.ponte.hr.repository.ImageRepository;
import hu.ponte.hr.interfaces.ImageStoreServiceInterface;
import hu.ponte.hr.interfaces.SignServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Service
public class ImageStoreService implements ImageStoreServiceInterface {

  private static Logger logger = LoggerFactory.getLogger(ImageStoreService.class);

  @Autowired private SignServiceInterface signService;

  @Autowired private ImageRepository imageRepository;

  @Override
  public List<ImageEntity> listImages() {
    List<ImageEntity> imageList = imageRepository.findAll();
    logger.info("listImages called. There are {} image in the image repository.", imageList.size());
    return imageList;
  }

  @Override
  public ImageEntity getImage(long id) {
    ImageEntity image = imageRepository.getOne(id);
      logger.info(
          "getImage called! ID: {} Name: {} Size: {} byte", id, image.getName(), image.getSize());
    return image;
  }

  @Override
  public void storeImage(MultipartFile image)
      throws IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException,
          InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException,
          SignatureException, KeyStoreException, IllegalBlockSizeException {

    byte[] imageBytes = image.getInputStream().readAllBytes();
    String signedBytes = signService.signBytes(imageBytes);

    ImageEntity imageEntity = new ImageEntity();
    imageEntity.setName(image.getOriginalFilename());
    imageEntity.setPhoto(imageBytes);
    imageEntity.setMimeType(image.getContentType());
    imageEntity.setSize(image.getSize());
    imageEntity.setDigitalSign(signedBytes);

    imageRepository.save(imageEntity);
    imageRepository.flush();

    logger.info(
        "Image stored propedly. Name: {} File size: {} byte",
        image.getOriginalFilename(),
        image.getSize());
  }
}
