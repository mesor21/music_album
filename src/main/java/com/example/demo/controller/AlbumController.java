package com.example.demo.controller;

import com.example.demo.entity.Track;
import com.example.demo.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    // Метод для получения списка всех треков и вывода на главную страницу
    @Async
    @GetMapping("/")
    public String getAll(Model model) {
        model.addAttribute("list", albumService.getAllList());
        return "main";
    }

    // Метод для создания нового трека и перенаправления на главную страницу
    @Async
    @GetMapping("/new")
    public String newPatient() {
        albumService.saveNew();
        return "redirect:/";
    }

    // Метод для получения информации о треке для редактирования
    @Async
    @GetMapping("edit/{id}")
    public String getForEdit(@PathVariable("id") String id, Model model) {
        model.addAttribute("track", albumService.getById(id));
        return "edit";
    }

    // Метод для обновления информации о треке
    @Async
    @PostMapping("edit/{id}")
    public String postDataForEdit(@PathVariable("id") String id,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "albumName", required = false) String albumName,
                                  @RequestParam(value = "artist", required = false) String artist,
                                  @RequestParam(value = "year", required = false) String year,
                                  @RequestParam(value = "genre", required = false) String genre,
                                  @RequestParam(value = "duration", required = false) String duration,
                                  @RequestParam(value = "nubmerOfPleys", required = false) Integer nubmerOfPleys) {
        Track track = new Track(Long.parseLong(id), name, albumName, artist, year, genre, duration, nubmerOfPleys);
        albumService.update(track);
        return "redirect:/";
    }

    // Метод для удаления трека на основе его идентификатора
    @Async
    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") String id) {
        albumService.delete(id);
        return "redirect:/";
    }
    @Async
    @GetMapping("/listen")
    public String listenPage(Model model){
        model.addAttribute("list", albumService.getAllList());
        return "listen";
    }
    @Async
    @GetMapping("listen/{id}")
    public String listen(@PathVariable("id") String id){
        albumService.listen(id);
        return "redirect:/listen";
    }
}
