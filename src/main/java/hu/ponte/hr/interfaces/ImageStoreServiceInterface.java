package hu.ponte.hr.interfaces;

import hu.ponte.hr.models.ImageEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface ImageStoreServiceInterface {
  public List<ImageEntity> listImages();

  public ImageEntity getImage(long id);

  public void storeImage(MultipartFile image) throws IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, SignatureException, KeyStoreException, IllegalBlockSizeException;
}
