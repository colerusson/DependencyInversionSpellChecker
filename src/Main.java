import java.io.IOException;
import java.util.SortedMap;


public class Main {

	public static void main(String[] args) {
	
		try {
			DocumentSource documentSource = new URLDocumentSource();
			WordExtractorInterface extractor = new WordExtractor();
			DictionaryInterface dictionary = new Dictionary("C:\\Users\\coler\\Documents\\BYU Fall 2023\\CS 340\\DependencyInversionSpellChecker\\src\\dict.txt");

			SpellingChecker checker = new SpellingChecker(documentSource, extractor, dictionary);
			SortedMap<String, Integer> mistakes = checker.check(args[0]);
			System.out.println(mistakes);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}

