package hu.ponte.hr.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "uploaded_images")
public class ImageEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;

  @Column private String name;

  @Column private String mimeType;

  @Column private long size;

  @Column(name = "digitalSign", columnDefinition = "TEXT")
  private String digitalSign;

  @Lob
  @Column(name = "photo", columnDefinition = "BLOB")
  private byte[] photo;
}
