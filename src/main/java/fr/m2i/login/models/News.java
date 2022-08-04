package fr.m2i.login.models;

import javax.persistence.*;


@Entity
@Table(name="news")
@NamedQueries({
        @NamedQuery(name="selectAllNews", query="SELECT news1 FROM News news1 "),
        @NamedQuery(name="findNewsById", query="SELECT news1 FROM News news1 WHERE id = :id"),
        @NamedQuery(name="deleteNewsById",query ="DELETE FROM News WHERE id=:id"),
        @NamedQuery(name="updateNews",query="UPDATE News SET titre = :titre,text = :text")
})
@NamedNativeQueries({
        @NamedNativeQuery(name="selectLastXNews", query="SELECT * FROM News ORDER BY id DESC LIMIT :x",resultClass = News.class ),
        @NamedNativeQuery(name="addNews", query="INSERT INTO News(titre,text) VALUES(:titre,:text)",resultClass = News.class )

})

public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name="titre")
    private String titre;

    @Basic
    @Column(name="text")
    private String text;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public News() {
    }


    public News(String titre, String text) {
        this.titre = titre;
        this.text = text;
    }

    public News(Integer id, String titre, String text) {
        this.id = id;
        this.titre = titre;
        this.text = text;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
