package com.example.demo.repository;

import com.example.demo.entity.Track;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class AlbumRepository {
    private final String filePlace = "src/main/resources/static/music_album.json";
    private Gson gson;

    // Компаратор для сравнения треков по их идентификатору
    private Comparator<Track> idComparator = new Comparator<Track>() {
        @Override
        public int compare(Track o1, Track o2) {
            return o1.getId().compareTo(o2.getId());
        }
    };

    public AlbumRepository(Gson gson) {
        this.gson = gson;
    }

    // Метод для загрузки данных о треках из файла
    @Async
    private List<Track> loadData() {
        var list = new ArrayList<Track>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePlace));
            list = gson.fromJson(bufferedReader, new TypeToken<List<Track>>() {}.getType());
            bufferedReader.close();
            System.out.println("Album objects have been read from " + filePlace + " file.");
            list.sort(idComparator); // Сортировка списка альбома по их идентификатору
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Метод для записи данных о треках в файл
    @Async
    private void writeData(List<Track> trackList) {
        try {
            FileWriter fileWriter = new FileWriter(filePlace);
            gson.toJson(trackList, fileWriter);
            fileWriter.close();
            System.out.println("Album objects have been saved to " + filePlace + " file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для получения трека по его идентификатору
    @Async
    public Track getByID(Long id) {
        List<Track> trackList = loadData();
        var buff = trackList.stream().filter(x -> x.getId() == Integer.parseInt(id.toString())).findFirst().get();
        return buff;
    }

    // Метод для удаления трека по его идентификатору
    @Async
    public void delete(Long albumId) {
        List<Track> trackList = loadData();
        trackList.removeIf(x -> albumId - 1 >= 0 && x.getId() == albumId);
        writeData(trackList);
    }

    // Метод для сохранения трека
    @Async
    public void save(Track track) {
        List<Track> trackList = loadData();
        if (trackList.isEmpty()) {
            track.setId(Long.valueOf(1));
        } else {
            track.setId(Long.valueOf(trackList.get(trackList.size() - 1).getId() + 1));
        }
        trackList.add(track);
        writeData(trackList);
    }

    // Метод для получения всех треков
    @Async
    public List<Track> findAll() {
        List<Track> trackList = loadData();
        return trackList;
    }

    // Метод для обновления информации о треке
    @Async
    public Track update(Track track) {
        List<Track> trackList = loadData();
        if (!trackList.isEmpty() && track != null) {
            var id = 0;
            for (var item : trackList) {
                if (item.getId() == track.getId()) {
                    break;
                }
                id = id + 1;
            }
            trackList.set(id, track);
        }
        writeData(trackList);
        trackList = loadData();
        return trackList.stream().filter(x -> (x.getId()) == track.getId()).toList().get(0);
    }
}
