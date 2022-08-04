package entities;

import lombok.*;


import javax.persistence.*;
import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int id;

    @Column(name = "authorName")
    private String authorName;



    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Post post;

    public Comment(String authorName, Post post) {
        this.authorName = authorName;
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return getId() == comment.getId() && Objects.equals(getAuthorName(), comment.getAuthorName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthorName());
    }

}
