import entities.Comment;
import entities.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        Post post1 = new Post("post1");
        Post post2 = new Post("post2");

        Comment comment1 = new Comment("comment1", post1);
        Comment comment2 = new Comment("comment2", post1);
        Comment comment3 = new Comment("comment3", post2);
        Comment comment4 = new Comment("comment4", post2);

        Set<Comment> set1 = new HashSet<>(Arrays.asList(comment1, comment2));
        post1.setComment(set1);

        Set<Comment> set2 = new HashSet<>(Arrays.asList(comment3,comment4));
        post2.setComment(set2);

        session.save(post1);
        session.save(post2);

        transaction.commit();
        session.close();
    }
     private static Session getSession(){
         Configuration configuration = new Configuration();
         configuration.configure("hibernate.cfg.xml");
         SessionFactory sessionFactory = configuration.buildSessionFactory();
         return sessionFactory.openSession();
     }
}
