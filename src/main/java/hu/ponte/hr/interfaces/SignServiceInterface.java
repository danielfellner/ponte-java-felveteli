package hu.ponte.hr.interfaces;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;

public interface SignServiceInterface {
  public String signBytes(byte[] originalBytes)
      throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, KeyStoreException,
          InvalidKeyException, UnrecoverableKeyException, CertificateException, BadPaddingException,
          IllegalBlockSizeException, InvalidKeySpecException, SignatureException;
}
