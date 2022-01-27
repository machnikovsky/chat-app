package pl.chatty.javabackend.domains.image.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.chatty.javabackend.domains.image.service.ImageService;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ImageController {

    private final ImageService imageService;

}
