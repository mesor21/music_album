package com.example.demo.service;

import com.example.demo.entity.Track;
import com.example.demo.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    // Метод для получения списка всех треков
    @Async
    public List<Track> getAllList() {
        return albumRepository.findAll();
    }

    // Метод для удаления трека по его идентификатору
    @Async
    public void delete(String id) {
        albumRepository.delete(Long.valueOf(id));
    }

    // Метод для сохранения нового трека
    @Async
    public void saveNew() {
        Track track = new Track();
        albumRepository.save(track);
    }
    @Async
    public Track getById(String id) {
        return albumRepository.getByID(Long.valueOf(id));
    }

    // Метод для обновления информации о треке
    @Async
    public Track update(Track track) {
        return albumRepository.update(track);
    }
    @Async
    public void listen(String id){
        Track track=albumRepository.getByID(Long.valueOf(id));
        track.setNubmerOfPleys(track.getNubmerOfPleys()+1);
        albumRepository.update(track);
    }
}
