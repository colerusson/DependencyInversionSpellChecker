import java.io.IOException;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;


public class SpellingChecker {
    private final DocumentSource documentSource;
    private final WordExtractorInterface extractor;
    private final DictionaryInterface dictionary;

    public SpellingChecker(DocumentSource documentSource, WordExtractorInterface extractor, DictionaryInterface dictionary) {
        this.documentSource = documentSource;
        this.extractor = extractor;
        this.dictionary = dictionary;
    }

	public SortedMap<String, Integer> check(String url) throws IOException {

		// download the document content
		//
		String content = documentSource.getContent(url);

		// extract words from the content
		//
		List<String> words = extractor.extract(content);

		// find spelling mistakes
		//
		SortedMap<String, Integer> mistakes = new TreeMap<>();

        for (String word : words) {
            if (!dictionary.isValidWord(word)) {
                if (mistakes.containsKey(word)) {
                    int oldCount = mistakes.get(word);
                    mistakes.put(word, oldCount + 1);
                } else {
                    mistakes.put(word, 1);
                }
            }
        }

		return mistakes;
	}
}

