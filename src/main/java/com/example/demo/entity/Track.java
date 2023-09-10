package com.example.demo.entity;

public class Track {
    private Long id; // Уникальный идентификатор трека
    private String name; // Название
    private String albumName; // Альбом
    private String artist; // Исполнитель
    private String year; // Год выхода
    private String genre; // Жанр
    private String duration; // Длительность
    private int nubmerOfPleys; // Количество прослушиваний

    public Track() {}

    public Track(Long id, String name, String albumName, String artist, String year, String genre, String duration, int nubmerOfPleys) {
        this.id = id;
        this.name = name;
        this.albumName = albumName;
        this.artist = artist;
        this.year = year;
        this.genre = genre;
        this.duration = duration;
        this.nubmerOfPleys = nubmerOfPleys;
    }

    // Геттеры и сеттеры для всех полей

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getNubmerOfPleys() {
        return nubmerOfPleys;
    }

    public void setNubmerOfPleys(int nubmerOfPleys) {
        this.nubmerOfPleys = nubmerOfPleys;
    }
}
