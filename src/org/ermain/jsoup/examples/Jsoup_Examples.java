package org.ermain.jsoup.examples;

import java.io.File;
import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import static java.lang.System.out;

public class Jsoup_Examples {
	
	public Jsoup_Examples() {
		try {
			Document document = Jsoup.connect("https://en.wikipedia.org/wiki/Data_science").get();
			displayImages(document);
		}catch (IOException ex) {
			ex.printStackTrace();
		}
		
		loadDocumentFromFile();
	}
	
	public void loadDocumentFromFile() {
		try {
			File file = new File("Example.html");
			Document document = Jsoup.parse(file, "UTF-8", "");
			listHyperLinks(document);
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void parseString() {
		String html = "<html>\\n\"\r\n" + 
				"                + \"<head><title>Example Document</title></head>\\n\"\r\n" + 
				"                + \"<body>\\n\"\r\n" + 
				"                + \"<p>The body of the document</p>\\n\"\r\n" + 
				"                + \"Interesting Links:\\n\"\r\n" + 
				"                + \"<br>\\n\"\r\n" + 
				"                + \"<a href=\\\"https://en.wikipedia.org/wiki/Data_science\\\">Data Science</a>\\n\"\r\n" + 
				"                + \"<br>\\n\"\r\n" + 
				"                + \"<a href=\\\"https://en.wikipedia.org/wiki/Jsoup\\\">Jsoup</a>\\n\"\r\n" + 
				"                + \"<br>\\n\"\r\n" + 
				"                + \"Images:\\n\"\r\n" + 
				"                + \"<br>\\n\"\r\n" + 
				"                + \" <img src=\\\"eyechart.jpg\\\" alt=\\\"Eye Chart\\\"> \\n\"\r\n" + 
				"                + \"</body>\\n\"\r\n" + 
				"                + \"</html>";
		
		Document document = Jsoup.parse(html);
		listHyperLinks(document);
	}
	
	public void displayBodyText(Document document) {
		// Display the entire body of the document
		String title = document.title();
		out.print("Title: " + title);
		
		out.println("---Body---");
		Elements element = document.select("body");
		out.println("Text: " + element.text());
	}
	
	public void displayImages(Document document) {
		out.println("---Images---");
		
		Elements images = document.select("img[src$=.png]");
		
		for(Element image : images) {
			out.println("\nImage: " + image);
		}
	}
	
	public void listHyperLinks(Document document) {
		out.println("---Links---");
		
		Elements links = document.select("a[href]");
		
		for (Element link : links) {
			out.println("Link: " + link.attr("href")
					  + "Text: " + link.text());
		}
		
		out.println("\n********************");
	}
}
