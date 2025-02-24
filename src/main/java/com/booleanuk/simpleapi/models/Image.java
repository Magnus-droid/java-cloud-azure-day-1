package com.booleanuk.simpleapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Types;
import java.util.Arrays;


@Getter
@Setter
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String url;

    private int height;

    private int width;

    @JsonIgnore
    private byte[] pixels;

    //this constructor allows an image to be read and its corresponding pixel array to be initialized
    public Image(int id, String url) {
        this.id = id;
        this.url = url;
        //try to read image from the provided URL
        try {
            this.pixels = ((DataBufferByte) ImageIO.read((new URI(this.url).toURL())).getRaster().getDataBuffer()).getData();
            this.height = ImageIO.read((new URI(this.url).toURL())).getHeight();
            this.width = ImageIO.read((new URI(this.url).toURL())).getWidth();

        } catch (IOException | URISyntaxException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
