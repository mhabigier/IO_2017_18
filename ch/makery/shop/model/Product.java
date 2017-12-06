package ch.makery.shop.model;

        import javafx.beans.property.*;

/**
 * Created by Wojtek on 20.03.2017.
 */
public class Product {

    private final StringProperty title;
    private final StringProperty author;
    private final StringProperty publisher;
    private ProductType subject;
    private final IntegerProperty copies;
    private boolean isAvailable;

    public Product() {
        this(null,null);
    }

    public Product(String title, String author) {
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);

        this.publisher = new SimpleStringProperty("");
        isAvailable =true;
        this.copies = new SimpleIntegerProperty(1);
        this.subject = ProductType.NONE;

    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getPublisher() {
        return publisher.get();
    }

    public StringProperty publisherProperty() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher.set(publisher);
    }

    public ProductType getSubject() {

        return subject;
    }
    public String getSubject2() {
        String subj;
        subj = "" + subject;
        return subj;
    }

    public void setSubject(ProductType sub){
        this.subject = sub;
    }
    public int getCopies() {
        return copies.get();
    }

    public IntegerProperty copiesProperty() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies.set(copies);
    }

    public boolean isAvailable() {
        return isAvailable;
    }
    public String isAvailable2() {
       if (isAvailable) return "Available";
       else return "Unavailable";
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }


}
