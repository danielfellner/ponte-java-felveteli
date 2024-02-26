package hu.ponte.hr.controller;

import hu.ponte.hr.interfaces.ImageStoreServiceInterface;
import hu.ponte.hr.models.ImageEntity;
import hu.ponte.hr.services.ImageStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@RestController()
@RequestMapping("api/images")
public class ImagesController {

  @Autowired private ImageStoreServiceInterface imageStoreService;

  @Autowired private ImageStoreService imageStore;

  @Autowired private ServletContext servletContext;

  @GetMapping("meta")
  public List<ImageMeta> listImages() {
    List<ImageMeta> results = new LinkedList<>();

    imageStoreService
        .listImages()
        .forEach(
            item ->
              results.add(
                  ImageMeta.builder()
                      .id("" + item.getId())
                      .name(item.getName())
                      .mimeType(item.getMimeType())
                      .size(item.getSize())
                      .digitalSign(item.getDigitalSign())
                      .build())
            );

    return results;
  }

  @GetMapping("preview/{id}")
  public void getImage(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
    ImageEntity image = imageStore.getImage(id);
      response.getOutputStream().write(image.getPhoto());
      response.getOutputStream().close();
  }
}
