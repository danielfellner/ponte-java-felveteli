package hu.ponte.hr.services;

import hu.ponte.hr.config.AppConfig;
import hu.ponte.hr.interfaces.SignServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Service
public class SignService implements SignServiceInterface {

  private static Logger logger = LoggerFactory.getLogger(SignService.class);

  @Value("classpath:config/keys/key.private")
  Resource pk;

  @Autowired AppConfig appConf;

  @Override
  public String signBytes(byte[] messageBytes)
      throws NoSuchAlgorithmException, IOException, InvalidKeyException, InvalidKeySpecException,
          SignatureException {

    String signServiceFactory = appConf.getSignServiceFactoryKey();
    String signServiceSignature = appConf.getSignServiceSignature();

    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pk.getInputStream().readAllBytes());
    KeyFactory keyFactory = KeyFactory.getInstance(signServiceFactory);
    PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

    Signature signature = Signature.getInstance(signServiceSignature);
    signature.initSign(privateKey);
    signature.update(messageBytes);

    byte[] digitalSignature = signature.sign();
    String encodedSignedResult = Base64.getEncoder().encodeToString(digitalSignature);

    logger.info(
        "Message signature ready. KeyFactory: {} Signature algorithm: {} Encoded signature string length: {}",
        signServiceFactory,
        signServiceSignature,
        encodedSignedResult.length());

    return encodedSignedResult;
  }

}
