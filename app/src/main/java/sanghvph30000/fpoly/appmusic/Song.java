package sanghvph30000.fpoly.appmusic;

public class Song {
    int id;
    String ten;
    String link;

    public Song(String ten, String link) {
        this.ten = ten;
        this.link = link;
    }

    public Song(int sayYes) {
    }

    public Song() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
