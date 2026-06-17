public class FactoryMethodTest {

    public static void main(String[] args) {

        System.out.println("--- Test: Factory Method Pattern ---\n");

        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDoc = wordFactory.openNewDocument();
        wordDoc.save();

        System.out.println();

        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdfDoc = pdfFactory.openNewDocument();
        pdfDoc.save();

        System.out.println();

        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excelDoc = excelFactory.openNewDocument();
        excelDoc.save();
    }
}