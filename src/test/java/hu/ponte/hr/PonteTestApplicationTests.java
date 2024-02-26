package hu.ponte.hr;

import hu.ponte.hr.config.AppConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PonteTestApplicationTests {
  @Autowired AppConfig appConfig;

  @Test
  public void contextLoads() {
    long maxUploadPhotoSize = 2097152;
    String signServiceFactoryKey = "RSA";
    String signServiceSignature = "SHA256withRSA";

    Assert.assertEquals(maxUploadPhotoSize, appConfig.getMaxUploadPhotoSize());
    Assert.assertEquals(signServiceFactoryKey, appConfig.getSignServiceFactoryKey());
    Assert.assertEquals(signServiceSignature, appConfig.getSignServiceSignature());
  }
}
