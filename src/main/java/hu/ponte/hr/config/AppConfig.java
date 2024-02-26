package hu.ponte.hr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.Locale;

/** @author zoltan */
@Configuration
public class AppConfig {

  @Value("${maxUploadPhotoSize}")
  private Long maxUploadPhotoSize;

  @Value("${signServiceFactoryKey}")
  private String signServiceFactoryKey;

  @Value("${signServiceSignature}")
  private String signServiceSignature;

  @Bean
  public LocaleResolver localeResolver() {
    return new FixedLocaleResolver(Locale.ENGLISH);
  }

  @Bean(name = "multipartResolver")
  public CommonsMultipartResolver multipartResolver() {
    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    multipartResolver.setMaxUploadSize(getMaxUploadPhotoSize());
    return multipartResolver;
  }

  public long getMaxUploadPhotoSize() {
    return this.maxUploadPhotoSize;
  }

  public String getSignServiceFactoryKey() {
    return this.signServiceFactoryKey;
  }

  public String getSignServiceSignature() {
    return signServiceSignature;
  }

}
