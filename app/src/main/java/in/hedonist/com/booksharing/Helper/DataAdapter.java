package in.hedonist.com.booksharing.Helper;

public class DataAdapter {
    public String id;
    public String book_name;
    public String book_img;
    public String author_name;
    public String edition;
    public String category;
    public String semister;
    public String price;


    public String getAuthor_name()
    {
        return author_name;
    }
    public void setAuthor_name(String book_author)
    {
        this.author_name=book_author;
    }

    public String getEdition()
    {
        return edition;
    }
    public void setEdition(String edition)
    {
        this.edition=edition;
    }

    public String getCategory()
    {
        return category;
    }
    public void setCategory(String category)
    {
        this.category=category;
    }

    public String getSemister()
    {
        return semister;
    }
    public void setSemister(String semister)
    {
        this.semister=semister;
    }

    public String getImageUrl() {

        return book_img;
    }

    public void  setImageUrl(String imageUrl)
    {
        this.book_img=imageUrl;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }



    public String getImageTitle() {

        return book_name;
    }

    public void setImageTitle(String Imagetitlename) {

        this.book_name = Imagetitlename;
    }

    public String getPrice()
    {
        return price;
    }
    public void setPrice(String price)
    {
        this.price=price;
    }
}
