package apply;

import implement.ArrayDeque;
import refactor.MyLinkedList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class MyQuackify implements StaticQuackify {

    private MyLinkedList<String> playlist;
    private Iterator<String> songIterator;
    private boolean isPlaying;
    private String currentlyPlaying;
    private Random random;

    private ArrayDeque<String> undoTypes;
    private ArrayDeque<String> undoSongs;
    private ArrayDeque<Integer> undoIndices;
    private ArrayDeque<String> redoTypes;
    private ArrayDeque<String> redoSongs;
    private ArrayDeque<Integer> redoIndices;

    public MyQuackify() {
        this.playlist = new MyLinkedList<>();
        this.songIterator = null;
        this.isPlaying = false;
        this.currentlyPlaying = null;
        this.random = null;
        this.undoTypes = new ArrayDeque<>();
        this.undoSongs = new ArrayDeque<>();
        this.undoIndices = new ArrayDeque<>();
        this.redoTypes = new ArrayDeque<>();
        this.redoSongs = new ArrayDeque<>();
        this.redoIndices = new ArrayDeque<>();
    }

    public MyQuackify(Random random) {
        this();
        this.random = random;
    }

    @Override
    public void play() {
        if (playlist.size() == 0) {
            throw new IllegalStateException("Cannot play - playlist is empty");
        }
        if (isPlaying) {
            throw new IllegalStateException("Music is already playing");
        }
        isPlaying = true;
        songIterator = playlist.iterator();
        currentlyPlaying = songIterator.next();
    }

    @Override
    public void stop() {
        if (playlist.size() == 0) {
            throw new IllegalStateException("Cannot stop - playlist is empty");
        }
        if (!isPlaying) {
            throw new IllegalStateException("Music is already stopped");
        }
        isPlaying = false;
        songIterator = null;
        currentlyPlaying = null;
    }

    @Override
    public boolean isPlaying() {
        return isPlaying;
    }

    @Override
    public MyLinkedList<String> getPlaylist() {
        return playlist;
    }

    @Override
    public int size() {
        return playlist.size();
    }

    @Override
    public String currentSong() {
        if (!isPlaying || currentlyPlaying == null) {
            throw new IllegalStateException("No song is currently playing");
        }
        return currentlyPlaying;
    }

    @Override
    public String nextSong() {
        if (!isPlaying || playlist.size() == 0) {
            throw new IllegalStateException("Cannot skip - no song is playing or playlist is empty");
        }
        currentlyPlaying = songIterator.next();
        return currentlyPlaying;
    }

    @Override
    public void addSong(String song) {
        if (isPlaying) {
            throw new IllegalStateException("Cannot add song while music is playing");
        }
        if (song == null || song.isEmpty()) {
            throw new IllegalArgumentException("Song name cannot be null or empty");
        }
        playlist.addFirst(song);
        undoTypes.addLast("ADD");
        undoSongs.addLast(song);
        undoIndices.addLast(-1);
        redoTypes = new ArrayDeque<>();
        redoSongs = new ArrayDeque<>();
        redoIndices = new ArrayDeque<>();
    }

    @Override
    public void removeSong(String song) {
        if (isPlaying) {
            throw new IllegalStateException("Cannot remove song while music is playing");
        }
        if (song == null || song.isEmpty()) {
            throw new IllegalArgumentException("Song name cannot be null or empty");
        }
        int index = -1;
        for (int i = 0; i < playlist.size(); i++) {
            if (playlist.get(i).equals(song)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("Song not found in playlist: " + song);
        }
        playlist.removeValue(song);
        undoTypes.addLast("REMOVE");
        undoSongs.addLast(song);
        undoIndices.addLast(index);
        redoTypes = new ArrayDeque<>();
        redoSongs = new ArrayDeque<>();
        redoIndices = new ArrayDeque<>();
    }

    @Override
    public void reverse() {
        if (playlist.size() == 0) {
            throw new IllegalStateException("Cannot reverse - playlist is empty");
        }
        if (isPlaying) {
            throw new IllegalStateException("Cannot reverse while music is playing");
        }
        playlist.reverse();
        undoTypes.addLast("REVERSE");
        undoSongs.addLast("");
        undoIndices.addLast(-1);
        redoTypes = new ArrayDeque<>();
        redoSongs = new ArrayDeque<>();
        redoIndices = new ArrayDeque<>();
    }

    @Override
    public String randomSong() {
        if (!isPlaying) {
            throw new IllegalStateException("Cannot play random song - music is stopped");
        }
        if (random == null) {
            random = new Random();
        }
        int randomIndex = random.nextInt(playlist.size());
        songIterator = playlist.iterator();
        for (int i = 0; i < randomIndex; i++) {
            songIterator.next();
        }
        currentlyPlaying = songIterator.next();
        return currentlyPlaying;
    }

    @Override
    public boolean isPalindrome() {
        if (playlist.size() == 0) {
            throw new IllegalStateException("Cannot check palindrome - playlist is empty");
        }
        ArrayDeque<String> deque = new ArrayDeque<>();
        for (int i = 0; i < playlist.size(); i++) {
            deque.addLast(playlist.get(i));
        }
        while (deque.size() > 1) {
            String front = deque.removeFirst();
            String back = deque.removeLast();
            if (!front.equals(back)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void undo() {
        if (isPlaying) {
            throw new IllegalStateException("Cannot undo while music is playing");
        }
        if (undoTypes.size() == 0) {
            throw new IllegalStateException("Nothing to undo");
        }
        String type = undoTypes.removeLast();
        String song = undoSongs.removeLast();
        Integer index = undoIndices.removeLast();

        if (type.equals("ADD")) {
            playlist.removeFirst();
        } else if (type.equals("REMOVE")) {
            if (index == 0) {
                playlist.addFirst(song);
            } else if (index == playlist.size()) {
                playlist.addLast(song);
            } else {
                playlist.addAtIndex(index, song);
            }
        } else if (type.equals("REVERSE")) {
            playlist.reverse();
        }

        redoTypes.addLast(type);
        redoSongs.addLast(song);
        redoIndices.addLast(index);
    }

    @Override
    public void redo() {
        if (isPlaying) {
            throw new IllegalStateException("Cannot redo while music is playing");
        }
        if (redoTypes.size() == 0) {
            throw new IllegalStateException("Nothing to redo");
        }
        String type = redoTypes.removeLast();
        String song = redoSongs.removeLast();
        Integer index = redoIndices.removeLast();

        if (type.equals("ADD")) {
            playlist.addFirst(song);
        } else if (type.equals("REMOVE")) {
            playlist.removeValue(song);
        } else if (type.equals("REVERSE")) {
            playlist.reverse();
        }

        undoTypes.addLast(type);
        undoSongs.addLast(song);
        undoIndices.addLast(index);
    }
}
