package com.lqb.hauwei;

import java.util.*;

public class MusicLike {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        MusicLike main = new MusicLike();
        while (in.hasNextInt()) {
            String op = in.next();
            String songName = in.next();
            if (ADD.equals(op)) {
                String type = in.next();
                Song song = new Song(songName, type);
                main.addSong(song);
            } else {
                main.opSong(songName, op);
            }
        }
    }

    public static final String TYPE_UNKNOWN = "UnknownStyle";
    public static final String PLAY = "P";
    public static final String ADD = "I";
    public static final String BLOCK = "B";


    Map<String, Song> name2Song = new HashMap<>();

    Map<String, List<Song>> type2Song = new HashMap<>();

    public void addSong(Song song) {
        List<Song> songs = type2Song.computeIfAbsent(song.type, key -> new ArrayList<>());
        songs.add(song);
        name2Song.put(song.name, song);
    }

    public void opSong(String songName, String op) {
        Song song = name2Song.get(songName);
        List<Song> typeSongs = type2Song.get(song.type);
        if (PLAY.equals(op)) {
            song.like += 3;
            if (!TYPE_UNKNOWN.equals(song.type)) {
                for (Song typeSong : typeSongs) {
                    typeSong.like += 1;
                }
            }
        } else if (BLOCK.equals(op)) {
            song.like -= 2;
            if (!TYPE_UNKNOWN.equals(song.type)) {
                for (Song typeSong : typeSongs) {
                    typeSong.like -= 1;
                }
            }
        }
    }

    public List<Song> getSongs() {
        List<Song> allSongs = new ArrayList<>(name2Song.values());
        Comparator<Song> comparator = (o1, o2) -> {
            if (o1.like != o2.like) {
                return o1.like - o2.like;
            } else {
                return o1.name.compareTo(o2.name);
            }
        };
        Collections.sort(allSongs, comparator);
        return allSongs;
    }

    public static class Song {
        String name;
        String type;
        int like;

        public Song(String name, String type) {
            this.name = name;
            this.type = type;
            like = 0;
        }
    }
}
