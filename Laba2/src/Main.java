public class Main {
    public static void main(String[] args) {

        Audiobook audiobook = new Audiobook("Мастер и Маргарита", "Булгаков", 1967, 960, "Хабенский");
        Movie movie = new Movie("Брат", "Балабанов", 1997, "Балабанов", 96);
        Musical musical = new Musical("Анна Каренина", "Толстой", 2016, "Чевик", "Игнатьев");

        MP3Audiobook mp3Book = new MP3Audiobook("Мастер и Маргарита", "Булгаков", 1967, 720, "Иванов", 320);
        FLACAudiobook flacBook = new FLACAudiobook("Война и мир", "Толстой", 1869, 2400, "Петров", "Hi-Res");

        System.out.println("\nИнформация о книгах\n");
        System.out.println(audiobook.getInfo());
        System.out.println(movie.getInfo());
        System.out.println(musical.getInfo());
        System.out.println(mp3Book.getInfo());
        System.out.println(flacBook.getInfo());

        System.out.println("\nДемонстрация методов (первый уровень)\n");
        audiobook.display();
        audiobook.display("Шедевр русской литературы");

        movie.display();
        movie.watchTrailer();

        musical.display();
        musical.listenAria();

        System.out.println("\nДемонстрация методов (второй уровень)\n");
        mp3Book.display();
        mp3Book.playSample();

        flacBook.display();
        flacBook.showQuality();

        System.out.println("\nСчетчик");
        System.out.println("Всего создано книг: " + Book.getCount());

        System.out.println("\nДемонстрация конструкторов по умолчанию");
        Audiobook defaultAudio = new Audiobook();
        MP3Audiobook defaultMP3 = new MP3Audiobook();

        System.out.println(defaultAudio.getInfo());
        System.out.println(defaultMP3.getInfo());
    }
}

abstract class Book {
    private String title;
    private String author;
    private int year;
    private static int totalBooks = 0;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        totalBooks++;
    }

    public Book() {
        this("Неизвестно", "Неизвестно", 0);
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public static int getCount() { return totalBooks; }

    public abstract void display();

    public String getInfo() {
        String yearStr = (year == 0) ? "не указан" : String.valueOf(year);
        return title + ", " + author + ", " + yearStr;
    }
}

abstract class AudioFormat extends Book {
    protected int duration;

    public AudioFormat(String title, String author, int year, int duration) {
        super(title, author, year);
        this.duration = duration;
    }

    public AudioFormat() {
        super();
        this.duration = 0;
    }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    @Override
    public String getInfo() {
        return super.getInfo() + ", длительность: " + duration + " мин";
    }
}

class Audiobook extends AudioFormat {
    private String narrator;

    public Audiobook(String title, String author, int year, int duration, String narrator) {
        super(title, author, year, duration);
        this.narrator = narrator;
    }

    public Audiobook() {
        super();
        this.narrator = "Неизвестен";
    }

    public String getNarrator() { return narrator; }
    public void setNarrator(String narrator) { this.narrator = narrator; }

    @Override
    public void display() {
        System.out.println("Аудиокнига: " + getTitle() + " читает " + narrator);
    }

    public void display(String comment) {
        display();
        System.out.println("  Комментарий: " + comment);
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", чтец: " + narrator;
    }
}

class MP3Audiobook extends Audiobook {
    private int bitrate;

    public MP3Audiobook(String title, String author, int year, int duration, String narrator, int bitrate) {
        super(title, author, year, duration, narrator);
        this.bitrate = bitrate;
    }

    public MP3Audiobook() {
        super();
        this.bitrate = 128;
    }

    public int getBitrate() { return bitrate; }
    public void setBitrate(int bitrate) { this.bitrate = bitrate; }

    @Override
    public void display() {
        System.out.println("MP3 аудиокнига: " + getTitle() + ", битрейт: " + bitrate + " kbps");
    }

    public void playSample() {
        System.out.println("  Воспроизведение фрагмента: " + getTitle());
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", MP3, битрейт: " + bitrate + " kbps";
    }
}

class FLACAudiobook extends Audiobook {
    private String quality;

    public FLACAudiobook(String title, String author, int year, int duration, String narrator, String quality) {
        super(title, author, year, duration, narrator);
        this.quality = quality;
    }

    public FLACAudiobook() {
        super();
        this.quality = "CD Quality";
    }

    public String getQuality() { return quality; }
    public void setQuality(String quality) { this.quality = quality; }

    @Override
    public void display() {
        System.out.println("FLAC аудиокнига: " + getTitle() + ", качество: " + quality);
    }

    public void showQuality() {
        System.out.println("  Качество звука: " + quality);
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", FLAC, качество: " + quality;
    }
}

abstract class VisualFormat extends Book {
    protected String director;

    public VisualFormat(String title, String author, int year, String director) {
        super(title, author, year);
        this.director = director;
    }

    public VisualFormat() {
        super();
        this.director = "Неизвестен";
    }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    @Override
    public String getInfo() {
        return super.getInfo() + ", режиссер: " + director;
    }
}

class Movie extends VisualFormat {
    private int duration;

    public Movie(String title, String author, int year, String director, int duration) {
        super(title, author, year, director);
        this.duration = duration;
    }

    public Movie() {
        super();
        this.duration = 0;
    }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    @Override
    public void display() {
        System.out.println("Фильм: " + getTitle() + ", режиссер: " + getDirector() + ", " + duration + " мин");
    }

    public void watchTrailer() {
        System.out.println("  Смотреть трейлер: " + getTitle());
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", длительность: " + duration + " мин";
    }
}

class Musical extends VisualFormat {
    private String composer;

    public Musical(String title, String author, int year, String director, String composer) {
        super(title, author, year, director);
        this.composer = composer;
    }

    public Musical() {
        super();
        this.composer = "Неизвестен";
    }

    public String getComposer() { return composer; }
    public void setComposer(String composer) { this.composer = composer; }

    @Override
    public void display() {
        System.out.println("Мюзикл: " + getTitle() + ", режиссер: " + getDirector() + ", музыка: " + composer);
    }

    public void listenAria() {
        System.out.println("  Слушать арию из мюзикла: " + getTitle());
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", композитор: " + composer;
    }
}