package com.booleanuk.simpleapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.imageio.ImageIO;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


@Getter
@Setter
@Entity
@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String url;

    private int height;

    private int width;

    @JsonIgnore
    private byte[] pixels;

    public Image(int height, int width, byte[] pixels) {
        this.height = height;
        this.width = width;
        this.pixels = pixels;
    }


    public Image(@JsonProperty("id") int id, @JsonProperty("url") String url) {
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
