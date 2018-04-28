package org.softuni.nuggets.cloud.controllers;

import org.softuni.nuggets.cloud.cloud.CloudImageExtractor;
import org.softuni.nuggets.cloud.cloud.CloudImageUploader;
import org.softuni.nuggets.entities.Image;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
public class CloudController {
    private final CloudImageExtractor cloudImageExtractor;

    private final CloudImageUploader cloudImageUploader;

    public CloudController(CloudImageExtractor cloudImageExtractor, CloudImageUploader cloudImageUploader) {
        this.cloudImageExtractor = cloudImageExtractor;
        this.cloudImageUploader = cloudImageUploader;
    }


    @GetMapping("/images")
    public ModelAndView images() {
        ModelAndView modelAndView = new ModelAndView();

//        List<Image> images = new ArrayList<Image>();

//        try {
//            images = this.cloudImageExtractor.getAllImages();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        modelAndView.setViewName("images");
//        modelAndView.addObject("imageData", images);

        return modelAndView;
    }

    @PostMapping("/images")
    public ModelAndView images(@RequestParam("file")MultipartFile file,Principal principal) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            this.cloudImageUploader.uploadFile(file,principal.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        modelAndView.setViewName("images");

        return modelAndView;
    }
}
