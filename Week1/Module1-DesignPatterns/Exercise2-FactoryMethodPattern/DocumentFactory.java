public abstract class DocumentFactory {

    public abstract Document createDocument();

    public Document openNewDocument() {
        Document doc = createDocument();  
        doc.open();
        return doc;
    }
}